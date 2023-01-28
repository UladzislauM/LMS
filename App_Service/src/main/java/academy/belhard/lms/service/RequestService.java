package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForSave;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RequestService {
    Page<RequestDto> getAll(Pageable pageable);

    Page<RequestDto> getAllForStudent(Pageable pageable, Long Id);

    RequestDto getById(Long id);

    RequestDto create(RequestDtoForSave dto);

    RequestDto update(RequestDtoForUpdate dto);
}
