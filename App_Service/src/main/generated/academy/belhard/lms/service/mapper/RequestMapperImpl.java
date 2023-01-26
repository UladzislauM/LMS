package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.FileLink;
import academy.belhard.lms.data.entity.Homework;
import academy.belhard.lms.data.entity.Lesson;
import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import academy.belhard.lms.service.dto.course.LessonDto;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForSave;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import academy.belhard.lms.service.dto.request.StatusDto;
import academy.belhard.lms.service.dto.user.ContactPreferencesDto;
import academy.belhard.lms.service.dto.user.RoleDto;
import academy.belhard.lms.service.dto.user.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T19:52:18+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class RequestMapperImpl implements RequestMapper {

    @Override
    public Request request(RequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Request request = new Request();

        request.setId( requestDto.getId() );
        request.setCourse( course( requestDto.getCourse() ) );
        request.setUser( user( requestDto.getUser() ) );
        request.setStatus( statusDtoToStatus( requestDto.getStatus() ) );

        return request;
    }

    @Override
    public Request request(RequestDtoForSave requestDtoForSave) {
        if ( requestDtoForSave == null ) {
            return null;
        }

        Request request = new Request();

        request.setCourse( course( requestDtoForSave.getCourse() ) );
        request.setUser( user( requestDtoForSave.getUser() ) );

        return request;
    }

    @Override
    public Request request(RequestDtoForUpdate requestDtoForUpdate) {
        if ( requestDtoForUpdate == null ) {
            return null;
        }

        Request request = new Request();

        request.setId( requestDtoForUpdate.getId() );
        request.setCourse( course( requestDtoForUpdate.getCourse() ) );
        request.setStatus( statusDtoToStatus( requestDtoForUpdate.getStatus() ) );

        return request;
    }

    @Override
    public RequestDto requestDto(Request request) {
        if ( request == null ) {
            return null;
        }

        RequestDto requestDto = new RequestDto();

        requestDto.setId( request.getId() );
        requestDto.setCourse( courseDto( request.getCourse() ) );
        requestDto.setUser( userDto( request.getUser() ) );
        requestDto.setStatus( statusToStatusDto( request.getStatus() ) );

        return requestDto;
    }

    @Override
    public Course course(CourseDto courseDto) {
        if ( courseDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( courseDto.getId() );
        course.setTitle( courseDto.getTitle() );
        course.setDescription( courseDto.getDescription() );
        course.setPrice( courseDto.getPrice() );
        course.setTrainer( courseDto.getTrainer() );
        course.setStartDate( courseDto.getStartDate() );
        course.setLessons( lessonDtoListToLessonList( courseDto.getLessons() ) );

        return course;
    }

    @Override
    public CourseDto courseDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDto courseDto = new CourseDto();

        courseDto.setId( course.getId() );
        courseDto.setTitle( course.getTitle() );
        courseDto.setPrice( course.getPrice() );
        courseDto.setStartDate( course.getStartDate() );
        courseDto.setTrainer( course.getTrainer() );
        courseDto.setDescription( course.getDescription() );
        courseDto.setLessons( lessonListToLessonDtoList( course.getLessons() ) );

        return courseDto;
    }

    @Override
    public User user(UserDto userDto) {
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
    public UserDto userDto(User user) {
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
    public RequestDtoForSave requestForSave(Request request) {
        if ( request == null ) {
            return null;
        }

        RequestDtoForSave requestDtoForSave = new RequestDtoForSave();

        requestDtoForSave.setCourse( courseDto( request.getCourse() ) );
        requestDtoForSave.setUser( userDto( request.getUser() ) );

        return requestDtoForSave;
    }

    @Override
    public Request requestDtoForUpdate(Request request) {
        if ( request == null ) {
            return null;
        }

        Request request1 = new Request();

        request1.setId( request.getId() );
        request1.setCourse( request.getCourse() );
        request1.setUser( request.getUser() );
        request1.setStatus( request.getStatus() );

        return request1;
    }

    @Override
    public RequestDtoForUpdate requestForUpdate(RequestDtoForUpdate requestDtoForUpdate) {
        if ( requestDtoForUpdate == null ) {
            return null;
        }

        RequestDtoForUpdate requestDtoForUpdate1 = new RequestDtoForUpdate();

        requestDtoForUpdate1.setId( requestDtoForUpdate.getId() );
        requestDtoForUpdate1.setCourse( requestDtoForUpdate.getCourse() );
        requestDtoForUpdate1.setStatus( requestDtoForUpdate.getStatus() );

        return requestDtoForUpdate1;
    }

    protected Request.Status statusDtoToStatus(StatusDto statusDto) {
        if ( statusDto == null ) {
            return null;
        }

        Request.Status status;

        switch ( statusDto ) {
            case PROCESSING: status = Request.Status.PROCESSING;
            break;
            case APPROVED: status = Request.Status.APPROVED;
            break;
            case PAID: status = Request.Status.PAID;
            break;
            case SATISFIED: status = Request.Status.SATISFIED;
            break;
            case CANCELLED: status = Request.Status.CANCELLED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + statusDto );
        }

        return status;
    }

    protected StatusDto statusToStatusDto(Request.Status status) {
        if ( status == null ) {
            return null;
        }

        StatusDto statusDto;

        switch ( status ) {
            case PROCESSING: statusDto = StatusDto.PROCESSING;
            break;
            case APPROVED: statusDto = StatusDto.APPROVED;
            break;
            case PAID: statusDto = StatusDto.PAID;
            break;
            case SATISFIED: statusDto = StatusDto.SATISFIED;
            break;
            case CANCELLED: statusDto = StatusDto.CANCELLED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + status );
        }

        return statusDto;
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
        homework.setStudent( homeworkDto.getStudent() );
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
        homeworkDto.setStudent( homework.getStudent() );
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
}
