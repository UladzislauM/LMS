package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.repository.FileLinkRepository;
import academy.belhard.lms.service.FileLinkService;
import academy.belhard.lms.service.dto.FileLinkDto;
import academy.belhard.lms.service.exception.NotFoundException;
import academy.belhard.lms.service.mapper.FileLinkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FileLinkServiceImpl implements FileLinkService {
    private static final String FILE_LINK_NOT_FOUND_MSG = "File link with id=%s not found";
    private final FileLinkRepository fileLinkRepository;
    private final FileLinkMapper fileLinkMapper;

    @Override
    public List<FileLinkDto> getAll() {
        return fileLinkRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream().
                map(fileLinkMapper::fileLinkToFileLinkDto)
                .toList();
    }

    @Override
    public FileLinkDto getById(Long id) {
        return fileLinkMapper.fileLinkToFileLinkDto(fileLinkRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(FILE_LINK_NOT_FOUND_MSG, id))));
    }

    @Override
    public FileLinkDto getByLink(String link) {
        return fileLinkMapper.fileLinkToFileLinkDto(fileLinkRepository.findByLink(link));
    }

    @Override
    public FileLinkDto create(FileLinkDto fileLinkDto) {
        return fileLinkMapper.fileLinkToFileLinkDto(fileLinkRepository.save(fileLinkMapper.fileLinkDtoToFileLink(fileLinkDto)));
    }

    @Override
    public FileLinkDto update(FileLinkDto fileLinkDto) {
        return fileLinkMapper.fileLinkToFileLinkDto(fileLinkRepository.save(fileLinkMapper.fileLinkDtoToFileLink(fileLinkDto)));
    }

    @Override
    public void delete(Long id) {
        fileLinkRepository.deleteById(id);
    }
}
