package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Homework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    @Query(value = "SELECT * FROM homeworks WHERE lesson_id = :id", nativeQuery = true)
    List<Homework> findHomeworkByLessonId(@Param("id") Long id);

    @Query(value = "SELECT * FROM homeworks WHERE student_id = :id", nativeQuery = true)
    Page<Homework> findAllByStudentId(@Param("id") Long id, Pageable pageable);

    @Query(value = "SELECT * FROM homeworks WHERE lesson_id = :id", nativeQuery = true)
    Optional<Homework> findByLessonId(@Param("id") Long id);
}
