package AuthService.Services;

import AuthService.Dtos.LoginRequest;
import AuthService.Dtos.LoginResponse;
import AuthService.Dtos.RegisterRequest;
import AuthService.Dtos.RegisterResponse;

public interface AuthService {

   RegisterResponse registerUser(RegisterRequest request);

   LoginResponse loginUser(LoginRequest request);


}
