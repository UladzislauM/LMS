package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.FileLinkService;
import academy.belhard.lms.service.HomeworkService;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.dto.course.HomeworkDto;
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

@RequestMapping("/homeworks")
@RequiredArgsConstructor
@Controller
public class HomeworkWebController {
    private final HomeworkService homeworkService;
    private final FileLinkService fileLinkService;
    private final UserService userService;


    @GetMapping
    public String getAll(Model model, @PageableDefault @SortDefault("id") Pageable pageable) {
        Page<HomeworkDto> homeworks = homeworkService.getAll(pageable);
        model.addAttribute("homeworks", homeworks);
        return "homeworks";
    }

    @GetMapping("/student/{id}")
    public String getAllByStudentId(@PathVariable Long id, Model model, @PageableDefault @SortDefault("id") Pageable pageable) {
        Page<HomeworkDto> homeworks = homeworkService.getAllByStudentId(id, pageable);
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
    public String createForm(Model model) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto = userService.getById(userAppDetails.getId());
        model.addAttribute("user", userDto);
        return "create_homework";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute HomeworkDto homeworkDto) {
        UserDto userDto = userService.getById(homeworkDto.getStudent().getId());
        FileLinkDto fileLinkDto = fileLinkService.create(homeworkDto.getFileLink());
        homeworkDto.setFileLink(fileLinkDto);
        homeworkDto.setStudent(userDto);
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
