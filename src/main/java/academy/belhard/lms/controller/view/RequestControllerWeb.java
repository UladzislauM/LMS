package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.dto.CourseDto;
import academy.belhard.lms.service.dto.UserDto;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/request")
@RequiredArgsConstructor
public class RequestControllerWeb {
    private final RequestService requestService;

    @GetMapping("/get_all")
    public String getRequests(Model model, @RequestParam(required = false) Optional<Integer> page,
                              @RequestParam(required = false) Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<RequestDto> requestDtoPage = requestService.getAll(
                PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("requests", requestDtoPage);
        int totalPages = requestDtoPage.getTotalPages();
        model.addAttribute("pageNumbers", requestService.getPageNumbers(model, totalPages));
        return "request";
    }

    @GetMapping("/get_by_id/{id}")
    public String getRequestById(@PathVariable Long id, Model model) {
        model.addAttribute("request", requestService.getById(id));
        return "request_by_id";
    }


    @PostMapping("/create")
    public String createRequest(@ModelAttribute RequestDto requestDto,
                                @RequestParam String user_email, String course_title, String page_now, String size) {
        addParamsToRequest(requestDto, user_email, course_title);
        requestService.createRequest(requestDto);
        return "redirect:/request/get_all?size=" + size + "&page=" + page_now;
    }

    @PostMapping("/update/{id}")
    public String editRequest(@ModelAttribute RequestDto requestDto,
                              @RequestParam String user_email, String course_title, String page_now, String size) {
        addParamsToRequest(requestDto, user_email, course_title);
        requestService.updateRequest(requestDto);
        return "redirect:/request/get_all?size=" + size + "&page=" + page_now;
    }

    private static void addParamsToRequest(RequestDto requestDto, String user_email, String course_title) {
        CourseDto courseDto = new CourseDto();
        courseDto.setTitle(course_title);
        requestDto.setCourse(courseDto);
        UserDto userDto = new UserDto();
        userDto.setEmail(user_email);
        requestDto.setUser(userDto);
    }

    @GetMapping("/update_form/{id}")
    public String toUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("request", requestService.getById(id));
        return "update_request";
    }

}
