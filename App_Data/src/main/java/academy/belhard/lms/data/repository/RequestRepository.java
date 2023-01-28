package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<Request> findByUser(User user);

    @Query(value = "SELECT * FROM requests r WHERE r.user_id =:id", nativeQuery = true)
    Page<Request> findByStudent(Pageable pageable, @Param("id") Long id);
}
