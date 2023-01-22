package academy.belhard.lms.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "homeworks")
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "student_id")
    private User student;

    @Column(name = "comment")
    private String comment;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "filelink_id")
    private FileLink fileLink;

    @Column(name = "mark")
    private Integer mark;
}
