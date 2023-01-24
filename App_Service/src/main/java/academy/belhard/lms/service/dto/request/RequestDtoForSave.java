package academy.belhard.lms.service.dto.request;

import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.user.UserDto;
import lombok.Data;

@Data
public class RequestDtoForSave {
    private CourseDto course;
    private UserDto user;
}
