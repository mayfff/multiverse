package coursework.multiverse.service.impl;

import coursework.multiverse.dto.TicketDto;
import coursework.multiverse.entity.Ticket;
import coursework.multiverse.entity.TicketGrant;
import coursework.multiverse.entity.User;
import coursework.multiverse.repository.TicketGrantRepository;
import coursework.multiverse.repository.TicketRepository;
import coursework.multiverse.repository.UserRepository;
import coursework.multiverse.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketGrantRepository ticketGrantRepository;
    private final UserRepository userRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, TicketGrantRepository ticketGrantRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketGrantRepository = ticketGrantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveTicket(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setMovieTitle(ticketDto.getMovieTitle());
        ticket.setAmount(ticketDto.getAmount());
        ticket.setTime(ticketDto.getTime());
        ticketRepository.save(ticket);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = null;

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                email = ((UserDetails) principal).getUsername();
            } else {
                email = principal.toString();
            }
        }

        if (email != null) {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                TicketGrant ticketGrant = new TicketGrant();
                ticketGrant.setTicket(ticket);
                ticketGrant.setUser(user);
                ticketGrantRepository.save(ticketGrant);
            }
        }
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findByUserId(Long userId) {
         List<TicketGrant> ticketGrants = ticketGrantRepository.findByUserId(userId);
         return ticketGrants.stream()
                 .map(TicketGrant::getTicket)
                 .collect(Collectors.toList());
    }
}
