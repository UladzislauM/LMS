package academy.belhard.lms.service.dto.request;

import academy.belhard.lms.service.dto.user.UserDtoForSaving;
import lombok.Data;

@Data
public class RequestDtoForSave {
    private CourseDto course;
    private UserDtoForSaving user;
}
