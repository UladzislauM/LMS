package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.mapper.RequestMapper;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/request")
@RequiredArgsConstructor
public class RequestControllerREST {
    private final RequestService requestService;
    private final RequestMapper mapper;

    @PostMapping("/create")
    public RequestDto createRequest(@RequestBody RequestDto requestDto) {
        return requestService.createRequest(requestDto);
    }

    @GetMapping("/find_all")
    public List<RequestDto> getAllRequest() {
        return requestService.getAll();
    }

    @GetMapping("/find_by_id/{id}")
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

