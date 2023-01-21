package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.CourseRepository;
import academy.belhard.lms.data.repository.RequestRepository;
import academy.belhard.lms.data.repository.UserRepository;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.request.CourseDto;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForSave;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import academy.belhard.lms.service.exception.LmsException;
import academy.belhard.lms.service.exception.NotFoundException;
import academy.belhard.lms.service.mapper.RequestMapper;
import academy.belhard.lms.service.mapper.UserMapper;
import academy.belhard.lms.service.dto.request.StatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("requestService")
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    public static final String FAILURE_UPDATE = "Failure update";
    public static final String ACTION_FORBIDDEN = "For this status action forbidden";
    public static final String REQUEST_EXITING_EXCEPTION = "You request with status = %s exiting";
    public static final String REQUEST_IS_NOT_FOUND = "Request is not found";
    public static final String USER_IS_NOT_FOUND = "User is not found";
    public static final String COURSE_IS_NOT_FOUND = "Course is not found";
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final RequestMapper requestMapper;

    public void validate(RequestDtoForSave request) {
        User user = requestMapper.user(request.getUser());
        requestRepository.findByUser(user)
                .ifPresent(r -> {
                    Request.Status status = r.getStatus();
                    if (status != Request.Status.CANCELLED) {
                        throw new LmsException(String.format(REQUEST_EXITING_EXCEPTION, status));
                    }
                });
    }

    @Override
    public RequestDto create(RequestDtoForSave requestDto) {
        validate(requestDto);
        Long userId = requestDto.getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new NotFoundException(USER_IS_NOT_FOUND);
                });
        Request request = new Request();
        request.setUser(user);
        Course course = courseRepository.findById(requestDto.getCourse().getId())
                .orElseThrow(() -> {
                    throw new NotFoundException(COURSE_IS_NOT_FOUND);
                });
        request.setCourse(course);
        request.setStatus(Request.Status.PROCESSING);
        return requestMapper.requestDto((requestRepository.save(request)));
    }

    @Override
    public Page<RequestDto> getAll(Pageable pageable) {
        Page<Request> requests = requestRepository.findAll(pageable);
        return requests.map(requestMapper::requestDto);
    }

    @Override
    public RequestDto getById(Long id) {
        return requestRepository.findById(id)
                .map(requestMapper::requestDto)
                .orElseThrow(() -> {
                    throw new NotFoundException("Request with id: " + id + " wasn't found");
                });
    }

    @Override
    public RequestDto update(RequestDtoForUpdate dto) {
        validateUpdate(dto);
        Request request = addToRequest(dto);
        Request updated = requestRepository.save(request);
        return requestMapper.requestDto(updated);
    }

    private void validateUpdate(RequestDtoForUpdate dto) {
        CourseDto newCourseDto = dto.getCourse();
        StatusDto newStatusDto = dto.getStatus();
        RequestDto oldRequest = getById(dto.getId());
        StatusDto oldStatusDto = oldRequest.getStatus();
        CourseDto oldCourseDto = oldRequest.getCourse();

        switch (oldStatusDto) {
            case PROCESSING -> validateUpdateProcessing(newStatusDto);
            case APPROVED -> validateUpdateApproved(newStatusDto, newCourseDto, oldCourseDto);
            case PAID -> validateUpdatePaid(newCourseDto, newStatusDto, oldCourseDto);
            case SATISFIED -> validateUpdateSatisfied(newCourseDto, newStatusDto, oldCourseDto);
            case CANCELLED -> throw new LmsException(ACTION_FORBIDDEN);
            default -> throw new LmsException("Missing status processing: " + oldStatusDto);
        }
    }

    private static void validateUpdateSatisfied(CourseDto newCourseDto,
                                                StatusDto newStatusDto,
                                                CourseDto oldCourseDto) {
        if (newStatusDto != StatusDto.CANCELLED) {
            throw new LmsException(FAILURE_UPDATE);
        } else if (!oldCourseDto.toString().equals(newCourseDto.toString())) {
            throw new LmsException(FAILURE_UPDATE);
        }
    }

    private static void validateUpdatePaid(CourseDto newCourseDto,
                                           StatusDto newStatusDto,
                                           CourseDto oldCourseDto) {
        if (newStatusDto != StatusDto.SATISFIED
                && newStatusDto != StatusDto.CANCELLED) {
            throw new LmsException(FAILURE_UPDATE);
        } else if (!oldCourseDto.toString().equals(newCourseDto.toString())) {
            throw new LmsException(FAILURE_UPDATE);
        }
    }

    private static void validateUpdateApproved(StatusDto newStatusDto,
                                               CourseDto newCourseDto,
                                               CourseDto oldCourseDto) {
        if (newStatusDto != StatusDto.PAID
                && newStatusDto != StatusDto.CANCELLED) {
            throw new LmsException(FAILURE_UPDATE);
        } else if (!oldCourseDto.toString().equals(newCourseDto.toString())) {
            throw new LmsException(FAILURE_UPDATE);
        }
    }

    private static void validateUpdateProcessing(StatusDto newStatusDto) {
        if (newStatusDto != StatusDto.PROCESSING
                && newStatusDto != StatusDto.APPROVED
                && newStatusDto != StatusDto.CANCELLED) {
            throw new LmsException(FAILURE_UPDATE);
        }
    }

    private Request addToRequest(RequestDtoForUpdate requestDto) {
        Request request = requestRepository.findById(requestDto.getId())
                .orElseThrow(() -> new NotFoundException(REQUEST_IS_NOT_FOUND));
        StatusDto statusDto = requestDto.getStatus();
        request.setStatus(Request.Status.valueOf(statusDto.toString()));
        Course course;
        CourseDto courseDto = requestDto.getCourse();
        if(courseDto != null){//FixMe Herman please, because I don't got how do it different...
            course = courseRepository.findById(requestDto.getCourse().getId())
                    .orElseThrow(() -> {
                throw new NotFoundException(COURSE_IS_NOT_FOUND);
            });
        }else {
            course = request.getCourse();
        }
        request.setCourse(course);
        return request;
    }
}
