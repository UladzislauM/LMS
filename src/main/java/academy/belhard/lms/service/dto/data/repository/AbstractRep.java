package academy.belhard.lms.service.dto.data.repository;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface AbstractRep<T> {
    Optional<T> findById(Long id);

    List<T> findAll();

    T create(T entity);

    T update(T entity);

    boolean delete(Long id);
}
