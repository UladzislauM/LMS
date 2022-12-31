package academy.belhard.lms.service;

import academy.belhard.lms.service.dto.RequestDto;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component
public interface RequestService extends AbstractService<RequestDto> {
    List<Integer> getPageNumbers(Model model, int totalPages);
}
