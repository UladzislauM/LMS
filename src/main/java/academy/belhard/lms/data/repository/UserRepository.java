package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
