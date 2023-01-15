package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.course.CourseSimpleDto;

import java.util.List;

public interface CourseService {
    List<CourseSimpleDto> getAll();

    CourseSimpleDto getById(Long id);

    CourseSimpleDto create(CourseSimpleDto CourseSimpleDto);

    CourseDto update(CourseDto courseDto);

    void delete(Long id);
}
