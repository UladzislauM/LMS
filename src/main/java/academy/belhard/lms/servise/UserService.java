package academy.belhard.lms.servise;

import academy.belhard.lms.dto.UserDtoForSaving;
import academy.belhard.lms.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(UserDtoForSaving userDtoForSaving);
}
