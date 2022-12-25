package academy.belhard.lms.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "title", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private List<Lesson> lessons;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "start_date")
    private LocalDate start_date;

    @ManyToOne
    @JoinColumn(name = "trainer")
    private User trainer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(lessons, course.lessons) && Objects.equals(title, course.title) && Objects.equals(description, course.description) && Objects.equals(price, course.price) && Objects.equals(start_date, course.start_date) && Objects.equals(trainer, course.trainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessons, title, description, price, start_date, trainer);
    }
}
