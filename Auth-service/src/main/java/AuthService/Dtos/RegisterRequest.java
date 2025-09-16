package AuthService.Dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {

    private String name;

    private String email;

    private String Password;


}
