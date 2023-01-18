package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSave;
import academy.belhard.lms.service.dto.user.UserDtoForUpdate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoForSavingToUser(UserDtoForSave userDtoForSaving);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    User userDtoForUpdatingToUser(UserDtoForUpdate userDtoForUpdating);
}
