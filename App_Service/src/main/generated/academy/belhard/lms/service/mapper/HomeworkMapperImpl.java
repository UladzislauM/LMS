package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.FileLink;
import academy.belhard.lms.data.entity.Homework;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import academy.belhard.lms.service.dto.user.ContactPreferencesDto;
import academy.belhard.lms.service.dto.user.RoleDto;
import academy.belhard.lms.service.dto.user.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-27T10:21:35+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
@Component
public class HomeworkMapperImpl implements HomeworkMapper {

    @Override
    public HomeworkDto homeworkToHomeworkDto(Homework homework) {
        if ( homework == null ) {
            return null;
        }

        HomeworkDto homeworkDto = new HomeworkDto();

        homeworkDto.setId( homework.getId() );
        homeworkDto.setStudent( userToUserDto( homework.getStudent() ) );
        homeworkDto.setComment( homework.getComment() );
        homeworkDto.setFileLink( fileLinkToFileLinkDto( homework.getFileLink() ) );
        homeworkDto.setMark( homework.getMark() );

        return homeworkDto;
    }

    @Override
    public Homework homeworkDtoToHomework(HomeworkDto homeworkDto) {
        if ( homeworkDto == null ) {
            return null;
        }

        Homework homework = new Homework();

        homework.setId( homeworkDto.getId() );
        homework.setStudent( userDtoToUser( homeworkDto.getStudent() ) );
        homework.setComment( homeworkDto.getComment() );
        homework.setFileLink( fileLinkDtoToFileLink( homeworkDto.getFileLink() ) );
        homework.setMark( homeworkDto.getMark() );

        return homework;
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

    protected UserDto userToUserDto(User user) {
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

    protected FileLinkDto fileLinkToFileLinkDto(FileLink fileLink) {
        if ( fileLink == null ) {
            return null;
        }

        FileLinkDto fileLinkDto = new FileLinkDto();

        fileLinkDto.setId( fileLink.getId() );
        fileLinkDto.setLink( fileLink.getLink() );

        return fileLinkDto;
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

    protected User userDtoToUser(UserDto userDto) {
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

    protected FileLink fileLinkDtoToFileLink(FileLinkDto fileLinkDto) {
        if ( fileLinkDto == null ) {
            return null;
        }

        FileLink fileLink = new FileLink();

        fileLink.setId( fileLinkDto.getId() );
        fileLink.setLink( fileLinkDto.getLink() );

        return fileLink;
    }
}
