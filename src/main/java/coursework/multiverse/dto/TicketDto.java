package coursework.multiverse.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TicketDto {
    @NotEmpty
    private String movieTitle;

    @NotEmpty(message = "Оберіть час сеансу")
    private String time;

    @NotEmpty
    private int amount;
}
