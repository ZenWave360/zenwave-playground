package io.zenwave360.example.clinicaltool.modules.web.mobile.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.modules.web.mobile.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BaseMapper.class)
public interface PatientsDTOsMapper {

    PatientsDTOsMapper INSTANCE = Mappers.getMapper(PatientsDTOsMapper.class);

    // request mappings

    // response mappings

    PatientProfileDTO asPatientProfileDTO(PatientProfile entity);
}
