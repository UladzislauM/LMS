package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.entity.enums.StatusReq;
import academy.belhard.lms.service.dto.CourseDto;
import academy.belhard.lms.service.dto.RequestDto;
import academy.belhard.lms.service.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RequestMapper {
    public abstract Request toRequest(RequestDto requestDto);

    public abstract RequestDto toRequestDto(Request request);

    public abstract Course toCourse(CourseDto courseDto);

    public abstract CourseDto toCourseDto(Course course);

    public abstract User toUser(UserDto userDto);

    public abstract UserDto toUserDto(User user);

    public abstract StatusReq toStatusReq(StatusReq statusReq);

    public abstract RequestDto toStatusReqDto(RequestDto requestDto);
}
