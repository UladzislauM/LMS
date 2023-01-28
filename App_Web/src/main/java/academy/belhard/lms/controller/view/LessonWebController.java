package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.LessonService;
import academy.belhard.lms.service.dto.course.LessonDto;
import academy.belhard.lms.service.dto.course.LessonSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lessons")
@RequiredArgsConstructor
@Controller
public class LessonWebController {
    private final LessonService lessonService;

    @GetMapping
    public String getAll(Model model, @PageableDefault @SortDefault("id") Pageable pageable) {
        Page<LessonDto> lessons = lessonService.getAll(pageable);
        model.addAttribute("lessons", lessons);
        return "lesson/lessons";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        LessonDto lesson = lessonService.getById(id);
        model.addAttribute("lesson", lesson);
        return "lesson/lesson";
    }

    @GetMapping("/read/{id}")
    public String getSimpleById(Model model, @PathVariable Long id) {
        LessonSimpleDto lesson = lessonService.getSimpleById(id);
        model.addAttribute("lesson", lesson);
        return "lesson/lesson";
    }

    @GetMapping("/createForm")
    public String createForm() {
        return "lesson/create_lesson";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("lesson") LessonDto newLesson) {
        lessonService.create(newLesson);
        return "lesson/lesson";
    }

    @GetMapping("/updateForm/{id}")
    public String updateForm(Model model, @PathVariable Long id) {
        LessonDto lessonDto = lessonService.getById(id);
        model.addAttribute("lesson", lessonDto);
        return "lesson/update_lesson";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("lesson") LessonDto newLesson) {
        newLesson.setId(id);
        lessonService.update(newLesson);
        return "redirect:/lesson/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        lessonService.delete(id);
        return "redirect:/lessons";
    }
}
