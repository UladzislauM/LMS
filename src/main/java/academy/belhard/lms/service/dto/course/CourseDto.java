package academy.belhard.lms.service.dto.course;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class CourseDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private LocalDate startDate;
    private String description;
    private List<LessonDto> lessons;
}
