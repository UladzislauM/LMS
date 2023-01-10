package academy.belhard.lms.service.impl;

import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.user.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class RequestServiceImplTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserDto userDto;

    @Test
    void create() {
    }

    @Test
    void getAll() {
        List<UserDto> list = new ArrayList<>();
        list.add(userDto);
        Mockito.when(userService.getAllUsers()).thenReturn(list);
        assertEquals(list, userService.getAllUsers());
    }

    @Test
    void getById() {
        Mockito.when(userService.getUserById(any())).thenReturn(userDto);
        assertEquals(userDto, userService.getUserById(any()));
    }

    @Test
    void update() {
    }
}