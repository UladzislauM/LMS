package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.FileLink;
import academy.belhard.lms.service.dto.FileLinkDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileLinkMapper {
    FileLinkDto fileLinkToFileLinkDto(FileLink fileLink);

    FileLink fileLinkDtoToFileLink(FileLinkDto fileLinkDto);
}
