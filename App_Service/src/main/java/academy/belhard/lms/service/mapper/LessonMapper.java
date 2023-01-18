package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.Lesson;
import academy.belhard.lms.service.dto.course.LessonDto;
import academy.belhard.lms.service.dto.course.LessonSimpleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    LessonSimpleDto lessonToLessonSimpleDto(Lesson lesson);

    Lesson lessonSimpleDtoToLesson(LessonSimpleDto lessonSimpleDto);

    LessonDto lessonToLessonDto(Lesson lesson);

    Lesson lessonDtoToLesson(LessonDto lessonDto);
}
