package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSaving;
import academy.belhard.lms.service.dto.user.UserDtoForUpdating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract User userDtoForSavingToUser(UserDtoForSaving userDtoForSaving);

    public abstract UserDto userToUserDto(User user);

    public abstract User userDtoToUser(UserDto userDto);

    public abstract User userDtoForUpdatingToUser(UserDtoForUpdating userDtoForUpdating);
}
