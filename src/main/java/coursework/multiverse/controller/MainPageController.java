package coursework.multiverse.controller;

import coursework.multiverse.dto.UserDto;
import coursework.multiverse.entity.Ticket;
import coursework.multiverse.entity.TicketGrant;
import coursework.multiverse.entity.User;
import coursework.multiverse.repository.TicketGrantRepository;
import coursework.multiverse.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainPageController {
    private final TicketGrantRepository ticketGrantRepository;
    private UserService userService;

    @GetMapping()
    public String showHome() {
        return "index";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String showProfile(Model model) {
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

        User user = userService.findByEmail(email);
        model.addAttribute("user", user);

        List<TicketGrant> ticketGrants = ticketGrantRepository.findByUserId(user.getId());
        List<Ticket> tickets = ticketGrants.stream().map(TicketGrant::getTicket).toList();
        model.addAttribute("tickets", tickets);

        return "profile";
    }

    @GetMapping("/{id}")
    public String showMoviePage(@PathVariable long id) {
        return String.format("%d", id);
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/signup")
    public String showSignup(Model model) {
        model.addAttribute("user", new UserDto());
        return "signup";
    }

    @PostMapping("/signup/save")
    public String registration (@Valid @ModelAttribute("user") UserDto userDto,
                                BindingResult result,
                                Model model) {
        User existingUser = userService.findByEmail(userDto.getEmail());


        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "Вже існує аккаунт з такою поштою");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "signup";
        }

        userService.saveUser(userDto);
        return "redirect:/login";
    }
}
