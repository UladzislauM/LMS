package academy.belhard.lms.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PROCESSING,
        APPROVED,
        PAID,
        SATISFIED,
        CANCELLED
    }
}
