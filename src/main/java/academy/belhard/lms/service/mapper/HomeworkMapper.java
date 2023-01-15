package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.Homework;
import academy.belhard.lms.service.dto.course.HomeworkDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HomeworkMapper {
    HomeworkDto homeworkToHomeworkDto(Homework homework);

    Homework homeworkDtoToHomework(HomeworkDto homeworkDto);
}
