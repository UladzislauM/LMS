package academy.belhard.lms.service.impl;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.service.dto.RequestDto;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperR {
    public Request toRequest(RequestDto requestDto) {
        Request request = new Request();
        request.setId(requestDto.getId());
        request.setDeleted(requestDto.isDeleted());
        request.setStatusReq(requestDto.getStatusReq());
        return request;
    }

    public RequestDto toRequestDto(Request request) {
        RequestDto requestDto = new RequestDto();
        requestDto.setId(request.getId());
        requestDto.setDeleted(request.isDeleted());
        requestDto.setStatusReq(request.getStatusReq());
        return requestDto;
    }
}
