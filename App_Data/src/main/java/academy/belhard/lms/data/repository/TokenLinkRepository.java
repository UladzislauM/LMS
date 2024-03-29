package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.TokenLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TokenLinkRepository extends JpaRepository<TokenLink, Long> {

    @Query("select t from TokenLink t where t.token = :token")
    Optional<TokenLink> findByEmailToken(@Param("token") String token);
}
