package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSave;
import academy.belhard.lms.service.dto.user.UserDtoForUpdate;
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

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerWeb {
    private final UserService userService;

    @GetMapping("/create")
    public String createForm() {
        return "create_user";
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

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        UserDto toUpdate = userService.getById(id);
        model.addAttribute("user", toUpdate);
        return "update_user";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") Long id, @ModelAttribute("user") UserDtoForUpdate dto) {
        dto.setId(id);
        UserDto updated = userService.update(dto);
        return "redirect:/users/" + updated.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
