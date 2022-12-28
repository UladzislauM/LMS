package academy.belhard.lms.controller.web;

import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.RequestDto;
import academy.belhard.lms.service.impl.ObjectMapperR;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestControllerWeb {
    private final RequestService requestService;
    private final ObjectMapperR mapper;

    @GetMapping("/request_find")
    public String findRequests(Model model) {
        model.addAttribute("requests", requestService.findAll());
        return "request";
    }

    @GetMapping("/request_find_by_id/{id}")
    public String findRequestById(@PathVariable Long id, Model model) {
        model.addAttribute("request", requestService.findById(id));
        return "request_by_id";
    }

    @PostMapping("/create")
    public String createRequest(@ModelAttribute RequestDto requestDto) {
        requestService.create(requestDto);
        return "redirect:/request/request_find";
    }

    @PostMapping("/request_update/{id}")
    public String editRequest(@ModelAttribute("request") RequestDto requestDto) {
        requestService.update(requestDto);
        return "redirect:/request/request_find";
    }

}
