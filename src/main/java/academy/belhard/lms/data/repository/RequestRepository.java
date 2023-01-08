package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {

}
