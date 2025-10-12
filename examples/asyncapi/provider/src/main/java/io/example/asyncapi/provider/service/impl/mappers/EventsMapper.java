package io.example.asyncapi.provider.service.impl.mappers;

import io.example.asyncapi.provider.domain.*;
import io.example.asyncapi.provider.service.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BaseMapper.class})
public interface EventsMapper {

    EventsMapper INSTANCE = Mappers.getMapper(EventsMapper.class);
}
