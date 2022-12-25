package academy.belhard.lms.data.repository.impl;

import academy.belhard.lms.data.entity.User;
import academy.belhard.lms.data.repository.UserRep;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userRep")
@Transactional
public class UserRepImpl implements UserRep {

    private static final String GET_USER_BY_LOGIN_PAS = """
            FROM User u
            WHERE u.email = :login AND u.password = :password
            """;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean active(Long id, User user) {
        return false;
    }

    @Override
    public Optional<User> login(String login, String password) {
        return Optional.of(entityManager.createQuery(GET_USER_BY_LOGIN_PAS, User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult());
    }
}
