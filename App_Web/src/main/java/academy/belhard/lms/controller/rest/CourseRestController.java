package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.CourseService;
import academy.belhard.lms.service.dto.course.CourseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1.0/courses")
@RequiredArgsConstructor
@RestController
public class CourseRestController {
    private final CourseService courseService;

    @GetMapping
    public List<CourseDto> getAll(Pageable pageable) {
        return courseService.getAll(pageable).getContent();
    }

    @GetMapping("/{id}")
    public CourseDto getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @PostMapping
    public CourseDto create(@RequestBody CourseDto courseDto) {
        return courseService.create(courseDto);
    }

    @PutMapping("/{id}")
    public CourseDto update(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        courseDto.setId(id);
        return courseService.update(courseDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        courseService.delete(id);
    }
}
