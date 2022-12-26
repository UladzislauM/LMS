package academy.belhard.lms.web.controller.impl;

import academy.belhard.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @PostMapping("/find_all")
    public String findRequests(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }
}
