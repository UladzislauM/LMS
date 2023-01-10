package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.RequestRepository;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.request.RequestDtoForSave;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import academy.belhard.lms.service.mapper.RequestMapper;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service("requestService")
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserService userService;
    private final RequestMapper mapper;

    public void validate(RequestDtoForSave request) {

    }

    @Override
    public RequestDto create(RequestDtoForSave requestDto) {
        validate(requestDto);
        Request request = mapper.RequestDtoForSave(requestDto);
        User user = request.getUser();
        user.setRole(User.Role.STUDENT);
        user.setActive(true);
        request.setUser(user);
        request.setStatus(Request.Status.PROCESSING);
        return mapper.RequestDto((requestRepository.save(request)));
    }

    @Override
    public Page<RequestDto> getAll(Pageable pageable) {
        Page<Request> requests = requestRepository.findAll(pageable);
        return requests.map(mapper::RequestDto);
    }


    @Override
    public RequestDto getById(Long id) {
        return requestRepository.findById(id)
                .map(mapper::RequestDto)
                .orElseThrow(() -> {
                    throw new NotFoundException("Request with id: " + id + " wasn't found");
                });
    }

    @Override
    public RequestDto update(RequestDtoForUpdate requestDto) {
        Request request = mapper.Request(requestDto);
        request.setUser(mapper.User(userService.getUserById(getById(request.getId()).getUser().getId())));
        return mapper.RequestDto((requestRepository.save(request)));
    }
}
