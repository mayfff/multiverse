package coursework.multiverse.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="tickets")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String movieTitle;

    private String ticketType;

}
