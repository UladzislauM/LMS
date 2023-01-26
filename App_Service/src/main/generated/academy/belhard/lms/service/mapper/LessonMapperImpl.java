package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.FileLink;
import academy.belhard.lms.data.entity.Homework;
import academy.belhard.lms.data.entity.Lesson;
import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import academy.belhard.lms.service.dto.course.LessonDto;
import academy.belhard.lms.service.dto.course.LessonSimpleDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-26T19:52:17+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class LessonMapperImpl implements LessonMapper {

    @Override
    public LessonSimpleDto lessonToLessonSimpleDto(Lesson lesson) {
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

    @Override
    public Lesson lessonSimpleDtoToLesson(LessonSimpleDto lessonSimpleDto) {
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

    @Override
    public LessonDto lessonToLessonDto(Lesson lesson) {
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

    @Override
    public Lesson lessonDtoToLesson(LessonDto lessonDto) {
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
}
