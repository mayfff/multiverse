package coursework.multiverse.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MovieDto {
    @NotEmpty
    private String title;
}
