package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BaseMapper.class})
public interface EventsMapper {

    EventsMapper INSTANCE = Mappers.getMapper(EventsMapper.class);

    io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.PatientCreated asPatientCreated(
            Patient input);

    io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.DoctorCreated asDoctorCreated(Doctor input);
}
