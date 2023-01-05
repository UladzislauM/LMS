package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.request.RequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component
public interface RequestService {
    Page<RequestDto> getAll(Pageable pageable);

    RequestDto getById(Long id);

    void delete(RequestDto d);

    RequestDto create(RequestDto d);

    RequestDto update(RequestDto d);

    List<Integer> getPageNumbers(Model model, int totalPages);
}
