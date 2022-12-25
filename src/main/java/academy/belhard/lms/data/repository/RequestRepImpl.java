package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.data.repository.impl.RequestRep;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("request_rep")
@Transactional
public class RequestRepImpl implements RequestRep {

    private static final String GET_ALL = """
            FROM Request
            """;

    public static final String DELETE_BOOK = """
            UPDATE Request
            SET deleted = true
            WHERE id = :id
            """;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Request> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Request.class, id));
    }

    @Override
    public List<Request> findAll() {
        List<Request> requests = entityManager.createQuery(GET_ALL, Request.class)
                .getResultList();
        if (requests == null) {
            return null;
        }
        return requests;
    }

    @Override
    public Request create(Request request) {
        entityManager.persist(request);
        return request;
    }

    @Override
    public Request update(Request request) {
        entityManager.merge(request);
        return request;
    }

    @Override
    public boolean delete(Long id) {
        Query query = entityManager.createQuery(DELETE_BOOK);
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }
}
