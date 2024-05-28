package coursework.multiverse.repository;

import coursework.multiverse.entity.TicketGrant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketGrantRepository extends JpaRepository<TicketGrant, Long> {
    List<TicketGrant> findByUserId(Long userId);
}
