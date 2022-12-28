package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.dto.data.entity.Request;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.RequestDto;
import academy.belhard.lms.service.impl.ObjectMapperR;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/request")
@RequiredArgsConstructor
public class RequestControllerREST {
    private final RequestService requestService;
    private final ObjectMapperR mapper;

    @PostMapping("/create")
    public Request create(@RequestBody RequestDto requestDto) {
        return mapper.toRequest(requestService.create(requestDto));
    }

    @GetMapping("/find_all")
    public List<Request> findRequests() {
        List<RequestDto> requestsDto = requestService.findAll();
        return requestsDto.stream()
                .map(mapper::toRequest)
                .collect(Collectors.toList());
    }

    @GetMapping("/find_by_id/{id}")
    public Request findRequestById(@PathVariable Long id) {
        return mapper.toRequest(requestService.findById(id));
    }


    @PutMapping("/update/{id}")
    public Request updateRequest(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        return mapper.toRequest(requestService.update(requestDto));
    }

    @GetMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        requestService.delete(id);
    }
}

