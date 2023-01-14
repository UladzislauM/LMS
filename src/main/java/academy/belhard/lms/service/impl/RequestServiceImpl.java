package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.RequestRepository;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("requestService")
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    public static final String FAILURE_UPDATE = "Failure update";
    public static final String ACTION_FORBIDDEN = "For this status action forbidden";
    private final RequestRepository requestRepository;
    private final RequestMapper mapper;

    public void validate(RequestDtoForSave request) {

    }

    @Override
    public RequestDto create(RequestDtoForSave requestDto) {
        validate(requestDto);
        Request request = mapper.requestDtoForSave(requestDto);
        User user = request.getUser();
        user.setRole(User.Role.STUDENT);
        user.setActive(true);
        request.setUser(user);
        request.setStatus(Request.Status.PROCESSING);
        return mapper.requestDto((requestRepository.save(request)));
    }

    @Override
    public Page<RequestDto> getAll(Pageable pageable) {
        Page<Request> requests = requestRepository.findAll(pageable);
        return requests.map(mapper::requestDto);
    }


    @Override
    public RequestDto getById(Long id) {
        return requestRepository.findById(id)
                .map(mapper::requestDto)
                .orElseThrow(() -> {
                    throw new NotFoundException("Request with id: " + id + " wasn't found");
                });
    }

    @Override
    public RequestDto update(RequestDtoForUpdate dto) {
        validateUpdate(dto);
        Request request = addToRequest(dto);
        Request updated = requestRepository.save(request);
        return mapper.requestDto(updated);
    }

    private void validateUpdate(RequestDtoForUpdate dto) {
        CourseDto newCourseDto = dto.getCourse();
        StatusDto newStatusDto = dto.getStatus();
        RequestDto oldRequest = getById(dto.getId());
        StatusDto oldStatusDto = oldRequest.getStatus();
        CourseDto oldCourseDto = oldRequest.getCourse();

        switch (oldStatusDto) {
            case PROCESSING -> validateUpdateProcessing(newStatusDto);
            case APPROVED -> validateUpdateApproved(newStatusDto);
            case PAID -> validateUpdatePaid(newCourseDto, newStatusDto, oldCourseDto);
            case SATISFIED -> validateUpdateSatisfied(newCourseDto, newStatusDto, oldCourseDto);
            case CANCELLED -> throw new LmsException(ACTION_FORBIDDEN);
            default -> throw new LmsException("Missing status processing: " + oldStatusDto);
        }
    }

    private static void validateUpdateSatisfied(CourseDto newCourseDto,
                                                StatusDto newStatusDto,
                                                CourseDto oldCourseDto) {
        if (newStatusDto == StatusDto.CANCELLED
                || oldCourseDto != newCourseDto) {
            throw new LmsException(FAILURE_UPDATE);
        }
    }

    private static void validateUpdatePaid(CourseDto newCourseDto,
                                           StatusDto newStatusDto,
                                           CourseDto oldCourseDto) {
        if (newStatusDto != StatusDto.SATISFIED
                && newStatusDto != StatusDto.CANCELLED
                || oldCourseDto != newCourseDto) {
            throw new LmsException(FAILURE_UPDATE);
        }
    }

    private static void validateUpdateApproved(StatusDto newStatusDto) {
        if (newStatusDto != StatusDto.PROCESSING
                && newStatusDto != StatusDto.PAID
                && newStatusDto != StatusDto.CANCELLED) {
            throw new LmsException(FAILURE_UPDATE);
        }
    }

    private static void validateUpdateProcessing(StatusDto newStatusDto) {
        if (newStatusDto != StatusDto.APPROVED
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
        Course course = mapper.course(courseDto);
        request.setCourse(course);
        return request;
    }
}
