package coursework.multiverse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty(message = "Ім`я не може бути порожнім")
    private String name;

    @Email(message = "Введіть пошту", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @Size(min = 8, max = 255, message = "Пароль має бути більше ніж 8 символів")
    private String password;
}
