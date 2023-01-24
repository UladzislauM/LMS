package academy.belhard.lms.service.dto.request;

import academy.belhard.lms.service.dto.course.CourseDto;
import lombok.Data;

@Data
public class RequestDtoForUpdate {
    private Long id;
    private CourseDto course;
    private StatusDto status;
}
