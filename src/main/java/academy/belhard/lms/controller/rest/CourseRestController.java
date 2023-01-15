package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.CourseService;
import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.course.CourseSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1.0/courses")
@RequiredArgsConstructor
@RestController
public class CourseRestController {
    private final CourseService courseService;

    @GetMapping
    public List<CourseSimpleDto> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public CourseSimpleDto getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseSimpleDto create(@RequestBody CourseSimpleDto courseSimpleDto) {
        return courseService.create(courseSimpleDto);
    }

    @PutMapping("/{id}")
    public CourseDto update(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        courseDto.setId(id);
        return courseService.update(courseDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        courseService.delete(id);
    }
}
