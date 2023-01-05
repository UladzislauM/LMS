package academy.belhard.lms.service.dto.request;

import academy.belhard.lms.service.dto.user.UserDto;
import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private CourseDto course;
    private UserDto user;
    private StatusReqDto statusReq;
    private boolean deleted;
}
