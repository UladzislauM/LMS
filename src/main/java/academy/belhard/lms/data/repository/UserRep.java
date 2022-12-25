package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRep extends AbstractRep<User> {
    boolean active(Long id, User user);

    Optional<User> login(String login, String password);
}
