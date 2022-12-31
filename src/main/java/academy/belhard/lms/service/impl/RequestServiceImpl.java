package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.repository.RequestRep;
import academy.belhard.lms.service.mapper.RequestMapper;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.RequestDto;
import academy.belhard.lms.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("requestService")
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRep requestRep;
    private final RequestMapper mapper;

    public void validate(Request request) {//fixMe

        throw new RuntimeException("... is not valid, ...");

    }

    @Override
    public Page<RequestDto> getAll(Pageable pageable) {
        List<Request> requestsDto = requestRep.findAll();
        if (requestsDto == null) {
            throw new NotFoundException("FindAll: Requests is not exist...");
        } else {
            List<RequestDto> requests = requestsDto.stream().map(mapper::toRequestDto).sorted(((o1, o2) ->
                    ((Long) o1.getId()).compareTo(o2.getId()))).toList();
            int pageSize = pageable.getPageSize();
            int currentPage = pageable.getPageNumber();
            int startItem = currentPage * pageSize;
            List<RequestDto> list;

            if (requests.size() < startItem) {
                list = Collections.emptyList();
            } else {
                int toIndex = Math.min(startItem + pageSize, requests.size());
                list = requests.subList(startItem, toIndex);
            }

            return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), requests.size());
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
        requestDto.setDeleted(true);
        requestRep.save(mapper.toRequest(requestDto));
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
        Request oldRequest = requestRep.findById(requestDto.getId()).orElseThrow(() -> {
            throw new NotFoundException("Request with id: " + requestDto.getId() + " wasn't found");
        });
        Request request = mapper.toRequest(requestDto);
        if (request == null) {
            throw new NotFoundException("Failed update request");
        }
        return mapper.toRequestDto((requestRep.save(request)));
    }

    @Override
    public List<Integer> getPageNumbers(Model model, int totalPages) {
        List<Integer> pageNumbers = new ArrayList<>();
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        } else {
            pageNumbers.add(1);
        }
        return pageNumbers;
    }
}
