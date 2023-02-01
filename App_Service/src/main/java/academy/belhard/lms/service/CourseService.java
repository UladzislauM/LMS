package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.course.CourseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    Page<CourseDto> getAll(Pageable pageable);

    CourseDto getById(Long id);

    Page<CourseDto> getByStudentId(Pageable pageable, Long id);

    CourseDto create(CourseDto courseDto);

    CourseDto update(CourseDto courseDto);

    void delete(Long id);
}
