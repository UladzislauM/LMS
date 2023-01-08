package academy.belhard.lms.service.dto.request;

import lombok.Data;

@Data
public class RequestDtoForUpdate {
    private Long id;
    private CourseDto course;
    private StatusDto status;
}
