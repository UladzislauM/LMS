package academy.belhard.lms.controller.rest;

import academy.belhard.lms.dto.UserDto;
import academy.belhard.lms.dto.UserDtoForSaving;
import academy.belhard.lms.model.User;
import academy.belhard.lms.servise.UserService;
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
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping
    public User create(@RequestBody UserDtoForSaving userDtoForSaving) {
        return userService.createUser(userDtoForSaving);
    }

    @GetMapping
    public List<UserDto> findAllUsers() {
        return userService.findAllUsers();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }
}
