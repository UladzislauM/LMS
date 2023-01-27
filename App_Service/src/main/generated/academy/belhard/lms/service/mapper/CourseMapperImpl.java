package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.FileLink;
import academy.belhard.lms.data.entity.Homework;
import academy.belhard.lms.data.entity.Lesson;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.course.CourseSimpleDto;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import academy.belhard.lms.service.dto.course.LessonDto;
import academy.belhard.lms.service.dto.course.LessonSimpleDto;
import academy.belhard.lms.service.dto.user.ContactPreferencesDto;
import academy.belhard.lms.service.dto.user.RoleDto;
import academy.belhard.lms.service.dto.user.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-27T10:21:36+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseSimpleDto courseToCourseSimpleDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseSimpleDto courseSimpleDto = new CourseSimpleDto();

        courseSimpleDto.setId( course.getId() );
        courseSimpleDto.setTitle( course.getTitle() );
        courseSimpleDto.setPrice( course.getPrice() );
        courseSimpleDto.setStartDate( course.getStartDate() );
        courseSimpleDto.setDescription( course.getDescription() );
        courseSimpleDto.setLessons( lessonListToLessonSimpleDtoList( course.getLessons() ) );

        return courseSimpleDto;
    }

    @Override
    public Course courseSimpleDtoToCourse(CourseSimpleDto courseSimpleDto) {
        if ( courseSimpleDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( courseSimpleDto.getId() );
        course.setTitle( courseSimpleDto.getTitle() );
        course.setDescription( courseSimpleDto.getDescription() );
        course.setPrice( courseSimpleDto.getPrice() );
        course.setStartDate( courseSimpleDto.getStartDate() );
        course.setLessons( lessonSimpleDtoListToLessonList( courseSimpleDto.getLessons() ) );

        return course;
    }

    @Override
    public Course courseDtoToCourse(CourseDto courseDto) {
        if ( courseDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( courseDto.getId() );
        course.setTitle( courseDto.getTitle() );
        course.setDescription( courseDto.getDescription() );
        course.setPrice( courseDto.getPrice() );
        course.setTrainer( userDtoToUser( courseDto.getTrainer() ) );
        course.setStartDate( courseDto.getStartDate() );
        course.setLessons( lessonDtoListToLessonList( courseDto.getLessons() ) );

        return course;
    }

    @Override
    public CourseDto courseToCourseDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDto courseDto = new CourseDto();

        courseDto.setId( course.getId() );
        courseDto.setTitle( course.getTitle() );
        courseDto.setPrice( course.getPrice() );
        courseDto.setStartDate( course.getStartDate() );
        courseDto.setTrainer( userToUserDto( course.getTrainer() ) );
        courseDto.setDescription( course.getDescription() );
        courseDto.setLessons( lessonListToLessonDtoList( course.getLessons() ) );

        return courseDto;
    }

    protected LessonSimpleDto lessonToLessonSimpleDto(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonSimpleDto lessonSimpleDto = new LessonSimpleDto();

        lessonSimpleDto.setId( lesson.getId() );
        lessonSimpleDto.setTitle( lesson.getTitle() );
        lessonSimpleDto.setStartTime( lesson.getStartTime() );
        lessonSimpleDto.setDescription( lesson.getDescription() );

        return lessonSimpleDto;
    }

    protected List<LessonSimpleDto> lessonListToLessonSimpleDtoList(List<Lesson> list) {
        if ( list == null ) {
            return null;
        }

        List<LessonSimpleDto> list1 = new ArrayList<LessonSimpleDto>( list.size() );
        for ( Lesson lesson : list ) {
            list1.add( lessonToLessonSimpleDto( lesson ) );
        }

        return list1;
    }

    protected Lesson lessonSimpleDtoToLesson(LessonSimpleDto lessonSimpleDto) {
        if ( lessonSimpleDto == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setId( lessonSimpleDto.getId() );
        lesson.setTitle( lessonSimpleDto.getTitle() );
        lesson.setStartTime( lessonSimpleDto.getStartTime() );
        lesson.setDescription( lessonSimpleDto.getDescription() );

        return lesson;
    }

    protected List<Lesson> lessonSimpleDtoListToLessonList(List<LessonSimpleDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Lesson> list1 = new ArrayList<Lesson>( list.size() );
        for ( LessonSimpleDto lessonSimpleDto : list ) {
            list1.add( lessonSimpleDtoToLesson( lessonSimpleDto ) );
        }

        return list1;
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

    protected Homework homeworkDtoToHomework(HomeworkDto homeworkDto) {
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

    protected List<Homework> homeworkDtoListToHomeworkList(List<HomeworkDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Homework> list1 = new ArrayList<Homework>( list.size() );
        for ( HomeworkDto homeworkDto : list ) {
            list1.add( homeworkDtoToHomework( homeworkDto ) );
        }

        return list1;
    }

    protected Lesson lessonDtoToLesson(LessonDto lessonDto) {
        if ( lessonDto == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setId( lessonDto.getId() );
        lesson.setTitle( lessonDto.getTitle() );
        lesson.setStartTime( lessonDto.getStartTime() );
        lesson.setDescription( lessonDto.getDescription() );
        lesson.setContent( lessonDto.getContent() );
        lesson.setHomeTask( lessonDto.getHomeTask() );
        lesson.setHomeworks( homeworkDtoListToHomeworkList( lessonDto.getHomeworks() ) );

        return lesson;
    }

    protected List<Lesson> lessonDtoListToLessonList(List<LessonDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Lesson> list1 = new ArrayList<Lesson>( list.size() );
        for ( LessonDto lessonDto : list ) {
            list1.add( lessonDtoToLesson( lessonDto ) );
        }

        return list1;
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

    protected HomeworkDto homeworkToHomeworkDto(Homework homework) {
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

    protected List<HomeworkDto> homeworkListToHomeworkDtoList(List<Homework> list) {
        if ( list == null ) {
            return null;
        }

        List<HomeworkDto> list1 = new ArrayList<HomeworkDto>( list.size() );
        for ( Homework homework : list ) {
            list1.add( homeworkToHomeworkDto( homework ) );
        }

        return list1;
    }

    protected LessonDto lessonToLessonDto(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonDto lessonDto = new LessonDto();

        lessonDto.setId( lesson.getId() );
        lessonDto.setTitle( lesson.getTitle() );
        lessonDto.setStartTime( lesson.getStartTime() );
        lessonDto.setDescription( lesson.getDescription() );
        lessonDto.setContent( lesson.getContent() );
        lessonDto.setHomeTask( lesson.getHomeTask() );
        lessonDto.setHomeworks( homeworkListToHomeworkDtoList( lesson.getHomeworks() ) );

        return lessonDto;
    }

    protected List<LessonDto> lessonListToLessonDtoList(List<Lesson> list) {
        if ( list == null ) {
            return null;
        }

        List<LessonDto> list1 = new ArrayList<LessonDto>( list.size() );
        for ( Lesson lesson : list ) {
            list1.add( lessonToLessonDto( lesson ) );
        }

        return list1;
    }
}
