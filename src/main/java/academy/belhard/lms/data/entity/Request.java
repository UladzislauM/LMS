package academy.belhard.lms.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course")
    private Course course;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusReq statusReq;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id) && Objects.equals(course, request.course) && Objects.equals(user, request.user) && statusReq == request.statusReq;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, course, user, statusReq);
    }
}
