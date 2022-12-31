package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.mapper.RequestMapper;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/request")
@RequiredArgsConstructor
public class RequestControllerREST {
    private final RequestService requestService;

    @PostMapping("/create")
    public RequestDto createRequest(@RequestBody RequestDto requestDto) {
        return requestService.createRequest(requestDto);
    }

    @GetMapping("/get_all")
    public Page<RequestDto> getAllRequest(@RequestParam(required = false) int page) {
        Pageable pageable = PageRequest.of(page - 1, 5, Sort.Direction.ASC, "id");
        return requestService.getAll(pageable);
    }

    @GetMapping("/get_by_id/{id}")
    public RequestDto getRequestById(@PathVariable Long id) {
        return requestService.getById(id);
    }


    @PutMapping("/update/{id}")
    public RequestDto updateRequest(@RequestBody RequestDto requestDto) {
        return requestService.updateRequest(requestDto);
    }

    @GetMapping("/delete/{id}")
    public void deleteRequest(@RequestBody RequestDto requestDto) {
        requestService.deleteRequest(requestDto);
    }
}

