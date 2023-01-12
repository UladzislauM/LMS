package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.RequestRepository;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.request.CourseDto;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import academy.belhard.lms.service.dto.request.StatusDto;
import academy.belhard.lms.service.dto.user.ContactPreferencesDto;
import academy.belhard.lms.service.dto.user.RoleDto;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.mapper.RequestMapperImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestServiceImplTest {
    private static RequestService requestService;
    private static RequestRepository requestRepositoryMock;
    private static UserService userServiceMock;
    private static User user;
    private static UserDto userDto;

    private static RequestDto existingDto;

    private static RequestDtoForUpdate requestDtoForUpdate;

    private static Course course;

    private static CourseDto courseDto;

    private static Request requestToRepositorySave;

    private static Request requestExisting;


    @BeforeAll
    static void beforeAll() {
        requestRepositoryMock = Mockito.mock(RequestRepository.class);
        userServiceMock = Mockito.mock(UserService.class);
        requestService = new RequestServiceImpl(requestRepositoryMock, userServiceMock, new RequestMapperImpl());

        user = new User();
        user.setId(1L);
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPatronymicName("Test");
        user.setEmail("test@test.com");
        user.setPassword("Test");
        user.setContactPreferences(User.ContactPreferences.CELLPHONE);
        user.setSocialMedia("Test");
        user.setRole(User.Role.STUDENT);
        user.setActive(true);

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setFirstName("Test");
        userDto.setLastName("Test");
        userDto.setPatronymicName("Test");
        userDto.setEmail("test@test.com");
        userDto.setPassword("Test");
        userDto.setContactPreferences(ContactPreferencesDto.CELLPHONE);
        userDto.setSocialMedia("Test");
        userDto.setRole(RoleDto.STUDENT);
        userDto.setActive(true);

        course = new Course();
        course.setId(1L);
        course.setTitle("course_1");

        courseDto = new CourseDto();
        courseDto.setId(1L);
        courseDto.setTitle("course_1");

        existingDto = new RequestDto();
        existingDto.setStatus(StatusDto.PROCESSING);
        existingDto.setCourse(courseDto);
        existingDto.setId(1L);
        existingDto.setUser(userDto);

        requestDtoForUpdate = new RequestDtoForUpdate();
        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestDtoForUpdate.setCourse(courseDto);
        requestDtoForUpdate.setId(1L);

        requestToRepositorySave = new Request();
        requestToRepositorySave.setId(1L);
        requestToRepositorySave.setUser(user);
        requestToRepositorySave.setCourse(course);
        requestToRepositorySave.setStatus(Request.Status.APPROVED);

        requestExisting = new Request();
        requestExisting.setId(1L);
        requestExisting.setUser(user);
        requestExisting.setCourse(course);
        requestExisting.setStatus(Request.Status.PROCESSING);
    }

    @Test
    void updatePositive() {
        RequestDto fromService;

        requestExisting.setStatus(Request.Status.PROCESSING);

        Optional<Request> optional = Optional.of(requestExisting);
        Mockito.when(requestRepositoryMock.findById(1L)).thenReturn(optional);
        Mockito.when(userServiceMock.getUserById(1L)).thenReturn(userDto);
        Mockito.when(requestRepositoryMock.save(requestToRepositorySave)).thenReturn(requestToRepositorySave);

        requestDtoForUpdate.setStatus(StatusDto.APPROVED);
        requestToRepositorySave.setStatus(Request.Status.APPROVED);
        fromService = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.APPROVED), fromService.getStatus());

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestToRepositorySave.setStatus(Request.Status.CANCELLED);
        fromService = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.CANCELLED), fromService.getStatus());

        requestExisting.setStatus(Request.Status.APPROVED);

        requestDtoForUpdate.setStatus(StatusDto.PROCESSING);
        requestToRepositorySave.setStatus(Request.Status.PROCESSING);
        fromService = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.PROCESSING), fromService.getStatus());

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestToRepositorySave.setStatus(Request.Status.CANCELLED);
        fromService = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.CANCELLED), fromService.getStatus());

        requestDtoForUpdate.setStatus(StatusDto.PAID);
        requestToRepositorySave.setStatus(Request.Status.PAID);
        fromService = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.PAID), fromService.getStatus());

        requestExisting.setStatus(Request.Status.PAID);

        requestDtoForUpdate.setStatus(StatusDto.SATISFIED);
        requestToRepositorySave.setStatus(Request.Status.SATISFIED);
        fromService = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.SATISFIED), fromService.getStatus());

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestToRepositorySave.setStatus(Request.Status.CANCELLED);
        fromService = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.CANCELLED), fromService.getStatus());

        requestExisting.setStatus(Request.Status.SATISFIED);

        requestDtoForUpdate.setStatus(StatusDto.CANCELLED);
        requestToRepositorySave.setStatus(Request.Status.CANCELLED);
        fromService = requestService.update(requestDtoForUpdate);
        assertEquals((StatusDto.CANCELLED), fromService.getStatus());
    }
}