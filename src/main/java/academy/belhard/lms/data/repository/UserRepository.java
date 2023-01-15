package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users u WHERE u.email =:email AND u.is_active='true'", nativeQuery = true)
    Optional<User> findByEmailActive(String email);
}
