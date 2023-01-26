package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.EmailLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmailLinkRepository extends JpaRepository<EmailLink, Long> {

    @Query("select e from EmailLink e where e.emailToken = :emailToken")
    EmailLink findByEmailToken(@Param("emailToken") String emailToken);
}
