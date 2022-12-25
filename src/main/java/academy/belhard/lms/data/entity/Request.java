package academy.belhard.lms.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "requests")
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

    @Column(name = "is_deleted")
    private boolean deleted;
}
