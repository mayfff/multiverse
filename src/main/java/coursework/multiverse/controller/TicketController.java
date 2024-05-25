package coursework.multiverse.controller;

import coursework.multiverse.dto.TicketDto;
import coursework.multiverse.entity.Ticket;
import coursework.multiverse.entity.TicketGrant;
import coursework.multiverse.entity.User;
import coursework.multiverse.repository.UserRepository;
import coursework.multiverse.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/tickets")
public class TicketController {
    private TicketService ticketService;
    private UserRepository userRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public String showTicketsForm(Model model) {
        model.addAttribute("ticketDto", new TicketDto());
        return "tickets_buy";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/buy")
    public String buyTicket(@ModelAttribute("ticketDto") TicketDto ticketDto, Model model) {
        ticketService.saveTicket(ticketDto);
        return "redirect:/tickets?success";
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
