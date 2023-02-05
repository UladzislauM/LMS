package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.FileLinkDto;

import java.util.List;

public interface FileLinkService {
    List<FileLinkDto> getAll();

    FileLinkDto getById(Long id);

    FileLinkDto getByLink (String link);

    FileLinkDto create(FileLinkDto fileLinkDto);

    FileLinkDto update(FileLinkDto fileLinkDto);

    void delete(Long id);
}
