package AuthService.Services;

import AuthService.CustomExceptions.InvalidCredentialsException;
import AuthService.CustomExceptions.UserAlreadyExistsException;
import AuthService.CustomExceptions.UserNotFoundException;
import AuthService.Dtos.LoginRequest;
import AuthService.Dtos.LoginResponse;
import AuthService.Dtos.RegisterRequest;
import AuthService.Dtos.RegisterResponse;
import AuthService.Entity.Role;
import AuthService.Entity.User;
import AuthService.Repository.RoleRepo;
import AuthService.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public RegisterResponse registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists: " + request.getEmail());
        }

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        User user = User.builder()
                .userName(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singleton(userRole))
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return RegisterResponse.builder()
                .message("User registered successfully")
                .token(token)
                .build();
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }catch (Exception ex) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + request.getEmail()));

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .message("Login successful")
                .token(token)
                .build();
    }
}


