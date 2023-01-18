package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.course.LessonDto;
import academy.belhard.lms.service.dto.course.LessonSimpleDto;

import java.util.List;

public interface LessonService {
    List<LessonDto> getAll();

    LessonSimpleDto getSimpleById(Long id);

    LessonDto getById(Long id);

    LessonDto create(LessonDto lessonDto);

    LessonDto update(LessonDto lessonDto);

    void delete(Long id);
}