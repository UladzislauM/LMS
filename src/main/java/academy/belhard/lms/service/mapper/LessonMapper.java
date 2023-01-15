package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.Lesson;
import academy.belhard.lms.service.dto.course.LessonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    LessonDto lessonToLessonDto(Lesson lesson);

    Lesson lessonDtoToLesson(LessonDto lessonDto);
}
