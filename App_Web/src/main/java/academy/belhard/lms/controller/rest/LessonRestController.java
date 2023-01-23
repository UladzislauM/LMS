package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.LessonService;
import academy.belhard.lms.service.dto.course.LessonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1.0/lessons")
@RequiredArgsConstructor
@RestController
public class LessonRestController {
    private final LessonService lessonService;

    @GetMapping
    public Page<LessonDto> getAll(Pageable pageable) {
        return lessonService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public LessonDto getById(@PathVariable Long id) {
        return lessonService.getById(id);
    }

    @PostMapping
    public LessonDto create(@RequestBody LessonDto lessonDto) {
        return lessonService.create(lessonDto);
    }

    @PutMapping("/{id}")
    public LessonDto update(@PathVariable Long id, @RequestBody LessonDto lessonDto) {
        lessonDto.setId(id);
        return lessonService.update(lessonDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        lessonService.delete(id);
    }
}
