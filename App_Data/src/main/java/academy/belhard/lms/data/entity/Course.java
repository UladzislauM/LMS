package academy.belhard.lms.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "trainer_id")
    private User trainer;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private Boolean deleted = false;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "course_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Lesson> lessons;
}
