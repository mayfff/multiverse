package coursework.multiverse.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TicketGrantDto {
    private Long ticketId;
    private Long userId;
}
