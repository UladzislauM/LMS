package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Course;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Modifying
    @Query("update Course c set c.deleted = true where c.id = :id")
    void deleteCourseById(Long id);

    @Query("select c from Course c where c.deleted = false")
    List<Course> findAll(Sort sort);
}
