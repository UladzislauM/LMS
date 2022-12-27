package academy.belhard.lms.mapper;

import academy.belhard.lms.dto.UserDto;
import academy.belhard.lms.dto.UserDtoForSaving;
import academy.belhard.lms.dto.UserDtoForUpdating;
import academy.belhard.lms.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract User userDtoForSavingToUser(UserDtoForSaving userDtoForSaving);

    public abstract UserDto userToUserDto(User user);

    public abstract User userDtoToUser(UserDto userDto);

    public abstract User userDtoForUpdatingToUser(UserDtoForUpdating userDtoForUpdating);
}
