package academy.belhard.lms.controller.rest;

import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSaving;
import academy.belhard.lms.service.dto.user.UserDtoForUpdating;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping
    public UserDto create(@RequestBody UserDtoForSaving dto) {
        return userService.createUser(dto);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDtoForUpdating dto) {
        dto.setId(id);
        return userService.updateUser(dto);
    }

    @GetMapping("/delete/{id}")
    public UserDto deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
