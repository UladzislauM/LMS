package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.LessonService;
import academy.belhard.lms.service.dto.course.LessonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1.0/lessons")
@RequiredArgsConstructor
@RestController
public class LessonRestController {
    private final LessonService lessonService;

    @GetMapping
    public List<LessonDto> getAll() {
        return lessonService.getAll();
    }

    @GetMapping("/{id}")
    public LessonDto getById(@PathVariable Long id) {
        return lessonService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonDto create(@RequestBody LessonDto lessonDto) {
        return lessonService.create(lessonDto);
    }

    @PutMapping("/{id}")
    public LessonDto update(@PathVariable Long id, @RequestBody LessonDto lessonDto) {
        lessonDto.setId(id);
        return lessonService.update(lessonDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        lessonService.delete(id);
    }
}
