package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BaseMapper.class})
public interface HospitalServiceMapper {

    HospitalServiceMapper INSTANCE = Mappers.getMapper(HospitalServiceMapper.class);

    // input mappings
    // Doctor-Doctor updateDoctor
    Doctor asDoctor(Doctor input);

    @Mapping(target = "id", ignore = true)
    Doctor update(@MappingTarget Doctor entity, Doctor input);
    // HospitalIdnull-Doctor listHospitalDoctors
    Doctor asDoctor(Long hospitalId);

    @Mapping(target = "id", ignore = true)
    Doctor update(@MappingTarget Doctor entity, Long hospitalId);
    // Hospital-Hospital updateHospital
    Hospital asHospital(Hospital input);

    @Mapping(target = "id", ignore = true)
    Hospital update(@MappingTarget Hospital entity, Hospital input);
    // HospitalIdnull-Hospital listHospitalPatients
    Hospital asHospital(Long hospitalId);

    @Mapping(target = "id", ignore = true)
    Hospital update(@MappingTarget Hospital entity, Long hospitalId);
    // output mappings
    // Hospital-PatientHospital listHospitalPatients
    PatientHospital asPatientHospital(Hospital entity);

    List<PatientHospital> asPatientHospitalList(List<Hospital> entity);
}
