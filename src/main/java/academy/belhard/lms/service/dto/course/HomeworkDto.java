package academy.belhard.lms.service.dto.course;

import academy.belhard.lms.data.entity.FileLink;
import academy.belhard.lms.data.entity.User;
import lombok.Data;

@Data
public class HomeworkDto {
    private Long id;
    private User student;
    private String comment;
    private FileLink fileLink;
    private Integer mark;
}
