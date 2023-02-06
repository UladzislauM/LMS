package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Lesson;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Modifying
    @Query("update Lesson l set l.deleted = true where l.id = :id")
    void deleteLessonById(@Param("id") Long id);

    @Query("select l from Lesson l where l.deleted = false")
    List<Lesson> findAll(Sort sort);

    @Query(value = "SELECT * FROM lessons WHERE course_id = :id", nativeQuery = true)
    List<Lesson> findLessonByCourseId(@Param("id") Long id);

    @Query(value = "SELECT * FROM lessons WHERE id = (SELECT lesson_id FROM homeworks WHERE id = :id)", nativeQuery = true)
    Optional<Lesson> findLessonByHomeworkId(@Param("id") Long id);
}
