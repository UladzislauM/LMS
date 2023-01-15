package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.course.LessonDto;

import java.util.List;

public interface LessonService {
    List<LessonDto> getAll();

    LessonDto getById(Long id);

    LessonDto create(LessonDto lessonDto);

    LessonDto update(LessonDto lessonDto);

    void delete(Long id);
}