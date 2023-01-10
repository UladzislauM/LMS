package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.service.dto.request.CourseDto;
import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForSave;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import academy.belhard.lms.service.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    Request Request(RequestDto requestDto);

    RequestDto RequestDto(Request request);

    Course Course(CourseDto courseDto);

    CourseDto CourseDto(Course course);

    User User(UserDto userDto);

    UserDto UserDto(User user);

    Request RequestDtoForSave(RequestDtoForSave requestDtoForSaving);

    RequestDtoForSave RequestForSave(Request request);

    Request RequestDtoForUpdate(Request request);

    RequestDtoForUpdate RequestForUpdate(RequestDtoForUpdate requestDtoForUpdate);

    Request Request(RequestDtoForUpdate requestDtoForUpdate);
}
