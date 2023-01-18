package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.FileLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileLinkRepository extends JpaRepository<FileLink, Long> {
}
