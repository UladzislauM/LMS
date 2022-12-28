package academy.belhard.lms.controller.rest;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.mapper.RequestMapper;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/request")
@RequiredArgsConstructor
public class RequestControllerREST {
    private final RequestService requestService;
    private final RequestMapper mapper;

    @PostMapping("/create")
    public Request create(@RequestBody RequestDto requestDto) {
        return mapper.toRequest(requestService.createRequest(requestDto));
    }

    @GetMapping("/find_all")
    public List<Request> findRequests() {
        List<RequestDto> requestsDto = requestService.getAll();
        return requestsDto.stream()
                .map(mapper::toRequest)
                .collect(Collectors.toList());
    }

    @GetMapping("/find_by_id/{id}")
    public Request findRequestById(@PathVariable Long id) {
        return mapper.toRequest(requestService.getById(id));
    }


    @PutMapping("/update/{id}")
    public Request updateRequest(@RequestBody RequestDto requestDto) {
        return mapper.toRequest(requestService.updateRequest(requestDto));
    }

    @GetMapping("/delete/{id}")
    public void deleteUser(@RequestBody RequestDto requestDto) {
        requestService.deleteRequest(requestDto);
    }
}

