package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForSave;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/requests")
@RequiredArgsConstructor
public class RequestRestController {
    private final RequestService requestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RequestDto create(@RequestBody RequestDtoForSave request) {
        return requestService.create(request);
    }

    @GetMapping
    public Page<RequestDto> getAll(Pageable pageable) {
        return requestService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public RequestDto getById(@PathVariable Long id) {
        return requestService.getById(id);
    }

    @PutMapping("/{id}")
    public RequestDto update(@PathVariable Long id, @RequestBody RequestDtoForUpdate request) {
        request.setId(id);
        return requestService.update(request);
    }

    @PatchMapping("/{id}")
    public RequestDto updatePartly(@PathVariable Long id, @RequestBody RequestDtoForUpdate request) {
        request.setId(id);
        return requestService.update(request);
    }
}
