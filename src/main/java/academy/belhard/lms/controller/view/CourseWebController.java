package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.CourseService;
import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.course.CourseSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/courses")
@RequiredArgsConstructor
@Controller
public class CourseWebController {
    private final CourseService courseService;

    @GetMapping
    public String getAll(Model model) {
        List<CourseSimpleDto> courses = courseService.getAll();
        model.addAttribute("courses", courses);
        return "course/courses-list";
    }

    @GetMapping("/read/{id}")
    public String getSimpleById(Model model, @PathVariable Long id) {
        CourseSimpleDto course = courseService.getSimpleById(id);
        model.addAttribute("course", course);
        return "course/course";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id) {
        CourseDto course = courseService.getById(id);
        model.addAttribute("course", course);
        return "course/course";
    }

    @GetMapping("/createForm")
    public String createForm() {
        return "course/create-course";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("course") CourseDto course) {
        courseService.create(course);
        return "course/course";
    }

    @GetMapping("/updateForm/{id}")
    public String updateForm(Model model, @PathVariable Long id) {
        CourseDto courseDto = courseService.getById(id);
        model.addAttribute("course", courseDto);
        return "course/update-course";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("course") CourseDto newCourse) {
        newCourse.setId(id);
        courseService.update(newCourse);
        return "redirect:/courses/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        courseService.delete(id);
        return "redirect:/courses";
    }
}
