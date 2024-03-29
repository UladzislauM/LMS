package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.CourseService;
import academy.belhard.lms.service.RequestService;
import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSave;
import academy.belhard.lms.service.dto.user.UserDtoForUpdate;
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

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserWebController {
    public static final int SIZE_PAGE = 10;
    public static final String SORT_PAGE = "id";
    private final UserService userService;
    private final CourseService courseService;
    private final RequestService requestService;

    @GetMapping("/create")
    public String createForm() {
        return "registration";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") UserDtoForSave dto) {
        UserDto created = userService.create(dto);
        return "redirect:/users/" + created.getId();
    }

    @GetMapping
    public String getAll(Model model, @PageableDefault @SortDefault("id") Pageable pageable) {
        Page<UserDto> users = userService.getAll(pageable);
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        UserDto dto = userService.getById(id);
        model.addAttribute("user", dto);
        return "user";
    }

    @GetMapping("/personal")
    public String getPersonalPageById(Model model, @PageableDefault(size = SIZE_PAGE) @SortDefault(SORT_PAGE) Pageable pageable) {
        UserAppDetails userAppDetails = (UserAppDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = userAppDetails.getId();
        UserDto user = userService.getById(id);
        model.addAttribute("user", user);
        switch (user.getRole()) {
            case STUDENT -> addToStudentPersonal(model, pageable, id);
            case TRAINER -> addToTrainerPersonal(model, pageable, id);
            case MANAGER -> addToManagerPersonal(model, pageable, id);
        }
        return "personal_page";
    }

    private void addToTrainerPersonal(Model model, Pageable pageable, Long id) {
        Page<RequestDto> requests = requestService.getAll(Pageable.unpaged());
        model.addAttribute("requests", requests);
        Page<CourseDto> courses = courseService.getByTrainerId(pageable, id);
        model.addAttribute("courses", courses);
    }

    private void addToStudentPersonal(Model model, Pageable pageable, Long id) {
        Page<CourseDto> courses = courseService.getByStudentId(pageable, id);
        model.addAttribute("courses", courses);
    }

    private void addToManagerPersonal(Model model, Pageable pageable, Long id) {
        Page<RequestDto> requests = requestService.getAllWithProcessingStatus(pageable);
        model.addAttribute("requests", requests);
        Page<CourseDto> courses = courseService.getAll(pageable);
        model.addAttribute("courses", courses);
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        UserDto toUpdate = userService.getById(id);
        model.addAttribute("user", toUpdate);
        return "update_user";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") Long id,
                         @ModelAttribute("user") UserDtoForUpdate dto,
                         @ModelAttribute("isActive") Boolean isActive) {
        dto.setId(id);
        dto.setActive(isActive);
        userService.update(dto);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
