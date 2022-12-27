package academy.belhard.lms.web.controller.impl;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.Lesson;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.CourseDto;
import academy.belhard.lms.service.dto.LessonDto;
import academy.belhard.lms.service.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;
    private final UserService userService;

    @GetMapping("/request_find")
    public String findRequests(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("requests", requestService.findAll());
        return "request";
    }

    @GetMapping("/request_find_by_id/{id}")
    public String findRequestById(@PathVariable Long id, Model model){
        model.addAttribute("request", requestService.findById(id));
        return "request_by_id";
    }

}
