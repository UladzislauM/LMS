package academy.belhard.lms.controller.view;

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

    @GetMapping("/")
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

    @GetMapping("/{id}")
    public String getRequestById(@PathVariable Long id, Model model,
                                 @RequestParam(value = "size", required = false) String size,
                                 @RequestParam(value = "page", required = false) String page) {
        model.addAttribute("request", requestService.getById(id));
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        return "request_by_id";
    }


    @PostMapping("/")
    public String createRequest(@ModelAttribute RequestDto request,
                                @RequestParam String user_email, String course_title, String page_now, String size) {
        requestService.addParamsToRequest(request, user_email, course_title);
        requestService.createRequest(request);
        return "redirect:/request/?size=" + size + "&page=" + page_now;
    }

    @PostMapping("/{id}")
    public String editRequest(@ModelAttribute RequestDto request,
                              @RequestParam String user_email, String course_title, String page_now, String size) {
        requestService.addParamsToRequest(request, user_email, course_title);
        requestService.updateRequest(request);
        return "redirect:/request/?size=" + size + "&page=" + page_now;
    }

    @GetMapping("/update_form/{id}")
    public String toUpdateForm(@PathVariable Long id, Model model,
                               @RequestParam(value = "size", required = false) String size,
                               @RequestParam(value = "page", required = false) String page) {
        model.addAttribute("request", requestService.getById(id));
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        return "update_request";
    }

}
