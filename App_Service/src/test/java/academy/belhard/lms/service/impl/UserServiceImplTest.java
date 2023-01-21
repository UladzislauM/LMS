package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.UserRepository;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.user.ContactPreferencesDto;
import academy.belhard.lms.service.dto.user.RoleDto;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSave;
import academy.belhard.lms.service.exception.NotFoundException;
import academy.belhard.lms.service.mapper.UserMapperImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    public static final long ID_EXISTING = 1L;
    public static final long ID_NOT_EXISTING = 988L;
    public static final long ID_ZERO = 0L;
    public static final long ID_NEGATIVE = -1L;
    private static UserService userService;
    private static UserRepository userRepository;
    private static User existing;
    private static UserDto existingDto;
    private static UserDtoForSave userDtoForSave;

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
        existing.setPassword("12345");
        existing.setPatronymicName("Ivanovich");
        existing.setSocialMedia("Telegram");

        existingDto = new UserDto();
        existingDto.setId(ID_EXISTING);
        existingDto.setEmail("test1@mail.ru");
        existingDto.setRole(RoleDto.STUDENT);
        existingDto.setFirstName("IvanTest");
        existingDto.setLastName("TestIvan");
        existingDto.setActive(true);
        existingDto.setPassword("12345");
        existingDto.setPatronymicName("Ivanovich");
        existingDto.setSocialMedia("Telegram");

        userDtoForSave = new UserDtoForSave();
        userDtoForSave.setEmail("test1@mail.ru");
        userDtoForSave.setFirstName("IvanTest");
        userDtoForSave.setLastName("TestIvan");
        userDtoForSave.setPassword("12345");
        userDtoForSave.setPatronymicName("Ivanovich");
        userDtoForSave.setContactPreferences(ContactPreferencesDto.INSTAGRAM);
        userDtoForSave.setSocialMedia("Telegram");
    }

    @BeforeEach
    void setUp() {
        reset(userRepository);
    }

    @Test
    void getAllPositiveTest() {
        List<User> userList = new ArrayList<>();
        setList(userList);
        Pageable pageable = PageRequest.ofSize(1);
        Page<User> userPage = new PageImpl<>(userList);
        when(userRepository.findAll(pageable)).thenReturn(userPage);

        List<UserDto> listDto = new ArrayList<>();
        setListDto(listDto);
        Page<UserDto> incomingUser = new PageImpl<>(listDto);
        Page<UserDto> fromService = userService.getAll(pageable);

        assertEquals(incomingUser, fromService);
    }

    private static void setList(List<User> userList) {
        for (long i = 1; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setEmail("fhasjfh@mnzvn.com" + i);
            user.setPassword("hsfadh");
            user.setFirstName("Sanya");
            user.setLastName("Sanich");
            user.setRole(User.Role.STUDENT);
            user.setActive(true);
            user.setPatronymicName("Sanek");
            user.setContactPreferences(User.ContactPreferences.TELEGRAM);
            user.setSocialMedia("Instagramm");
            userList.add(user);
        }
    }

    private static void setListDto(List<UserDto> userList) {
        for (long i = 1; i < 10; i++) {
            UserDto userDto = new UserDto();
            userDto.setId(i);
            userDto.setEmail("fhasjfh@mnzvn.com" + i);
            userDto.setPassword("hsfadh");
            userDto.setFirstName("Sanya");
            userDto.setLastName("Sanich");
            userDto.setRole(RoleDto.STUDENT);
            userDto.setActive(true);
            userDto.setPatronymicName("Sanek");
            userDto.setContactPreferences(ContactPreferencesDto.TELEGRAM);
            userDto.setSocialMedia("Instagramm");
            userList.add(userDto);
        }
    }

    @Test
    void getUserByIdExistingTest() {
        when(userRepository.findById(ID_EXISTING)).thenReturn(Optional.of(existing));

        UserDto fromService = userService.getById(ID_EXISTING);

        assertEquals(existingDto, fromService);
    }

    @Test
    void getUserByIdNotExistingTest() {
        when(userRepository.findById(ID_NOT_EXISTING)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getById(ID_NOT_EXISTING));
    }

    @Test
    void getUserByIdZeroTest() {
        when(userRepository.findById(ID_ZERO)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getById(ID_ZERO));
    }

    @Test
    void getUserByIdNegativeTest() {
        when(userRepository.findById(ID_NEGATIVE)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getById(ID_NEGATIVE));
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUserExistingTest() {
        when(userRepository.findById(ID_EXISTING)).thenReturn(Optional.of(existing));

        userService.delete(ID_EXISTING);

        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void deleteUserNotExistingTest() {
        when(userRepository.findById(ID_NOT_EXISTING)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.delete(ID_NOT_EXISTING));

    }

    @Test
    void deleteUserZeroTest() {
        when(userRepository.findById(ID_ZERO)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.delete(ID_ZERO));

    }

    @Test
    void deleteUserNegativeTest() {
        when(userRepository.findById(ID_NEGATIVE)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.delete(ID_NEGATIVE));

    }
}