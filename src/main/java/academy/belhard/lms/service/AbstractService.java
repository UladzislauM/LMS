package academy.belhard.lms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AbstractService<D> {
    Page<D> getAll(Pageable pageable);

    D getById(Long id);

    void deleteRequest(D d);

    D createRequest(D d);

    D updateRequest(D d);
}
