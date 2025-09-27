package io.zenwave360.example.clinicaltool.modules.web.webapp.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.modules.web.webapp.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BaseMapper.class)
public interface PatientsDTOsMapper {

    PatientsDTOsMapper INSTANCE = Mappers.getMapper(PatientsDTOsMapper.class);

    // request mappings
    Patient asPatient(PatientDTO dto);

    // response mappings

    PatientDTO asPatientDTO(Patient entity);
}
