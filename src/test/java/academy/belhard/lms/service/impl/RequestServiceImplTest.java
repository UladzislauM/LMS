package academy.belhard.lms.service.impl;

import academy.belhard.lms.service.UserService;
import academy.belhard.lms.service.dto.user.UserDto;
import academy.belhard.lms.service.dto.user.UserDtoForSaving;
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

//@ExtendWith(SpringExtension.class)
class RequestServiceImplTest {

    UserService userServiceMock = Mockito.mock(UserService.class);

    UserDto userDtoMock = Mockito.mock(UserDto.class);

    @MockBean
    private UserDto userDto;


//    @MockBean
//    private UserService userService;

//    @MockBean
//    private UserDto userDto;
//  UserDto createUser(UserDtoForSaving userDtoForSaving);
    @Test
    void create() {
    }

    @Test
    void getAllUsers() {
        List<UserDto> list = new ArrayList<>();
        list.add(userDtoMock);
        Mockito.when(userServiceMock.getAllUsers()).thenReturn(list);
        assertEquals(list, userServiceMock.getAllUsers());
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