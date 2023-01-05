package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.request.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1.0/request")
@RequiredArgsConstructor
public class RequestControllerREST {
    private final RequestService requestService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public Page<RequestDto> getAllRequest(@RequestParam(required = false) Optional<Integer> page,
                                          @RequestParam(required = false) Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        return requestService.getAll(
                PageRequest.of(currentPage - 1, pageSize));
    }

    @GetMapping("/{id}")
    public RequestDto getRequestById(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST, consumes = "application/json")
    public RequestDto createRequest(@RequestBody RequestDto request) {
        return requestService.create(request);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public RequestDto editRequest(@RequestBody RequestDto request) {
        return requestService.update(request);
    }
}

