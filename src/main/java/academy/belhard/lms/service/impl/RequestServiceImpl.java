package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.RequestRepository;
import academy.belhard.lms.data.repository.UserRepository;
import academy.belhard.lms.service.RequestService;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("requestService")
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    public static final String USER_ID_NOT_EXIST = "User id = %s not exist";
    public static final String CREATE_EXCEPTION = "You can't create new Request. Request is created with status = %s";
    public static final String FAILURE_UPDATE = "Failure update";
    public static final String ACTION_FORBIDDEN = "For this status action forbidden";
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final RequestMapper requestMapper;

    public void validate(RequestDtoForSave request) {
        Request oldRequest = requestRepository.findByUser(requestMapper.request(request).getUser());
        Request.Status status;
        try {
            status = oldRequest.getStatus();
        } catch (NullPointerException e) {
            return;
        }
        if (status == Request.Status.CANCELLED) {
            return;
        }
        throw new LmsException(String.format(CREATE_EXCEPTION, status));
    }

    @Override
    public RequestDto create(RequestDtoForSave requestDto) {
        validate(requestDto);
        Long id = requestDto.getUser().getId();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(USER_ID_NOT_EXIST, id)));
        Course course = fakeCourseRepository(requestDto);//FixMe for Course Repository
        Request request = setRequest(user, course);
        return requestMapper.requestDto((requestRepository.save(request)));
    }

    private static Request setRequest(User user, Course course) {
        Request request = new Request();
        request.setStatus(Request.Status.PROCESSING);
        request.setUser(user);
        request.setCourse(course);
        return request;
    }

    private static Course fakeCourseRepository(RequestDtoForSave request) {
        Course course = new Course();
        course.setId(request.getCourse().getId());
        course.setTitle("course_test_1");
        return course;
    }

    @Override
    public Page<RequestDto> getAll(Pageable pageable) {
        Page<Request> requests = requestRepository.findAll(pageable);
        Page<RequestDto> requestsDto;
        try {
            requestsDto = requests.map(requestMapper::requestDto);
        } catch (NullPointerException e) {
            List<RequestDto> list = new ArrayList<>();
            requestsDto = new PageImpl<>(list);
        }
        return requestsDto;
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
                .orElseThrow(() -> new NotFoundException("User is not found"));
        StatusDto statusDto = requestDto.getStatus();
        request.setStatus(Request.Status.valueOf(statusDto.toString()));
        CourseDto courseDto = requestDto.getCourse();
        Course course = requestMapper.course(courseDto);
        request.setCourse(course);
        return request;
    }
}
