package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.RequestRep;
import academy.belhard.lms.service.dto.request.RequestDtoForSaving;
import academy.belhard.lms.service.exception.LmsException;
import academy.belhard.lms.service.mapper.RequestMapper;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.request.RequestDto;
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

    public void validate(Request request) {
        Request.StatusReq reqStatus = request.getStatusReq();
        boolean check = false;
        for (Request.StatusReq thisEnum : Request.StatusReq.values()) {
            if (thisEnum == reqStatus) {
                check = true;
            }
        }
        if (!check) {
            throw new LmsException("Request Status " + reqStatus + " is not valid");
        }
    }

    @Override
    public Page<RequestDto> getAll(Pageable pageable) {
        List<Request> requests = requestRep.findAll();
        if (requests == null) {
            throw new NotFoundException("FindAll: Requests is not exist...");
        } else {
            requests.stream().forEach(req -> validate(req));
            List<RequestDto> requestsDto = requests.stream().map(mapper::toRequestDto).sorted(((o1, o2) ->
                    ((Long) o1.getId()).compareTo(o2.getId()))).toList();
            int pageSize = pageable.getPageSize();
            int currentPage = pageable.getPageNumber();
            int startItem = currentPage * pageSize;
            List<RequestDto> list;

            if (requestsDto.size() < startItem) {
                list = Collections.emptyList();
            } else {
                int toIndex = Math.min(startItem + pageSize, requestsDto.size());
                list = requestsDto.subList(startItem, toIndex);
            }
            return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), requestsDto.size());
        }
    }


    @Override
    public RequestDto getById(Long id) {
        Request request = requestRep.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Request with id: " + id + " wasn't found");
        });
        validate(request);
        RequestDto requestDto = mapper.toRequestDto(request);
        return requestDto;
    }

    @Override
    public void delete(RequestDto requestDto) {
        requestDto.setDeleted(true);
        requestRep.save(mapper.toRequest(requestDto));
    }

    @Override
    public RequestDtoForSaving create(RequestDtoForSaving requestDto) {
        Request request = mapper.toRequestDtoForSaving(requestDto);
        if (request == null) {
            throw new NotFoundException("Failed create request");
        }
        validate(request);
        User user = request.getUser();
        user.setRole(User.Role.STUDENT);
        request.setUser(user);
        return mapper.toRequestForSaving((requestRep.save(request)));
    }

    @Override
    public RequestDto update(RequestDto requestDto) {
        Request request = mapper.toRequest(requestDto);
        if (request == null) {
            throw new NotFoundException("Failed create request");
        }
        validate(request);
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
