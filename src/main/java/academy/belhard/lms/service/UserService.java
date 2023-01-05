package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSaving;
import academy.belhard.lms.service.dto.user.UserDtoForUpdating;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDtoForSaving userDtoForSaving);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto updateUser(UserDtoForUpdating userDtoForUpdating);

    void deleteUser(Long id);
}
