package academy.belhard.lms.service.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusReqDto {
    IN_PROCESSING,
    ASSEMBLED,
    AWAITING_PAYMENT,
    READY_TO_START,
    PAID_FOR,
    COMPLETELY_CHANGED,
    FAILED,
    REFUND,
    THE_TRANSACTION_IS_COMPLETED,
    CANCELLED
}
