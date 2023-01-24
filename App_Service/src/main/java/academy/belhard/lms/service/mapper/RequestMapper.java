package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForSave;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import academy.belhard.lms.service.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    Request request(RequestDto requestDto);

    Request request(RequestDtoForSave requestDtoForSave);

    Request request(RequestDtoForUpdate requestDtoForUpdate);

    RequestDto requestDto(Request request);

    Course course(CourseDto courseDto);

    CourseDto courseDto(Course course);

    User user(UserDto userDto);

    UserDto userDto(User user);

    RequestDtoForSave requestForSave(Request request);

    Request requestDtoForUpdate(Request request);

    RequestDtoForUpdate requestForUpdate(RequestDtoForUpdate requestDtoForUpdate);
}
