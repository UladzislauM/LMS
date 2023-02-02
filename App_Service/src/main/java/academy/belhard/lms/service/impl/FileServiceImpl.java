package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.FileLink;
import academy.belhard.lms.data.repository.FileLinkRepository;
import academy.belhard.lms.service.FileService;
import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.mapper.FileLinkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
@Service("fileService")
public class FileServiceImpl implements FileService {
    private static final String LOCAL_STORAGE_PATH = "files";
    private final FileLinkRepository fileLinkRepository;
    private final FileLinkMapper fileLinkMapper;

    public FileLinkDto save(InputStream is, String extension) {
        try {
            String name = System.currentTimeMillis() + "." + extension;
            Path path = Path.of(LOCAL_STORAGE_PATH, name);
            Files.copy(is, path);
            FileLinkDto fileLinkDto = new FileLinkDto();
            fileLinkDto.setLink(name);
            fileLinkRepository.save(fileLinkMapper.fileLinkDtoToFileLink(fileLinkDto));
            return fileLinkDto;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream get(FileLink fileLink) {
        try {
            return Files.newInputStream(Path.of(LOCAL_STORAGE_PATH, fileLink.getLink()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
