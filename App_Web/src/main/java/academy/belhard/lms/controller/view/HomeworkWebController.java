package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.HomeworkService;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/homeworks")
@RequiredArgsConstructor
@Controller
public class HomeworkWebController {
    private final HomeworkService homeworkService;

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

    @GetMapping("/createForm")
    public String createForm() {

        return "create-homework";
    }

    @PostMapping("/create")
    public String create(Model model, @RequestParam Map<String, Object> params) {
        return "redirect:/homeworks";
    }

    @GetMapping("/update/{id}")
    public String updateForm(Model model, @PathVariable Long id) {
        HomeworkDto toUpdate = homeworkService.getById(id);
        model.addAttribute("update", toUpdate);
        return "update-homework";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute HomeworkDto homeworkDto) {
        homeworkDto.setId(id);
        homeworkService.update(homeworkDto);
        return "redirect:/homeworks";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        homeworkService.delete(id);
        return "redirect:/homeworks";
    }
}
