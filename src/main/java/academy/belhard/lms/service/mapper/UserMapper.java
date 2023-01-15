package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSaving;
import academy.belhard.lms.service.dto.user.UserDtoForUpdating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoForSavingToUser(UserDtoForSaving userDtoForSaving);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    User userDtoForUpdatingToUser(UserDtoForUpdating userDtoForUpdating);
}
