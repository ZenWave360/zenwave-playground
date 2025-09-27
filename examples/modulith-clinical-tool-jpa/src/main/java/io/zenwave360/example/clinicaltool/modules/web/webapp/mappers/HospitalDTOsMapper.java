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
public interface HospitalDTOsMapper {

    HospitalDTOsMapper INSTANCE = Mappers.getMapper(HospitalDTOsMapper.class);

    // request mappings
    Doctor asDoctor(DoctorDTO dto);

    Hospital asHospital(HospitalDTO dto);

    // response mappings

    HospitalDTO asHospitalDTO(Hospital entity);

    DoctorDTO asDoctorDTO(Doctor entity);

    List<DoctorDTO> asDoctorDTOList(List<Doctor> entityList);

    List<HospitalDTO> asHospitalDTOList(List<Hospital> entityList);

    List<PatientHospitalDTO> asPatientHospitalDTOList(List<PatientHospital> entityList);
}
