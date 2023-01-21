package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<Request> findByUser(User user);
}
