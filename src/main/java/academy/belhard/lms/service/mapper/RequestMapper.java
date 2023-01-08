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
    Request toRequest(RequestDto requestDto);

    RequestDto toRequestDto(Request request);

    Course toCourse(CourseDto courseDto);

    CourseDto toCourseDto(Course course);

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    Request toRequestDtoForSave(RequestDtoForSave requestDtoForSaving);

    RequestDtoForSave toRequestForSave(Request request);

    Request toRequestDtoForUpdate(Request request);

    RequestDtoForUpdate toRequestForUpdate(RequestDtoForUpdate requestDtoForUpdate);

    Request toRequest(RequestDtoForUpdate requestDtoForUpdate);
}
