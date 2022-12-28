package academy.belhard.lms.servise;

import academy.belhard.lms.dto.UserDto;
import academy.belhard.lms.dto.UserDtoForSaving;
import academy.belhard.lms.dto.UserDtoForUpdating;
import academy.belhard.lms.model.User;

import java.util.List;
//import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {     //extends UserDetailsService
    User createUser(UserDtoForSaving userDtoForSaving);

    List<UserDto> findAllUsers();

    UserDto findUserById(Long id);

    User updateUser(Long id, UserDtoForUpdating userDtoForUpdating);

    User deleteUser(Long id);
}
