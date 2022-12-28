package academy.belhard.lms.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AbstractService<D> {
    List<D> getAll();

    D getById(Long id);

    void deleteRequest(D d);

    D createRequest(D d);

    D updateRequest(D d);
}
