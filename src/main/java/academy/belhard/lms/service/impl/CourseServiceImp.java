package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Homework;
import academy.belhard.lms.data.entity.Lesson;
import academy.belhard.lms.data.repository.CourseRepository;
import academy.belhard.lms.data.repository.HomeworkRepository;
import academy.belhard.lms.data.repository.LessonRepository;
import academy.belhard.lms.service.CourseService;
import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.course.CourseSimpleDto;
import academy.belhard.lms.service.exception.NotFoundException;
import academy.belhard.lms.service.mapper.CourseMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseServiceImp implements CourseService {
    private static final String COURSE_NOT_FOUND_MSG = "Course with id=%s not found";
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final LessonRepository lessonRepository;
    private final HomeworkRepository homeworkRepository;

    @Override
    public List<CourseSimpleDto> getAll() {
        return courseRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(courseMapper::courseToCourseReadDto)
                .toList();
    }

    @Override
    public CourseDto getById(Long id) {
        return courseMapper.courseToCourseDto(courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(COURSE_NOT_FOUND_MSG, id))));
    }

    @Override
    public CourseSimpleDto getSimpleById(Long id) {
        return courseMapper.courseToCourseReadDto(courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(COURSE_NOT_FOUND_MSG, id))));
    }

    @Override
    public CourseDto create(CourseDto courseDto) {
        return courseMapper.courseToCourseDto(courseRepository.save(courseMapper.courseDtoToCourse(courseDto)));
    }

    @Override
    public CourseDto update(CourseDto courseDto) {
        return courseMapper.courseToCourseDto(courseRepository.save(courseMapper.courseDtoToCourse(courseDto)));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        List<Lesson> lessons = lessonRepository.findLessonByCourseId(id);
        for (Lesson lesson : lessons) {
            lessonRepository.deleteLessonById(lesson.getId());
            List<Homework> homeworks = homeworkRepository.findHomeworkByLessonId(lesson.getId());
            for (Homework homework: homeworks) {
                homeworkRepository.deleteById(homework.getId());
            }
        }
        courseRepository.deleteCourseById(id);
    }
}
