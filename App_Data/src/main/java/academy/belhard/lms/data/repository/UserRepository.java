package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users u WHERE u.email =:email AND u.is_active = true", nativeQuery = true)
    Optional<User> findByEmailActive(@Param("email") String email);

    @Query(value = "SELECT * FROM users u WHERE u.id in " +
            "(SELECT r.user_id FROM requests r WHERE r.course_id=:id and status='SATISFIED')", nativeQuery = true)
    Page<User> findAllByCourseAndRequestStatus(@Param("id") Long id, Pageable pageable);

    @Query(value = "SELECT * FROM users WHERE role = 'TRAINER' AND is_active = TRUE", nativeQuery = true)
    Page<User> findAllTrainers(Pageable pageable);
}
