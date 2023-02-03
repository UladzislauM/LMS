package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.FileLinkDto;

import java.io.InputStream;

public interface FileService {
    FileLinkDto save(InputStream is, String originalFilename);

    InputStream get(FileLinkDto fileLinkDto);
}
