package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Optional<Request>> findByUser(User user);

    @Query(value = "select r from Request r where r.user.id =:id")
    Page<Request> findByStudent(Pageable pageable, @Param("id") Long id);

    @Query(value = "select r from Request r where r.status = 'PROCESSING'")
    Page<Request> findWithProcessingStatus(Pageable pageable);

    @Query(value = "select r from Request r where course.id =:id and r.status not in ('CANCELLED')")
    Page<Request> findByCourse(Pageable pageable, @Param("id") Long id);
}
