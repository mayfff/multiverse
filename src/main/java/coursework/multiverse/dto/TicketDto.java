package coursework.multiverse.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TicketDto {
    @NotEmpty
    private String movieTitle;
    @NotEmpty
    private String ticketType;
}
