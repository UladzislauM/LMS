package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.course.LessonDto;
import academy.belhard.lms.service.dto.course.LessonSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LessonService {
    Page<LessonDto> getAll(Pageable pageable);

    LessonSimpleDto getSimpleById(Long id);

    LessonDto getById(Long id);

    LessonDto create(LessonDto lessonDto);

    LessonDto update(LessonDto lessonDto);

    void delete(Long id);
}