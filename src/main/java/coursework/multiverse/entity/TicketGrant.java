package coursework.multiverse.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="user_tickets")
@Data
public class TicketGrant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
