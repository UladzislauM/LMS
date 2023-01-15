package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.UserRepository;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSaving;
import academy.belhard.lms.service.dto.user.UserDtoForUpdating;
import academy.belhard.lms.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserServiceImplTest {
    private static UserService userService;
    private static UserRepository userRepository;

    private static User user;
    private static UserDto userDto;
    private static UserDtoForSaving userDtoForSaving;
    private static UserDtoForUpdating userDtoForUpdating;

    @BeforeAll
    static void beforeAll() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository, new UserMapper);
    }

    @Test
    void createUser() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}