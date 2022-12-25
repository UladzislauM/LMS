package academy.belhard.lms.web.controller.impl;

import academy.belhard.lms.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/request_find")
    public String findRequests(Model model) {
        model.addAttribute("requests", requestService.findAll());
        return "requests";
    }
}
