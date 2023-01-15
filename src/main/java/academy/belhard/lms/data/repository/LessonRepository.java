package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Modifying
    @Query("update Lesson l set l.deleted = true where l.id = :id")
    void deleteLessonById(Long id);

    @Query(value = "SELECT * FROM lessons WHERE course_id = :id", nativeQuery = true)
    List<Lesson> findLessonByCourseId(Long id);
}
