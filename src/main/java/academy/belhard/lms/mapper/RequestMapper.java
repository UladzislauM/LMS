package academy.belhard.lms.mapper;

import academy.belhard.lms.data.entity.Request;
import academy.belhard.lms.service.dto.RequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RequestMapper {
    public abstract Request toRequest(RequestDto requestDto);

    public abstract RequestDto toRequestDto(Request request);
}
