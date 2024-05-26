package coursework.multiverse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @Email(message = "Please, enter an email")
    private String email;

    @Size(min = 8, max = 255, message = "Password should be 8 or more characters long!")
    private String password;
}
