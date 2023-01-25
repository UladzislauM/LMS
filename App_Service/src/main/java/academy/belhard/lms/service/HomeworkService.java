package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.course.HomeworkDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HomeworkService {
    Page<HomeworkDto> getAll(Pageable pageable);

    HomeworkDto getById(Long id);

    HomeworkDto create(HomeworkDto homeworkDto);

    HomeworkDto update(HomeworkDto homeworkDto);

    void delete(Long id);
}
