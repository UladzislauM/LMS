package academy.belhard.lms.service.dto;

import academy.belhard.lms.service.dto.data.entity.StatusReq;
import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private StatusReq statusReq;
    private boolean deleted;
}
