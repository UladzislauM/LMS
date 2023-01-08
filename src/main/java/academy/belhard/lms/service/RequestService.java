package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.request.RequestDto;
import academy.belhard.lms.service.dto.request.RequestDtoForSave;
import academy.belhard.lms.service.dto.request.RequestDtoForUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RequestService {
    Page<RequestDto> getAll(Pageable pageable);

    RequestDto getById(Long id);

    RequestDto create(RequestDtoForSave dto);

    RequestDto update(RequestDtoForUpdate dto);
}
