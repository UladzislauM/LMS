package academy.belhard.lms.service.dto.request;

import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private CourseDto course;
    private UserDto user;
    private StatusReqDto statusReq;
    private boolean deleted;
}
