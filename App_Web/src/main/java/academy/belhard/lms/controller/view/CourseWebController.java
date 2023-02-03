package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.CourseService;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.impl.UserAppDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@RequestMapping("/courses")
@RequiredArgsConstructor
@Controller
public class CourseWebController {
    private final CourseService courseService;
    private final UserService userService;

    @GetMapping
    public String getAll(Model model, @PageableDefault @SortDefault("id") Pageable pageable) {
        Page<CourseDto> courses = courseService.getAll(pageable);
        model.addAttribute("courses", courses);
        return "course/courses";
    }

    @GetMapping("/student")
    public String getAllForStudent(Model model, @PageableDefault @SortDefault("id") Pageable pageable) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = userAppDetails.getId();
        Page<CourseDto> courses = courseService.getByStudentId(pageable, id);
        model.addAttribute("courses", courses);
        return "course/student_courses";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable Long id, @PageableDefault @SortDefault("id") Pageable pageable) {
        CourseDto course = courseService.getById(id);
        model.addAttribute("course", course);
        Page<UserDto> users = userService.getAllByCourseAndRequestStatus(id, pageable);
        model.addAttribute("users", users);
        return "course/course";
    }

    @GetMapping("/createForm")
    public String createForm() {
        return "course/create_course";
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
        return "course/update_course";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("course") CourseDto newCourse,
                         @RequestParam("localDate") LocalDate localDate) {
        newCourse.setId(id);
        newCourse.setStartDate(localDate);
        courseService.update(newCourse);
        return "redirect:/courses/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        courseService.delete(id);
        return "redirect:/courses";
    }
}
