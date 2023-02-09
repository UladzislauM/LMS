package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSave;
import academy.belhard.lms.service.dto.user.UserDtoForUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto create(UserDtoForSave dto);

    Page<UserDto> getAll(Pageable pageable);

    Page<UserDto> getAllTrainers(Pageable pageable);

    UserDto getById(Long id);

    UserDto getByEmail(String email);

    UserDto update(UserDtoForUpdate dto);

    void delete(Long id);

    Page<UserDto> getAllByCourseAndRequestStatus(Long id, Pageable pageable);

    void registerUser(UserDtoForSave dto);

    void activateUser(Long userId);

    void recoveryPassword(String email);

    void changePassword(Long userId, String newPassword);

    void updatePassword(Long userId, String oldPassword, String newPassword);

    void updatePasswordManager(Long userId, String newPassword);
}
