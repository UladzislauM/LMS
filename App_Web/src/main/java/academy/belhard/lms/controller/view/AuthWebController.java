package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.EmailLinkService;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.user.UserDtoForSave;
import academy.belhard.lms.service.exception.LmsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthWebController {
    private final UserService userService;
    private final EmailLinkService emailLinkService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute UserDtoForSave user,
                                      @RequestParam("password") String password,
                                      @RequestParam("confirmPassword") String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new LmsException("Password doesn't match.");
        }
        userService.registerUser(user);
        return "info";
    }

    @GetMapping("/activate/{token}/{userId}")
    public String activateUser(@PathVariable String token, @PathVariable Long userId) {
        emailLinkService.activate(token);
        userService.activate(userId, token);
        return "redirect:/auth/login";
    }
}
