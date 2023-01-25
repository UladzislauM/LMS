package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.FileLinkService;
import academy.belhard.lms.service.HomeworkService;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/homeworks")
@RequiredArgsConstructor
@Controller
public class HomeworkWebController {
    private final HomeworkService homeworkService;
    private final FileLinkService fileLinkService;

    @GetMapping
    public String getAll(Model model) {
        List<HomeworkDto> homeworks = homeworkService.getAll();
        model.addAttribute("homeworks", homeworks);
        return "homeworks";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        HomeworkDto homework = homeworkService.getById(id);
        model.addAttribute("homework", homework);
        return "homework";
    }

    @GetMapping("/create")
    public String createForm() {
        return "create_homework";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute HomeworkDto homeworkDto) {
        HomeworkDto created = homeworkService.create(homeworkDto);
        return "redirect:/homeworks/" + created.getId();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        HomeworkDto toUpdate = homeworkService.getById(id);
        model.addAttribute("homework", toUpdate);
        return "update_homework";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute HomeworkDto homeworkDto) {
        homeworkDto.setId(id);
        homeworkService.update(homeworkDto);
        return "redirect:/homeworks";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        homeworkService.delete(id);
        return "redirect:/homeworks";
    }
}
