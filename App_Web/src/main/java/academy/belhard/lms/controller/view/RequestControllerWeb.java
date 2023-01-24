package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.CourseService;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForSave;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import academy.belhard.lms.service.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestControllerWeb {
    private final RequestService requestService;
    private final UserService userService;
    private final CourseService courseService;

    @GetMapping("/create")
    public String createForm(Model model) {
        Page<UserDto> users = userService.getAll(Pageable.unpaged());
        model.addAttribute("users", users);
        Page<CourseDto> courses = courseService.getAll(Pageable.unpaged());
        model.addAttribute("courses", courses);
        return "create_request";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("request") RequestDtoForSave request) {
        RequestDto created = requestService.create(request);
        return "redirect:/requests/" + created.getId();
    }

    @GetMapping
    public String getAll(Model model,@PageableDefault (size = 10) Pageable pageable) {
        Page<RequestDto> requests = requestService.getAll(pageable);
        model.addAttribute("requests", requests);
        return "requests";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        RequestDto request = requestService.getById(id);
        model.addAttribute("request", request);
        return "request";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        RequestDto toUpdate = requestService.getById(id);
        model.addAttribute("request", toUpdate);
        return "update_request";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute RequestDtoForUpdate request) {
        request.setId(id);
        requestService.update(request);
        return "redirect:/requests/" + id;
    }
}
