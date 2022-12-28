package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RequestRep extends JpaRepository<Request, Long> {

}
