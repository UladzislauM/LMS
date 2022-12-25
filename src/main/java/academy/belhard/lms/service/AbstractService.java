package academy.belhard.lms.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AbstractService<D> {
    List<D> findAll();

    D findById(Long id);

    void delete(Long id);

    D create(D d);

    D update(D d);
}
