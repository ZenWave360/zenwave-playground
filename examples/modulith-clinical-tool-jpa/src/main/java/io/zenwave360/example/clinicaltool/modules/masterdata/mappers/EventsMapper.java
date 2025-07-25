package io.zenwave360.example.clinicaltool.modules.masterdata.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper;
import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(uses = { BaseMapper.class })
public interface EventsMapper {

    EventsMapper INSTANCE = Mappers.getMapper(EventsMapper.class);
}
