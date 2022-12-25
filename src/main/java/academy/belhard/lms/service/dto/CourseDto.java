package academy.belhard.lms.service.dto;

import academy.belhard.lms.data.entity.Lesson;
import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class CourseDto {
    private Long id;
    private List<Lesson> lessons;
    private String title;
    private String description;
    private BigDecimal price;
    private LocalDate start_date;
    private User trainer;
    private Request request;
}
