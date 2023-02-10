package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Modifying
    @Query("update Course c set c.deleted = true where c.id = :id")
    void deleteCourseById(@Param("id") Long id);

    @Query("select c from Course c where c.deleted = false")
    Page<Course> findAll(Pageable pageable);

    @Query(value = "select c from Course c join Request r on r.course = c.id where r.user.id =:id " +
            "and c.deleted = false and r.status not in ('CANCELLED')")
    Page<Course> findCourseByStudentId(Pageable pageable, @Param("id") Long id);

    @Query(value = "select c from Course c where c.trainer.id =:id and c.deleted = false")
    Page<Course> findCourseByTrainerId(Pageable pageable, @Param("id") Long id);

    @Query(value = "SELECT c.* FROM courses c JOIN lessons l ON l.course_id = c.id WHERE l.id = :id AND c.deleted = FALSE",
            nativeQuery = true)
    Course findCourseByLessonId(@Param("id") Long id);
}
