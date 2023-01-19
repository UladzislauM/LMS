package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.course.CourseSimpleDto;

import java.util.List;

public interface CourseService {
    List<CourseSimpleDto> getAll();

    CourseDto getById(Long id);

    CourseSimpleDto getSimpleById(Long id);

    CourseDto create(CourseDto courseDto);

    CourseDto update(CourseDto courseDto);

    void delete(Long id);
}
