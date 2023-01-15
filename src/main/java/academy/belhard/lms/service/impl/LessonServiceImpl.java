package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Homework;
import academy.belhard.lms.data.repository.HomeworkRepository;
import academy.belhard.lms.data.repository.LessonRepository;
import academy.belhard.lms.service.LessonService;
import academy.belhard.lms.service.dto.course.LessonDto;
import academy.belhard.lms.service.exception.NotFoundException;
import academy.belhard.lms.service.mapper.LessonMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LessonServiceImpl implements LessonService {
    private static final String LESSON_NOT_FOUND_MSG = "Lesson with id=%s not found";
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final HomeworkRepository homeworkRepository;

    @Override
    public List<LessonDto> getAll() {
        return lessonRepository.findAll().stream()
                .map(lessonMapper::lessonToLessonDto)
                .toList();
    }

    @Override
    public LessonDto getById(Long id) {
        return lessonMapper.lessonToLessonDto(lessonRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(LESSON_NOT_FOUND_MSG, id))));
    }

    @Override
    public LessonDto create(LessonDto lessonDto) {
        return lessonMapper.lessonToLessonDto(lessonRepository.save(lessonMapper.lessonDtoToLesson(lessonDto)));
    }

    @Override
    public LessonDto update(LessonDto lessonDto) {
        return lessonMapper.lessonToLessonDto(lessonRepository.save(lessonMapper.lessonDtoToLesson(lessonDto)));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        List<Homework> homeworks = homeworkRepository.findHomeworkByLessonId(id);
        for (Homework homework : homeworks) {
            homeworkRepository.deleteById(homework.getId());
        }
        lessonRepository.deleteLessonById(id);
    }
}
