package academy.belhard.lms.controller.view;

import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSaving;
import academy.belhard.lms.service.dto.user.UserDtoForUpdating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v1.0/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @RequestMapping("/new")
    public String newUser(ModelMap modelMap) {
        UserDto dto = new UserDto();
        modelMap.addAttribute("user", dto);
        return "create";
    }

    @RequestMapping("/create")
    public String createUser(@ModelAttribute("user") UserDtoForSaving dto) {
        userService.createUser(dto);
        return "redirect:/v1.0/user/users";
    }

    @GetMapping("/users")
    public String getAllUsers(ModelMap modelMap) {
        List<UserDto> users = userService.getAllUsers();
        modelMap.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, ModelMap modelMap) {
        UserDto dto = userService.getUserById(id);
        modelMap.addAttribute("user", dto);
        return "user";
    }

    @RequestMapping("update/{id}")
    public String updateUser(@PathVariable(value = "id") Long id, ModelMap modelMap) {
        UserDto dto = userService.getUserById(id);
        modelMap.addAttribute("user", dto);
        return "update";
    }

    @RequestMapping("/save")
    public String saveUser(@ModelAttribute("user") UserDtoForUpdating dto) {
        userService.updateUser(dto);
        return "redirect:/v1.0/user/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/v1.0/user/users";
    }
}
