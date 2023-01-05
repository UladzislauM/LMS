package academy.belhard.lms.service.dto;

import academy.belhard.lms.data.entity.StatusReq;
import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private CourseDto course;
    private UserDto user;
    private StatusReq statusReq;
    private boolean deleted;
}
