package academy.belhard.lms.service.dto.request;

import academy.belhard.lms.service.dto.user.UserDtoForSaving;
import lombok.Data;

@Data
public class RequestDtoForSaving {
    private Long id;
    private CourseDto course;
    private UserDtoForSaving user;
    private StatusReqDto statusReq;
    private boolean deleted;
}

