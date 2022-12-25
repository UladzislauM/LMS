package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.repository.impl.RequestRep;
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

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Object create(Object entity) {
        return null;
    }

    @Override
    public Object update(Object entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
