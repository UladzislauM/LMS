package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.RequestRepository;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.request.CourseDto;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import academy.belhard.lms.service.dto.request.StatusDto;
import academy.belhard.lms.service.dto.user.ContactPreferencesDto;
import academy.belhard.lms.service.dto.user.RoleDto;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.exception.LmsException;
import academy.belhard.lms.service.mapper.RequestMapperImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class RequestServiceImplTest {
    public static final long EXISTING_REQUEST_ID = 1L;
    public static final long EXISTING_USER_ID = 1L;
    public static final long EXISTING_COURSE_ID = 1L;
    public static final String EXISTING_COURSE_STRING = "course_1";
    public static final String FOR_UPDATE_COURSE = "course_2";
    private static RequestService requestService;
    @Mock
    private static RequestRepository requestRepositoryMock;
    private static User user;
    private static UserDto userDto;

    private static RequestDto existingDto;

    private static RequestDtoForUpdate requestDtoForUpdate;

    private static Course courseExisting;

    private static Course courseForUpdate;

    private static CourseDto courseDtoExisting;

    private static CourseDto courseDtoForUpdate;

    private static Request requestForSaving;

    private static Request requestExisting;

    private static RequestDto fromServiceRequestDto;

    @BeforeAll
    static void beforeAll() {
        requestRepositoryMock = Mockito.mock(RequestRepository.class);
        requestService = new RequestServiceImpl(requestRepositoryMock, new RequestMapperImpl());
        initUser();
        initUserDto();
        initCourseExisting();
        initCourseForUpdate();
        initCourseDtoExisting();
        initCourseDtoForUpdate();
        initRequestDto();
        initRequestDtoForUpdate();
        initRequestForSaving();
        initRequestExisting();
        Optional<Request> optional = Optional.of(requestExisting);
        when(requestRepositoryMock.findById(EXISTING_REQUEST_ID)).thenReturn(optional);
        when(requestRepositoryMock.save(requestForSaving)).thenReturn(requestForSaving);
    }

    @Test
    void updatePositiveCourseWhenStatusProcessing() {
        requestExisting.setStatus(Request.Status.PROCESSING);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PROCESSING);
        requestDtoForUpdate.setCourse(courseDtoForUpdate);
        requestForSaving.setStatus(Request.Status.PROCESSING);
        requestForSaving.setCourse(courseForUpdate);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals((courseDtoForUpdate), fromServiceRequestDto.getCourse());
    }

    @Test
    void updatePositiveStatusProcessingToApproved() {
        requestExisting.setStatus(Request.Status.PROCESSING);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.APPROVED);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.APPROVED), fromServiceRequestDto.getStatus());
    }

    @Test
    void updatePositiveStatusProcessingToCancelled() {
        requestExisting.setStatus(Request.Status.PROCESSING);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.CANCELLED);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.CANCELLED), fromServiceRequestDto.getStatus());
    }

    @Test
    void updatePositiveStatusApprovedToCancelled() {
        requestExisting.setStatus(Request.Status.APPROVED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.CANCELLED);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.CANCELLED), fromServiceRequestDto.getStatus());
    }

    @Test
    void updatePositiveStatusApprovedToPaid() {
        requestExisting.setStatus(Request.Status.APPROVED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PAID);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.PAID);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.PAID), fromServiceRequestDto.getStatus());
    }

    @Test
    void updatePositiveStatusPaidToSatisfied() {
        requestExisting.setStatus(Request.Status.PAID);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.SATISFIED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.SATISFIED);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.SATISFIED), fromServiceRequestDto.getStatus());
    }

    @Test
    void updatePositiveStatusPaidToCancelled() {
        requestExisting.setStatus(Request.Status.PAID);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.CANCELLED);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.CANCELLED), fromServiceRequestDto.getStatus());
    }

    @Test
    void updatePositiveStatusSatisfiedToCancelled() {
        requestExisting.setStatus(Request.Status.SATISFIED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.CANCELLED);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.CANCELLED), fromServiceRequestDto.getStatus());
    }

    @Test
    void updateNegativeCourseWhenStatusApproved() {
        requestExisting.setStatus(Request.Status.APPROVED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestDtoForUpdate.setCourse(courseDtoForUpdate);
        requestForSaving.setStatus(Request.Status.APPROVED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeCourseWhenStatusPaid() {
        requestExisting.setStatus(Request.Status.PAID);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PAID);
        requestDtoForUpdate.setCourse(courseDtoForUpdate);
        requestForSaving.setStatus(Request.Status.PAID);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeCourseWhenStatusSatisfied() {
        requestExisting.setStatus(Request.Status.SATISFIED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestDtoForUpdate.setCourse(courseDtoForUpdate);
        requestForSaving.setStatus(Request.Status.CANCELLED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeCourseWhenStatusCancelled() {
        requestExisting.setStatus(Request.Status.CANCELLED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestDtoForUpdate.setCourse(courseDtoForUpdate);
        requestForSaving.setStatus(Request.Status.CANCELLED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeCourseWhenWrongStatus() {
        requestExisting.setStatus(Request.Status.CANCELLED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PAID);
        requestDtoForUpdate.setCourse(courseDtoForUpdate);
        requestForSaving.setStatus(Request.Status.PAID);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }


    @Test
    void updateNegativeFromStatusProcessingToPaid() {
        requestExisting.setStatus(Request.Status.PROCESSING);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PAID);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.PAID);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeFromStatusProcessingToSatisfied() {
        requestExisting.setStatus(Request.Status.PROCESSING);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.SATISFIED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.SATISFIED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeFromStatusApprovedToSatisfied() {
        requestExisting.setStatus(Request.Status.APPROVED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.SATISFIED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.SATISFIED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeFromStatusPaidToApproved() {
        requestExisting.setStatus(Request.Status.PAID);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.APPROVED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeFromStatusPaidToProcessing() {
        requestExisting.setStatus(Request.Status.PAID);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PROCESSING);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.PROCESSING);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeFromStatusSatisfiedToProcessing() {
        requestExisting.setStatus(Request.Status.SATISFIED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PROCESSING);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.PROCESSING);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeFromStatusSatisfiedToApproved() {
        requestExisting.setStatus(Request.Status.SATISFIED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.APPROVED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeFromStatusCancelledToProcessing() {
        requestExisting.setStatus(Request.Status.CANCELLED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PROCESSING);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.PROCESSING);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeFromStatusCancelledToApproved() {
        requestExisting.setStatus(Request.Status.CANCELLED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.APPROVED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeFromStatusCancelledToPaid() {
        requestExisting.setStatus(Request.Status.CANCELLED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PAID);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.PAID);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    @Test
    void updateNegativeFromStatusCancelledToSatisfied() {
        requestExisting.setStatus(Request.Status.CANCELLED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.SATISFIED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestForSaving.setStatus(Request.Status.SATISFIED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate).getStatus());
    }

    private static void initRequestExisting() {
        requestExisting = new Request();
        requestExisting.setId(EXISTING_REQUEST_ID);
        requestExisting.setUser(user);
        requestExisting.setCourse(courseExisting);
        requestExisting.setStatus(Request.Status.APPROVED);
    }

    private static void initRequestForSaving() {
        requestForSaving = new Request();
        requestForSaving.setId(EXISTING_REQUEST_ID);
        requestForSaving.setUser(user);
        requestForSaving.setCourse(courseExisting);
        requestForSaving.setStatus(Request.Status.APPROVED);
    }

    private static void initRequestDtoForUpdate() {
        requestDtoForUpdate = new RequestDtoForUpdate();
        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestDtoForUpdate.setCourse(courseDtoExisting);
        requestDtoForUpdate.setId(EXISTING_REQUEST_ID);
    }

    private static void initRequestDto() {
        existingDto = new RequestDto();
        existingDto.setStatus(StatusDto.PROCESSING);
        existingDto.setCourse(courseDtoExisting);
        existingDto.setId(EXISTING_REQUEST_ID);
        existingDto.setUser(userDto);
    }

    private static void initCourseDtoForUpdate() {
        courseDtoForUpdate = new CourseDto();
        courseDtoForUpdate.setId(EXISTING_COURSE_ID);
        courseDtoForUpdate.setTitle(FOR_UPDATE_COURSE);
    }

    private static void initCourseDtoExisting() {
        courseDtoExisting = new CourseDto();
        courseDtoExisting.setId(EXISTING_COURSE_ID);
        courseDtoExisting.setTitle(EXISTING_COURSE_STRING);
    }

    private static void initCourseForUpdate() {
        courseForUpdate = new Course();
        RequestServiceImplTest.courseForUpdate.setId(EXISTING_COURSE_ID);
        RequestServiceImplTest.courseForUpdate.setTitle(FOR_UPDATE_COURSE);
    }

    private static void initCourseExisting() {
        courseExisting = new Course();
        courseExisting.setId(EXISTING_COURSE_ID);
        courseExisting.setTitle(EXISTING_COURSE_STRING);
    }

    private static void initUserDto() {
        userDto = new UserDto();
        userDto.setId(EXISTING_USER_ID);
        userDto.setFirstName("Test");
        userDto.setLastName("Test");
        userDto.setPatronymicName("Test");
        userDto.setEmail("test@test.com");
        userDto.setPassword("Test");
        userDto.setContactPreferences(ContactPreferencesDto.CELLPHONE);
        userDto.setSocialMedia("Test");
        userDto.setRole(RoleDto.STUDENT);
        userDto.setActive(true);
    }

    private static void initUser() {
        user = new User();
        user.setId(EXISTING_USER_ID);
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPatronymicName("Test");
        user.setEmail("test@test.com");
        user.setPassword("Test");
        user.setContactPreferences(User.ContactPreferences.CELLPHONE);
        user.setSocialMedia("Test");
        user.setRole(User.Role.STUDENT);
        user.setActive(true);
    }
}