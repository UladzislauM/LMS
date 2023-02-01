package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Homework;
import academy.belhard.lms.data.repository.HomeworkRepository;
import academy.belhard.lms.service.HomeworkService;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import academy.belhard.lms.service.exception.NotFoundException;
import academy.belhard.lms.service.mapper.HomeworkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HomeworkServiceImpl implements HomeworkService {
    private static final String HOMEWORK_NOT_FOUND_MSG = "Homework with id=%s not found";
    private final HomeworkRepository homeworkRepository;
    private final HomeworkMapper homeworkMapper;

    @Override
    public Page<HomeworkDto> getAll(Pageable pageable) {
        Page<Homework> homeworks = homeworkRepository.findAll(pageable);
        return homeworks.map(homeworkMapper::homeworkToHomeworkDto);
    }

    @Override
    public Page<HomeworkDto> getAllByStudentId(Long id, Pageable pageable) {
        Page<Homework> homeworks = homeworkRepository.findAllByStudentId(id, pageable);
        return homeworks.map(homeworkMapper::homeworkToHomeworkDto);
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
