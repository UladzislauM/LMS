package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.Course;
import academy.belhard.lms.service.dto.course.CourseDto;
import academy.belhard.lms.service.dto.course.CourseSimpleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseSimpleDto courseToCourseReadDto(Course course);

    Course courseReadDtoToCourse(CourseSimpleDto courseSimpleDto);

    Course courseDtoToCourse(CourseDto courseDto);

    CourseDto courseToCourseDto(Course course);
}
