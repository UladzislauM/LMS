package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    @Query(value = "SELECT * FROM homeworks WHERE lesson_id = :id", nativeQuery = true)
    List<Homework> findHomeworkByLessonId(Long id);
}