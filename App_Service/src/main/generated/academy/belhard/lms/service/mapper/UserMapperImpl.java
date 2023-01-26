package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.service.dto.user.ContactPreferencesDto;
import academy.belhard.lms.service.dto.user.RoleDto;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSave;
import academy.belhard.lms.service.dto.user.UserDtoForUpdate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T06:45:09+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDtoForSavingToUser(UserDtoForSave userDtoForSaving) {
        if ( userDtoForSaving == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( userDtoForSaving.getEmail() );
        user.setPassword( userDtoForSaving.getPassword() );
        user.setFirstName( userDtoForSaving.getFirstName() );
        user.setLastName( userDtoForSaving.getLastName() );
        user.setPatronymicName( userDtoForSaving.getPatronymicName() );
        user.setContactPreferences( contactPreferencesDtoToContactPreferences( userDtoForSaving.getContactPreferences() ) );
        user.setSocialMedia( userDtoForSaving.getSocialMedia() );

        return user;
    }

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setPatronymicName( user.getPatronymicName() );
        userDto.setContactPreferences( contactPreferencesToContactPreferencesDto( user.getContactPreferences() ) );
        userDto.setSocialMedia( user.getSocialMedia() );
        userDto.setRole( roleToRoleDto( user.getRole() ) );
        userDto.setActive( user.isActive() );

        return userDto;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setEmail( userDto.getEmail() );
        user.setPassword( userDto.getPassword() );
        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setPatronymicName( userDto.getPatronymicName() );
        user.setContactPreferences( contactPreferencesDtoToContactPreferences( userDto.getContactPreferences() ) );
        user.setSocialMedia( userDto.getSocialMedia() );
        user.setRole( roleDtoToRole( userDto.getRole() ) );
        user.setActive( userDto.isActive() );

        return user;
    }

    @Override
    public User userDtoForUpdatingToUser(UserDtoForUpdate userDtoForUpdating) {
        if ( userDtoForUpdating == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDtoForUpdating.getId() );
        user.setEmail( userDtoForUpdating.getEmail() );
        user.setPassword( userDtoForUpdating.getPassword() );
        user.setFirstName( userDtoForUpdating.getFirstName() );
        user.setLastName( userDtoForUpdating.getLastName() );
        user.setPatronymicName( userDtoForUpdating.getPatronymicName() );
        user.setContactPreferences( contactPreferencesDtoToContactPreferences( userDtoForUpdating.getContactPreferences() ) );
        user.setSocialMedia( userDtoForUpdating.getSocialMedia() );
        user.setRole( roleDtoToRole( userDtoForUpdating.getRole() ) );

        return user;
    }

    protected User.ContactPreferences contactPreferencesDtoToContactPreferences(ContactPreferencesDto contactPreferencesDto) {
        if ( contactPreferencesDto == null ) {
            return null;
        }

        User.ContactPreferences contactPreferences;

        switch ( contactPreferencesDto ) {
            case TELEGRAM: contactPreferences = User.ContactPreferences.TELEGRAM;
            break;
            case INSTAGRAM: contactPreferences = User.ContactPreferences.INSTAGRAM;
            break;
            case VIBER: contactPreferences = User.ContactPreferences.VIBER;
            break;
            case WHATSAPP: contactPreferences = User.ContactPreferences.WHATSAPP;
            break;
            case EMAIL: contactPreferences = User.ContactPreferences.EMAIL;
            break;
            case CELLPHONE: contactPreferences = User.ContactPreferences.CELLPHONE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + contactPreferencesDto );
        }

        return contactPreferences;
    }

    protected ContactPreferencesDto contactPreferencesToContactPreferencesDto(User.ContactPreferences contactPreferences) {
        if ( contactPreferences == null ) {
            return null;
        }

        ContactPreferencesDto contactPreferencesDto;

        switch ( contactPreferences ) {
            case TELEGRAM: contactPreferencesDto = ContactPreferencesDto.TELEGRAM;
            break;
            case INSTAGRAM: contactPreferencesDto = ContactPreferencesDto.INSTAGRAM;
            break;
            case VIBER: contactPreferencesDto = ContactPreferencesDto.VIBER;
            break;
            case WHATSAPP: contactPreferencesDto = ContactPreferencesDto.WHATSAPP;
            break;
            case EMAIL: contactPreferencesDto = ContactPreferencesDto.EMAIL;
            break;
            case CELLPHONE: contactPreferencesDto = ContactPreferencesDto.CELLPHONE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + contactPreferences );
        }

        return contactPreferencesDto;
    }

    protected RoleDto roleToRoleDto(User.Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto;

        switch ( role ) {
            case STUDENT: roleDto = RoleDto.STUDENT;
            break;
            case TRAINER: roleDto = RoleDto.TRAINER;
            break;
            case MANAGER: roleDto = RoleDto.MANAGER;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + role );
        }

        return roleDto;
    }

    protected User.Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        User.Role role;

        switch ( roleDto ) {
            case STUDENT: role = User.Role.STUDENT;
            break;
            case TRAINER: role = User.Role.TRAINER;
            break;
            case MANAGER: role = User.Role.MANAGER;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + roleDto );
        }

        return role;
    }
}
