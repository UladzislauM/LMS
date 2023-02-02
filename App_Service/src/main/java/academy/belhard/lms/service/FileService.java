package academy.belhard.lms.service;

import academy.belhard.lms.data.entity.FileLink;
import academy.belhard.lms.service.dto.FileLinkDto;

import java.io.InputStream;

public interface FileService {
    FileLinkDto save(InputStream is, String extension);

    InputStream get(FileLink fileLink);
}
