package academy.belhard.lms.service.dto.course;

import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.service.dto.FileLinkDto;
import lombok.Data;

@Data
public class HomeworkDto {
    private Long id;
    private User student;
    private String comment;
    private FileLinkDto fileLink;
    private Integer mark;
}
