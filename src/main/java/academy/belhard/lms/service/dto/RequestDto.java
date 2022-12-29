package academy.belhard.lms.service.dto;

import academy.belhard.lms.data.entity.enums.StatusReq;
import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private CourseDto courseDto;
    private UserDto userDto;
    private StatusReq statusReq;
    private boolean deleted;
}
