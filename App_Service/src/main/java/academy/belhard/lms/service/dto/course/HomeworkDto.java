package academy.belhard.lms.service.dto.course;

import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.dto.user.UserDto;
import lombok.Data;

@Data
public class HomeworkDto {
    private Long id;
    private UserDto student;
    private String comment;
    private FileLinkDto fileLink;
    private Integer mark;
}
