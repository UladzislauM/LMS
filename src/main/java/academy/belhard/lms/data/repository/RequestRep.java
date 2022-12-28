package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Request;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RequestRep extends AbstractRep<Request> {
    Optional<Request> findByCourseId (Long id);
}
