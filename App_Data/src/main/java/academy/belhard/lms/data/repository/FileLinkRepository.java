package academy.belhard.lms.data.repository;

import academy.belhard.lms.data.entity.FileLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FileLinkRepository extends JpaRepository<FileLink, Long> {
    @Query(value = "SELECT * FROM file_links WHERE link = :link", nativeQuery = true)
    FileLink findByLink (@Param("link") String link);
}
