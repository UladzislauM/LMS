package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.RequestRepository;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.request.CourseDto;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForSave;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import academy.belhard.lms.service.dto.request.StatusDto;
import academy.belhard.lms.service.exception.LmsException;
import academy.belhard.lms.service.exception.NotFoundException;
import academy.belhard.lms.service.mapper.RequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("requestService")
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    public static final String FAILURE_UPDATE = "Failure update";
    public static final String COURSE_CHANGE_NOT_POSSIBLE = "Course change not possible";
    public static final String ACTION_FORBIDDEN = "For this status action forbidden";
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
    public RequestDto update(RequestDtoForUpdate requestDtoForUpdate) {
        Request request;
        CourseDto newCourseDto = requestDtoForUpdate.getCourse();
        StatusDto newStatusDto = requestDtoForUpdate.getStatus();
        RequestDto oldRequest = getById(requestDtoForUpdate.getId());
        StatusDto oldStatusDto = oldRequest.getStatus();
        CourseDto oldCourseDto = oldRequest.getCourse();

        switch (oldStatusDto) {
            case PROCESSING -> {
                if (newStatusDto == StatusDto.APPROVED || newStatusDto == StatusDto.CANCELLED) {
                    request = addToRequest(requestDtoForUpdate);
                    break;
                }
                throw new LmsException(FAILURE_UPDATE);
            }
            case APPROVED -> {
                if (newStatusDto == StatusDto.PROCESSING || newStatusDto == StatusDto.PAID ||
                        newStatusDto == StatusDto.CANCELLED) {
                    request = addToRequest(requestDtoForUpdate);
                    break;
                }
                throw new LmsException(FAILURE_UPDATE);
            }
            case PAID -> {
                if (newStatusDto == StatusDto.SATISFIED || newStatusDto == StatusDto.CANCELLED) {
                    request = addToRequest(requestDtoForUpdate);
                    checkCourse(newCourseDto, oldCourseDto);
                    break;
                }
                throw new LmsException(FAILURE_UPDATE);
            }
            case SATISFIED -> {
                if (newStatusDto == StatusDto.CANCELLED) {
                    request = addToRequest(requestDtoForUpdate);
                    checkCourse(newCourseDto, oldCourseDto);
                    break;
                }
                throw new LmsException(FAILURE_UPDATE);
            }
            case CANCELLED -> throw new LmsException(ACTION_FORBIDDEN);
            default -> throw new LmsException(FAILURE_UPDATE);
        }
        return mapper.RequestDto((requestRepository.save(request)));
    }

    private void checkCourse(CourseDto newCourseDto, CourseDto oldCourseDto) {
        if (oldCourseDto != newCourseDto) {
            throw new LmsException(COURSE_CHANGE_NOT_POSSIBLE);
        }
    }

    private Request addToRequest(RequestDtoForUpdate requestDtoForUpdate) {
        Request request;
        request = mapper.Request(requestDtoForUpdate);
        request.setUser(mapper.User(userService.getUserById(getById(request.getId()).getUser().getId())));
        return request;
    }
}
