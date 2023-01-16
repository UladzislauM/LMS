package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.UserRepository;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.user.RoleDto;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.exception.NotFoundException;
import academy.belhard.lms.service.mapper.UserMapperImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    public static final long ID_EXISTING = 1L;
    public static final long ID_NOT_EXISTING = 988L;
    private static UserService userService;
    private static UserRepository userRepository;
    private static User existing;
    private static UserDto existingDto;

    @BeforeAll
    static void beforeAll() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository, new UserMapperImpl());
        existing = new User();
        existing.setId(ID_EXISTING);
        existing.setEmail("test1@mail.ru");
        existing.setRole(User.Role.STUDENT);
        existing.setFirstName("IvanTest");
        existing.setLastName("TestIvan");
        existing.setActive(true);

        existingDto = new UserDto();
        existingDto.setId(ID_EXISTING);
        existingDto.setEmail("test1@mail.ru");
        existingDto.setRole(RoleDto.STUDENT);
        existingDto.setFirstName("IvanTest");
        existingDto.setLastName("TestIvan");
        existingDto.setActive(true);
    }

    @Test
    void createUser() {
    }

    @Test
    void getAllUsers() {
    }

    @BeforeEach
    void setUp() {
        reset(userRepository);
    }

    @Test
    void getUserByIdExistingTest() {
        when(userRepository.findById(ID_EXISTING)).thenReturn(Optional.of(existing));

        UserDto fromService = userService.getUserById(ID_EXISTING);

        assertEquals(existingDto, fromService);
    }

    @Test
    void getUserByIdNotExistingTest() {
        when(userRepository.findById(ID_NOT_EXISTING)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getUserById(ID_NOT_EXISTING));
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserExistingTest() {
        when(userRepository.findById(ID_EXISTING)).thenReturn(Optional.of(existing));

        userService.deleteUser(ID_EXISTING);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void deleteUserNotExistingTest() {
        when(userRepository.findById(ID_NOT_EXISTING)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.deleteUser(ID_NOT_EXISTING));

    }
}