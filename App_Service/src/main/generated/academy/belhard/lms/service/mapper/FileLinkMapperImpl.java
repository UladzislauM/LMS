package academy.belhard.lms.service.mapper;

import academy.belhard.lms.data.entity.FileLink;
import academy.belhard.lms.service.dto.FileLinkDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-27T10:21:36+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.3.1 (Oracle Corporation)"
)
@Component
public class FileLinkMapperImpl implements FileLinkMapper {

    @Override
    public FileLinkDto fileLinkToFileLinkDto(FileLink fileLink) {
        if ( fileLink == null ) {
            return null;
        }

        FileLinkDto fileLinkDto = new FileLinkDto();

        fileLinkDto.setId( fileLink.getId() );
        fileLinkDto.setLink( fileLink.getLink() );

        return fileLinkDto;
    }

    @Override
    public FileLink fileLinkDtoToFileLink(FileLinkDto fileLinkDto) {
        if ( fileLinkDto == null ) {
            return null;
        }

        FileLink fileLink = new FileLink();

        fileLink.setId( fileLinkDto.getId() );
        fileLink.setLink( fileLinkDto.getLink() );

        return fileLink;
    }
}
