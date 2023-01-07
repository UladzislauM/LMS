package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSave;
import academy.belhard.lms.service.dto.user.UserDtoForUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto createUser(UserDtoForSave dto);

    Page<UserDto> getAllUsers(Pageable pageable);

    UserDto getUserById(Long id);

    UserDto updateUser(UserDtoForUpdate dto);

    void deleteUser(Long id);
}
