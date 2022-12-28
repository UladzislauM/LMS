package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.repository.RequestRep;
import academy.belhard.lms.mapper.RequestMapper;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.RequestDto;
import academy.belhard.lms.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("requestService")
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRep requestRep;
    private final RequestMapper mapper;

    public void validate(Request request) {//fixMe

        throw new RuntimeException("... is not valid, ...");

    }

    @Override
    public List<RequestDto> getAll() {
        List<Request> requests = requestRep.findAll();
        if (requests == null) {
            throw new NotFoundException("FindAll: Requests is not exist..."); //fixMe
        } else {
            return requests.stream().map(mapper::toRequestDto).toList();
        }
    }

    @Override
    public RequestDto getById(Long id) {
        Request request = requestRep.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Request with id: " + id + " wasn't found");
        });
        RequestDto requestDto = mapper.toRequestDto(request);
        if (requestDto == null) {
            throw new NotFoundException("RequestDto with id: " + id + " wasn't found");
        }
        return requestDto;
    }

    @Override
    public void deleteRequest(RequestDto requestDto) {
        requestRep.delete(mapper.toRequest(requestDto));
    }

    @Override
    public RequestDto createRequest(RequestDto requestDto) {
        Request request = mapper.toRequest(requestDto);
        if (request == null) {
            throw new NotFoundException("Failed create request");
        }
        return mapper.toRequestDto((requestRep.save(request)));
    }

    @Override
    public RequestDto updateRequest(RequestDto requestDto) {
        Request request = mapper.toRequest(requestDto);
        if (request == null) {
            throw new NotFoundException("Failed update request");
        }
        return mapper.toRequestDto((requestRep.save(request)));
    }
}
