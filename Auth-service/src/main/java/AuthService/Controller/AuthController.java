package AuthService.Controller;

import AuthService.Services.AuthServiceImpl;
import AuthService.Dtos.LoginRequest;
import AuthService.Dtos.LoginResponse;
import AuthService.Dtos.RegisterRequest;
import AuthService.Dtos.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return authService.registerUser(request); // matches interface
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.loginUser(request); // matches interface
    }
}
