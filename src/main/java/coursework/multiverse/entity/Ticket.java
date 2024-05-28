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

    @Column(nullable = false)
    private String movieTitle;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private int amount;
}
