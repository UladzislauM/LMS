package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSave;
import academy.belhard.lms.service.dto.user.UserDtoForUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto create(UserDtoForSave dto);

    Page<UserDto> getAll(Pageable pageable);

    UserDto getById(Long id);

    UserDto update(UserDtoForUpdate dto);

    void delete(Long id);
}