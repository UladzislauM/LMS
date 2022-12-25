package academy.belhard.lms.web.controller.impl;

import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final RequestService requestService;

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request, HttpSession session) {
        session.setAttribute("refer", request.getHeader("referer"));
        return "login_form";
    }

    @PostMapping("/login")
    public String login(@RequestParam String login,
                        @RequestParam String password, HttpSession session) {
        UserDto userDto = userService.login(login, password);
        session.setAttribute("user", userDto);
        return "redirect:" + session.getAttribute("refer");
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session.invalidate();
        return "redirect:/";
    }
}