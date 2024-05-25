package coursework.multiverse.service;

import coursework.multiverse.dto.TicketDto;
import coursework.multiverse.entity.Ticket;

import java.util.List;

public interface TicketService {

    void saveTicket(TicketDto ticketDto);

    List<Ticket> getAllTickets();

    List<Ticket> findByUserId(Long userId);
}
