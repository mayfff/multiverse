package coursework.multiverse.dto;

import jakarta.validation.constraints.NotEmpty;

public class MovieDto {
    @NotEmpty
    private String title;
    private String description;
}
