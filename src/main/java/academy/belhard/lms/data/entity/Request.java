package academy.belhard.lms.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courses_id")
    private Course course;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusReq statusReq;

    @Column(name = "is_deleted")
    private boolean deleted;

    public enum StatusReq {
        IN_PROCESSING, ASSEMBLED, AWAITING_PAYMENT, READY_TO_START, PAID_FOR, COMPLETELY_CHANGED, FAILED, REFUND, THE_TRANSACTION_IS_COMPLETED, CANCELLED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Request request = (Request) o;
        return id != null && Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
