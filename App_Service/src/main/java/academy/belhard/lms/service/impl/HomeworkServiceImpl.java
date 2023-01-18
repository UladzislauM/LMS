package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.repository.HomeworkRepository;
import academy.belhard.lms.service.HomeworkService;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import academy.belhard.lms.service.exception.NotFoundException;
import academy.belhard.lms.service.mapper.HomeworkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HomeworkServiceImpl implements HomeworkService {
    private static final String HOMEWORK_NOT_FOUND_MSG = "Homework with id=%s not found";
    private final HomeworkRepository homeworkRepository;
    private final HomeworkMapper homeworkMapper;

    @Override
    public List<HomeworkDto> getAll() {
        return homeworkRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream().
                map(homeworkMapper::homeworkToHomeworkDto)
                .toList();
    }

    @Override
    public HomeworkDto getById(Long id) {
        return homeworkMapper.homeworkToHomeworkDto(homeworkRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(HOMEWORK_NOT_FOUND_MSG, id))));
    }

    @Override
    public HomeworkDto create(HomeworkDto homeworkDto) {
        return homeworkMapper.homeworkToHomeworkDto(homeworkRepository.save(homeworkMapper.homeworkDtoToHomework(homeworkDto)));
    }

    @Override
    public HomeworkDto update(HomeworkDto homeworkDto) {
        return homeworkMapper.homeworkToHomeworkDto(homeworkRepository.save(homeworkMapper.homeworkDtoToHomework(homeworkDto)));
    }

    @Override
    public void delete(Long id) {
        homeworkRepository.deleteById(id);
    }
}