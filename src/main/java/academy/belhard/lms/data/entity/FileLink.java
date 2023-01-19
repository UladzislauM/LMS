package academy.belhard.lms.data.entity;

<<<<<<<< HEAD:src/main/java/academy/belhard/lms/data/entity/Course.java
import jakarta.persistence.*;
========
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
>>>>>>>> 3c48a42 (LMS-7: Create new branch):src/main/java/academy/belhard/lms/data/entity/FileLink.java
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "file_links")
public class FileLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "link")
    private String link;

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
    private boolean deleted;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "course_id")
    @ToString.Exclude
    private List<Lesson> lessons;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FileLink fileLink = (FileLink) o;
        return id != null && Objects.equals(id, fileLink.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
