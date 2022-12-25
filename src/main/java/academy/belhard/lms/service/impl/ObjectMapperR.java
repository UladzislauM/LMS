package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.service.dto.RequestDto;
import academy.belhard.lms.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperR {
    public Request toRequest(RequestDto requestDto) {
        Request request = new Request();
        request.setId(requestDto.getId());
        request.setUser(requestDto.getUser());
        request.setCourse(requestDto.getCourse());
        request.setDeleted(requestDto.isDeleted());
        request.setStatusReq(requestDto.getStatusReq());
        return request;
    }

    public RequestDto toRequestDto(Request request) {
        RequestDto requestDto = new RequestDto();
        requestDto.setId(request.getId());
        requestDto.setUser(request.getUser());
        requestDto.setCourse(request.getCourse());
        requestDto.setDeleted(request.isDeleted());
        requestDto.setStatusReq(request.getStatusReq());
        return requestDto;
    }

    public User toUser(UserDto userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setLast_name(userDTO.getLast_name());
        user.setRole(userDTO.getRole());
        user.setIs_active(userDTO.getIs_active());
        user.setRequest(userDTO.getRequest());
        user.setCourse_list(userDTO.getCourse_list());
        return user;
    }

    public UserDto toUserDTO(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setLast_name(user.getLast_name());
        userDTO.setRole(user.getRole());
        userDTO.setIs_active(user.getIs_active());
        userDTO.setRequest(user.getRequest());
        userDTO.setCourse_list(user.getCourse_list());
        return userDTO;
    }
}
