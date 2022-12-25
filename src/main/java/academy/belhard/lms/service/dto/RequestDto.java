package academy.belhard.lms.service.dto;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.data.entity.StatusReq;
import academy.belhard.lms.data.entity.User;
import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private Course course;
    private User user;
    private StatusReq statusReq;
    private boolean deleted;
}
