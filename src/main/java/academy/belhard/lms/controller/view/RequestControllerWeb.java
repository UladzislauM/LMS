package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForSave;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestControllerWeb {
    private final RequestService requestService;

    @PostMapping
    public String create(@ModelAttribute RequestDtoForSave request, Pageable pageable) {
        requestService.create(request);
        return "redirect:/requests?size=" + pageable.getPageSize() + "&page=" + pageable.getPageNumber();
    }

    @GetMapping
    public String getAll(Model model, Pageable pageable) {
        Page<RequestDto> requests = requestService.getAll(pageable);
        model.addAttribute("requests", requests);
        return "request";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("request", requestService.getById(id));
        return "request_by_id";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute RequestDtoForUpdate request) {
        requestService.update(request);
        return "redirect:/requests?sort=id,asc";
    }

    @GetMapping("/update_form/{id}")
    public String toUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("request", requestService.getById(id));
        return "update_request";
    }

}
