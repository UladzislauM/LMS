package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.HomeworkService;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        return "";
    }

    @PostMapping("/create")
    public String create(Model model, @RequestParam Map<String, Object> params) {
        return "";
    }

    @GetMapping("/updateForm/{id}")
    public String updateForm(Model model, @PathVariable Long id) {
        return "";
    }

    @PostMapping("/update")
    public String update(@RequestParam Map<String, Object> params) {
        return "";
    }

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        return "";
    }
}
