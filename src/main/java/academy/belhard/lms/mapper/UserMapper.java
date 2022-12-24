package academy.belhard.lms.mapper;

import academy.belhard.lms.dto.UserDtoForSaving;
import academy.belhard.lms.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract User userDtoForSavingToUser(UserDtoForSaving userDtoForSaving);
}
