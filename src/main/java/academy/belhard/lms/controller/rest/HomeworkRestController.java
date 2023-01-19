package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.HomeworkService;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1.0/homeworks")
@RequiredArgsConstructor
@RestController
public class HomeworkRestController {
    private final HomeworkService homeworkService;

    @GetMapping
    public List<HomeworkDto> getAll() {
        return homeworkService.getAll();
    }

    @GetMapping("/{id}")
    public HomeworkDto getById(@PathVariable Long id) {
        return homeworkService.getById(id);
    }

    @PostMapping
    public HomeworkDto create(@RequestBody HomeworkDto homeworkDto) {
        homeworkService.create(homeworkDto);
        return null;
    }

    @PutMapping("/{id}")
    public HomeworkDto update(@PathVariable Long id, @RequestBody HomeworkDto homeworkDto) {
        homeworkDto.setId(id);
        return homeworkService.update(homeworkDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        homeworkService.delete(id);
    }
}
