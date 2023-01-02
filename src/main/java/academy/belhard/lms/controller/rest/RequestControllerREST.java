package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1.0/request")
@RequiredArgsConstructor
public class RequestControllerREST {
    private final RequestService requestService;

    @RequestMapping(path = "/get_all", method = RequestMethod.POST, consumes = "application/json")
    public Page<RequestDto> getAllRequest(@RequestParam(required = false) Optional<Integer> page,
                                          @RequestParam(required = false) Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        return requestService.getAll(
                PageRequest.of(currentPage - 1, pageSize));
    }

    @GetMapping("/get_by_id/{id}")
    public RequestDto getRequestById(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @PostMapping("/create")
    public RequestDto createRequest(@RequestBody RequestDto requestDto,
                                    @RequestParam String user_email, String course_title) {
        requestService.addParamsToRequest(requestDto, user_email, course_title);
        return requestService.createRequest(requestDto);
    }

    @PutMapping("/update/{id}")
    public RequestDto updateRequest(@RequestBody RequestDto requestDto,
                                    @RequestParam String user_email, String course_title) {
        requestService.addParamsToRequest(requestDto, user_email, course_title);
        return requestService.updateRequest(requestDto);
    }

    @GetMapping("/update_form/{id}")
    public RequestDto toUpdateForm(@PathVariable Long id, Model model) {
        return requestService.getById(id);
    }
}

