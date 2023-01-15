package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.course.HomeworkDto;

import java.util.List;

public interface HomeworkService {
    List<HomeworkDto> getAll();

    HomeworkDto getById(Long id);

    HomeworkDto create(HomeworkDto homeworkDto);

    HomeworkDto update(HomeworkDto homeworkDto);

    void delete(Long id);
}
