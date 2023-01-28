package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.TokenLinkService;
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
    private static final String CONFIRMATION_MESSAGE = "An email has been sent to your email address with a confirmation link.";
    private final UserService userService;
    private final TokenLinkService tokenLinkService;

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
                                      @RequestParam String confirmPassword,
                                      Model model) {
        if (!user.getPassword().equals(confirmPassword)) {
            throw new LmsException("Password doesn't match.");
        }
        userService.registerUser(user);
        model.addAttribute("message", CONFIRMATION_MESSAGE);
        return "info";
    }

    @GetMapping("/activate/{token}/{userId}")
    public String activateUser(@PathVariable String token, @PathVariable Long userId) {
        tokenLinkService.activate(token);
        userService.activateUser(userId);
        return "redirect:/auth/login";
    }

    @GetMapping("/recoveryPass")
    public String recoveryPassPage() {
        return "recovery_pass";
    }

    @PostMapping("/recoveryPass")
    public String performRecoveryPass(@RequestParam String email, Model model) {
        userService.recoveryPassword(email);
        model.addAttribute("message", CONFIRMATION_MESSAGE);
        return "info";
    }

    @GetMapping("/recoveryPass/{token}/{userId}")
    public String recoveryPass(Model model, @PathVariable String token, @PathVariable Long userId) {
        tokenLinkService.activate(token);
        model.addAttribute("userId", userId);
        return "new_password";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam Long userId,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new LmsException("Password doesn't match.");
        }
        userService.changePassword(userId, newPassword);
        return "redirect:/auth/login";
    }
}
