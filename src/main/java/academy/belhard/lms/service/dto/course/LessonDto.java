package academy.belhard.lms.service.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LessonDto {
    private Long id;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSS")
    private LocalDateTime startTime;
    private String description;
    private String content;
    private String homeTask;
    private List<HomeworkDto> homeworks;
}
