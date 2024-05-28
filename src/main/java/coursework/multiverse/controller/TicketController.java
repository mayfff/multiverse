package coursework.multiverse.controller;

import coursework.multiverse.dto.TicketDto;
import coursework.multiverse.entity.Ticket;
import coursework.multiverse.entity.User;
import coursework.multiverse.repository.MovieRepository;
import coursework.multiverse.repository.UserRepository;
import coursework.multiverse.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class TicketController {
    private TicketService ticketService;
    private UserRepository userRepository;
    private MovieRepository movieRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/buy")
    public String showTicketsForm(Model model, @PathVariable Long id) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setMovieTitle(movieRepository.findById(id).get().getTitle());
        model.addAttribute("ticket", ticketDto);
        return "buy-ticket";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/save/ticket")
    public String buyTicket(@ModelAttribute("ticketDto") TicketDto ticketDto) {
        System.out.println(ticketDto.toString());
        ticketService.saveTicket(ticketDto);
        return "redirect:/profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get")
    public String getTickets(Model model) {
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
                List<Ticket> tickets = ticketService.findByUserId(user.getId());
                model.addAttribute("tickets", tickets);
                return "tickets";
            }
        }

        return "redirect:/tickets";
    }
}
