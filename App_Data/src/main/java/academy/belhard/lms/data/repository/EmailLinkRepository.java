package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.EmailLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailLinkRepository extends JpaRepository<EmailLink, Long> {
}
