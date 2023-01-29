package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.CourseRepository;
import academy.belhard.lms.data.repository.RequestRepository;
import academy.belhard.lms.data.repository.UserRepository;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForSave;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import academy.belhard.lms.service.dto.request.StatusDto;
import academy.belhard.lms.service.dto.user.ContactPreferencesDto;
import academy.belhard.lms.service.dto.user.RoleDto;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.exception.LmsException;
import academy.belhard.lms.service.mapper.RequestMapperImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestServiceImplTest {
    public static final int MAX_USERS_IN_GET_ALL_TEST = 4;
    public static final long COURSE_ID = 1L;
    public static final long COURSE_ID_DTO = 1L;
    public static final long REQUEST_ID = 1L;
    public static final long ID_FOR_GET_BY_ID = 1L;
    public static final long ID_TRAINER = 2L;
    private static final Long REQUEST_ID_DTO = 1L;
    public static final int PAGE_SIZE = 1;
    public static final String COURSE_TITLE = "course_existing";
    public static final String COURSE_TITLE_DTO = "course_existing";
    public static final long USER_ID = 1L;
    public static final String USER_FIRST_NAME = "Test";
    public static final String USER_LAST_NAME = "Test";
    public static final String USER_PATRONYMIC_NAME = "Test";
    public static final String USER_EMAIL = "test@test.com";
    public static final String USER_PASSWORD = "Test";
    public static final String USER_SOCIAL_MEDIA = "Test";
    public static final boolean USER_IS_ACTIVE = true;
    public static final User.Role USER_ROLE = User.Role.STUDENT;
    public static final User.ContactPreferences USER_CONTACT_PREFERENCES = User.ContactPreferences.CELLPHONE;
    private static final Long USER_ID_DTO = 1L;
    private static final String USER_FIRST_NAME_DTO = "Test";
    private static final String USER_LAST_NAME_DTO = "Test";
    private static final String USER_PATRONYMIC_NAME_DTO = "Test";
    private static final String USER_EMAIL_DTO = "test@test.com";
    private static final String USER_PASSWORD_DTO = "Test";
    private static final String USER_SOCIAL_MEDIA_DTO = "Test";
    public static final RoleDto USER_ROLE_DTO = RoleDto.STUDENT;
    private static final boolean USER_IS_ACTIVE_DTO = true;
    public static final ContactPreferencesDto USER_CONTACT_PREFERENCES_DTO = ContactPreferencesDto.CELLPHONE;
    public static final Request.Status REQUEST_STATUS = Request.Status.APPROVED;
    private static final StatusDto REQUEST_STATUS_DTO = StatusDto.PROCESSING;
    public static final String COURSE_TITLE_UPDATE = "course_new";
    private static final String COURSE_TITLE_UPDATE_DTO = "course_new";
    private static final Request.Status REQUEST_STATUS_EXISTING = Request.Status.APPROVED;
    private static final Request.Status REQUEST_STATUS_EXISTING_CREATE = Request.Status.PROCESSING;
    private static final StatusDto REQUEST_STATUS_DTO_UPDATE = StatusDto.APPROVED;
    private static RequestService requestService;
    @Mock
    private static RequestRepository requestRepositoryMock;
    @Mock
    private static UserRepository userRepositoryMock;
    @Mock
    private static CourseRepository courseRepositoryMock;
    private static User user;

    private static User trainer;
    private static UserDto userDto;

    private static UserDto trainerDto;

    private static RequestDto requestExistingDto;

    private static RequestDtoForUpdate requestDtoForUpdate;

    private static Course courseExisting;

    private static Course courseForUpdate;

    private static CourseDto courseExistingDto;

    private static CourseDto courseDtoForUpdate;

    private static Request requestForSaving;

    private static Request requestExisting;

    private static RequestDto fromServiceRequestDto;

    private static RequestDtoForSave requestDtoForSaving;

    @BeforeAll
    static void beforeAll() {
        requestRepositoryMock = mock(RequestRepository.class);
        userRepositoryMock = mock(UserRepository.class);
        courseRepositoryMock = mock(CourseRepository.class);
    }

    @BeforeEach
    void beforeEach() {
        requestService = new RequestServiceImpl(requestRepositoryMock, userRepositoryMock, courseRepositoryMock, new RequestMapperImpl());
        initTrainer();

        initTrainerDto();

        initUser(USER_ID,
                USER_FIRST_NAME,
                USER_LAST_NAME,
                USER_PATRONYMIC_NAME,
                USER_EMAIL,
                USER_PASSWORD,
                USER_CONTACT_PREFERENCES,
                USER_SOCIAL_MEDIA,
                USER_ROLE,
                USER_IS_ACTIVE);
        initUserDto(USER_ID_DTO,
                USER_FIRST_NAME_DTO,
                USER_LAST_NAME_DTO,
                USER_PATRONYMIC_NAME_DTO,
                USER_EMAIL_DTO,
                USER_PASSWORD_DTO,
                USER_CONTACT_PREFERENCES_DTO,
                USER_SOCIAL_MEDIA_DTO,
                USER_ROLE_DTO,
                USER_IS_ACTIVE_DTO);
        initCourseExisting(COURSE_ID,
                COURSE_TITLE);
        initCourseForUpdate();
        initCourseDtoExisting(COURSE_ID_DTO,
                COURSE_TITLE_DTO);
        initCourseDtoForUpdate(COURSE_ID_DTO,
                COURSE_TITLE_UPDATE_DTO);
        initRequestDto(REQUEST_ID_DTO, userDto, courseExistingDto, REQUEST_STATUS_DTO);
        initRequestDtoForUpdate(REQUEST_ID_DTO, courseExistingDto, REQUEST_STATUS_DTO_UPDATE);
        initRequestDtoForSaving();
        initRequestExisting(REQUEST_ID, user, courseExisting, REQUEST_STATUS_EXISTING);
        initRequestForSaving(REQUEST_ID, user, courseExisting, REQUEST_STATUS);

        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));

        when(requestRepositoryMock.findById(requestExisting.getId())).thenReturn(Optional.of(requestExisting));

        when(courseRepositoryMock.findById(courseExisting.getId())).thenReturn(Optional.of(courseExisting));

        when(requestRepositoryMock.save(any())).thenReturn(requestForSaving);
    }

    @Test
    void GetAllPositiveTest() {
        List<Request> list = new ArrayList<>();
        setList(list);
        Pageable pageable = PageRequest.ofSize(PAGE_SIZE);
        Page<Request> requestPage = new PageImpl<>(list);
        when(requestRepositoryMock.findAll(pageable)).thenReturn(requestPage);
        List<RequestDto> listDto = new ArrayList<>();
        setListDto(listDto);
        Page<RequestDto> incomingRequest = new PageImpl<>(listDto);
        Page<RequestDto> requestFromService = requestService.getAll(pageable);
        assertEquals(requestFromService.toString(), incomingRequest.toString());
    }

    @Test
    void GetAllNegativeTest() {
        List<Request> list = new ArrayList<>();
        Pageable pageable = PageRequest.ofSize(PAGE_SIZE);
        Page<Request> requestPage = new PageImpl<>(list);
        when(requestRepositoryMock.findAll(pageable)).thenReturn(requestPage);
        Page<RequestDto> requestFromService = requestService.getAll(pageable);
        assertNotNull(requestFromService);
    }

    @Test
    void GetByIdTest() {
        initRequestExisting(REQUEST_ID, user, courseExisting, REQUEST_STATUS_EXISTING_CREATE);
        when(requestRepositoryMock.findById(any())).thenReturn(Optional.of(requestExisting));
        RequestDto incomingRequest = requestService.getById(ID_FOR_GET_BY_ID);
        RequestDto result = requestExistingDto;
        assertEquals(result, incomingRequest);
    }

    @Test
    void create() {
        requestExisting.setStatus(Request.Status.PROCESSING);
        requestForSaving.setStatus(Request.Status.PROCESSING);
        requestForSaving.setStatus(Request.Status.PROCESSING);

        RequestDto incomingRequest = requestService.create(requestDtoForSaving);

        assertEquals(requestDtoForSaving.getUser(), incomingRequest.getUser());
        assertEquals(requestDtoForSaving.getCourse(), incomingRequest.getCourse());
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
        assertEquals(courseDtoForUpdate.getTitle(),
                fromServiceRequestDto.getCourse().getTitle());
    }

    @Test
    void updatePositiveStatusProcessingToApproved() {
        requestExisting.setStatus(Request.Status.PROCESSING);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.APPROVED);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);

        assertEquals(StatusDto.APPROVED, fromServiceRequestDto.getStatus());
    }

    @Test
    void updatePositiveStatusProcessingToCancelled() {
        requestExisting.setStatus(Request.Status.PROCESSING);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.CANCELLED);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals(StatusDto.CANCELLED, fromServiceRequestDto.getStatus());
    }

    @Test
    void updatePositiveStatusApprovedToCancelled() {
        requestExisting.setStatus(Request.Status.APPROVED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.CANCELLED);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals(StatusDto.CANCELLED, fromServiceRequestDto.getStatus());
    }

    @Test
    void updatePositiveStatusApprovedToPaid() {
        requestExisting.setStatus(Request.Status.APPROVED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PAID);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.PAID);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals(StatusDto.PAID, fromServiceRequestDto.getStatus());
    }

    @Test
    void updatePositiveStatusPaidToSatisfied() {
        requestExisting.setStatus(Request.Status.PAID);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.SATISFIED);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.SATISFIED);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals(StatusDto.SATISFIED, fromServiceRequestDto.getStatus());
    }

    @Test
    void updatePositiveStatusPaidToCancelled() {
        requestExisting.setStatus(Request.Status.PAID);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.CANCELLED);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals(StatusDto.CANCELLED, fromServiceRequestDto.getStatus());
    }

    @Test
    void updatePositiveStatusSatisfiedToCancelled() {
        requestExisting.setStatus(Request.Status.SATISFIED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.CANCELLED);
        fromServiceRequestDto = requestService.update(requestDtoForUpdate);
        assertEquals(StatusDto.CANCELLED, fromServiceRequestDto.getStatus());
    }

    @Test
    void updateNegativeCourseWhenStatusApproved() {
        requestExisting.setStatus(Request.Status.APPROVED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestDtoForUpdate.setCourse(courseDtoForUpdate);
        requestForSaving.setStatus(Request.Status.APPROVED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeCourseWhenStatusPaid() {
        requestExisting.setStatus(Request.Status.PAID);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PAID);
        requestDtoForUpdate.setCourse(courseDtoForUpdate);
        requestForSaving.setStatus(Request.Status.PAID);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeCourseWhenStatusSatisfied() {
        requestExisting.setStatus(Request.Status.SATISFIED);
        requestExisting.setCourse(courseExisting);
        requestForSaving.setCourse(courseForUpdate);

        when(courseRepositoryMock.findById(courseForUpdate.getId())).thenReturn(Optional.of(courseForUpdate));

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestDtoForUpdate.setCourse(courseDtoForUpdate);
        requestForSaving.setStatus(Request.Status.CANCELLED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeCourseWhenStatusCancelled() {
        requestExisting.setStatus(Request.Status.CANCELLED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestDtoForUpdate.setCourse(courseDtoForUpdate);
        requestForSaving.setStatus(Request.Status.CANCELLED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeCourseWhenWrongStatus() {
        requestExisting.setStatus(Request.Status.CANCELLED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PAID);
        requestDtoForUpdate.setCourse(courseDtoForUpdate);
        requestForSaving.setStatus(Request.Status.PAID);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }


    @Test
    void updateNegativeFromStatusProcessingToPaid() {
        requestExisting.setStatus(Request.Status.PROCESSING);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PAID);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.PAID);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeFromStatusProcessingToSatisfied() {
        requestExisting.setStatus(Request.Status.PROCESSING);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.SATISFIED);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.SATISFIED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeFromStatusApprovedToSatisfied() {
        requestExisting.setStatus(Request.Status.APPROVED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.SATISFIED);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.SATISFIED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeFromStatusPaidToApproved() {
        requestExisting.setStatus(Request.Status.PAID);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.APPROVED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeFromStatusPaidToProcessing() {
        requestExisting.setStatus(Request.Status.PAID);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PROCESSING);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.PROCESSING);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeFromStatusSatisfiedToProcessing() {
        requestExisting.setStatus(Request.Status.SATISFIED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PROCESSING);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.PROCESSING);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeFromStatusSatisfiedToApproved() {
        requestExisting.setStatus(Request.Status.SATISFIED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.APPROVED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeFromStatusCancelledToProcessing() {
        requestExisting.setStatus(Request.Status.CANCELLED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PROCESSING);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.PROCESSING);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeFromStatusCancelledToApproved() {
        requestExisting.setStatus(Request.Status.CANCELLED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.APPROVED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeFromStatusCancelledToPaid() {
        requestExisting.setStatus(Request.Status.CANCELLED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.PAID);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.PAID);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    @Test
    void updateNegativeFromStatusCancelledToSatisfied() {
        requestExisting.setStatus(Request.Status.CANCELLED);
        requestExisting.setCourse(courseExisting);

        requestDtoForUpdate.setStatus(StatusDto.SATISFIED);
        requestDtoForUpdate.setCourse(courseExistingDto);
        requestForSaving.setStatus(Request.Status.SATISFIED);
        assertThrows(LmsException.class, () -> requestService.update(requestDtoForUpdate));
    }

    private static void initRequestDtoForSaving() {
        requestDtoForSaving = new RequestDtoForSave();
        requestDtoForSaving.setCourse(courseExistingDto);
        requestDtoForSaving.setUser(userDto);
    }

    private static void setList(List<Request> list) {
        for (Long i = 1L; i < MAX_USERS_IN_GET_ALL_TEST + 1; i++) {
            Request request = new Request();
            request.setId(i);
            request.setStatus(Request.Status.PROCESSING);
            initCourseExisting(i, "Course_test_" + i);
            request.setCourse(courseExisting);
            initUser(i,
                    USER_FIRST_NAME,
                    USER_LAST_NAME,
                    USER_PATRONYMIC_NAME,
                    "USER_EMAIL" + i,
                    USER_PASSWORD,
                    USER_CONTACT_PREFERENCES,
                    USER_SOCIAL_MEDIA,
                    USER_ROLE,
                    USER_IS_ACTIVE);
            request.setUser(user);
            list.add(request);
        }
    }

    private static void setListDto(List<RequestDto> list) {
        for (Long i = 1L; i < MAX_USERS_IN_GET_ALL_TEST + 1; i++) {
            RequestDto request = new RequestDto();
            request.setId(i);
            request.setStatus(StatusDto.PROCESSING);
            initCourseDtoExisting(i, "Course_test_" + i);
            request.setCourse(courseExistingDto);
            initUserDto(i,
                    USER_FIRST_NAME,
                    USER_LAST_NAME,
                    USER_PATRONYMIC_NAME,
                    "USER_EMAIL" + i,
                    USER_PASSWORD,
                    USER_CONTACT_PREFERENCES_DTO,
                    USER_SOCIAL_MEDIA,
                    USER_ROLE_DTO,
                    USER_IS_ACTIVE);
            request.setUser(userDto);
            list.add(request);
        }
    }

    private static void initRequestExisting(Long id, User user, Course course, Request.Status status) {
        requestExisting = new Request();
        requestExisting.setId(id);
        requestExisting.setUser(user);
        requestExisting.setCourse(course);
        requestExisting.setStatus(status);
    }

    private static void initRequestForSaving(Long id, User user, Course course, Request.Status status) {
        requestForSaving = new Request();
        requestForSaving.setId(id);
        requestForSaving.setUser(user);
        requestForSaving.setCourse(course);
        requestForSaving.setStatus(status);
    }

    private static void initRequestDtoForUpdate(Long id, CourseDto course, StatusDto status) {
        requestDtoForUpdate = new RequestDtoForUpdate();
        requestDtoForUpdate.setId(id);
        requestDtoForUpdate.setCourse(course);
        requestDtoForUpdate.setStatus(status);
    }

    private static void initRequestDto(Long id, UserDto user, CourseDto course, StatusDto status) {
        requestExistingDto = new RequestDto();
        requestExistingDto.setId(id);
        requestExistingDto.setUser(user);
        requestExistingDto.setCourse(course);
        requestExistingDto.setStatus(status);
    }

    private static void initCourseDtoForUpdate(Long id, String title) {
        courseDtoForUpdate = new CourseDto();
        courseDtoForUpdate.setId(id);
        courseDtoForUpdate.setTitle(title);

    }

    private static void initCourseDtoExisting(Long id, String title) {
        courseExistingDto = new CourseDto();
        courseExistingDto.setId(id);
        courseExistingDto.setTitle(title);
        courseExistingDto.setTrainer(trainerDto);
        courseExistingDto.setPrice(null);
        courseExistingDto.setStartDate(null);
        courseExistingDto.setDescription(null);
        courseExistingDto.setLessons(null);
    }

    private static void initCourseForUpdate() {
        courseForUpdate = new Course();
        courseForUpdate.setId(COURSE_ID);
        courseForUpdate.setTitle(COURSE_TITLE_UPDATE);
        courseForUpdate.setTrainer(trainer);
        courseForUpdate.setLessons(null);
        courseForUpdate.setPrice(null);
        courseForUpdate.setDescription(null);
        courseForUpdate.setStartDate(null);
        courseForUpdate.setDeleted(false);
    }

    private static void initCourseExisting(Long id,
                                           String title) {
        courseExisting = new Course();
        courseExisting.setId(id);
        courseExisting.setTitle(COURSE_TITLE);
        courseExisting.setTrainer(trainer);
        courseExisting.setLessons(null);
        courseExisting.setPrice(null);
        courseExisting.setDescription(null);
        courseExisting.setStartDate(null);
        courseExisting.setDeleted(false);
    }

    private static void initUserDto(Long id,
                                    String firstName,
                                    String lastName,
                                    String patronymicName,
                                    String email,
                                    String password,
                                    ContactPreferencesDto contactPreferences,
                                    String socialMedia,
                                    RoleDto role,
                                    boolean isActive) {
        userDto = new UserDto();
        userDto.setId(id);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setPatronymicName(patronymicName);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setContactPreferences(contactPreferences);
        userDto.setSocialMedia(socialMedia);
        userDto.setRole(role);
        userDto.setActive(isActive);
    }

    private static void initUser(Long id,
                                 String firstName,
                                 String lastName,
                                 String patronymicName,
                                 String email,
                                 String password,
                                 User.ContactPreferences contactPreferences,
                                 String socialMedia,
                                 User.Role role,
                                 boolean isActive) {
        user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPatronymicName(patronymicName);
        user.setEmail(email);
        user.setPassword(password);
        user.setContactPreferences(contactPreferences);
        user.setSocialMedia(socialMedia);
        user.setRole(role);
        user.setActive(isActive);
    }

    private static void initTrainer() {
        trainer = new User();
        trainer.setId(ID_TRAINER);
        trainer.setFirstName(USER_FIRST_NAME + "Tr");
        trainer.setLastName(USER_LAST_NAME + "Tr");
        trainer.setPatronymicName(USER_PATRONYMIC_NAME + "Tr");
        trainer.setEmail(USER_EMAIL + "Tr");
        trainer.setPassword(USER_PASSWORD + "Tr");
        trainer.setContactPreferences(User.ContactPreferences.TELEGRAM);
        trainer.setSocialMedia(USER_SOCIAL_MEDIA);
        trainer.setRole(User.Role.TRAINER);
        trainer.setActive(true);
    }

    private static void initTrainerDto() {
        trainerDto = new UserDto();
        trainerDto.setId(ID_TRAINER);
        trainerDto.setFirstName(USER_FIRST_NAME_DTO + "Tr");
        trainerDto.setLastName(USER_LAST_NAME_DTO + "Tr");
        trainerDto.setPatronymicName(USER_PATRONYMIC_NAME_DTO + "Tr");
        trainerDto.setEmail(USER_EMAIL_DTO + "Tr");
        trainerDto.setPassword(USER_PASSWORD_DTO + "Tr");
        trainerDto.setContactPreferences(ContactPreferencesDto.TELEGRAM);
        trainerDto.setSocialMedia(USER_SOCIAL_MEDIA_DTO);
        trainerDto.setRole(RoleDto.TRAINER);
        trainerDto.setActive(true);
    }
}