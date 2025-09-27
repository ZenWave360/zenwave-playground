package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers;

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BaseMapper.class})
public interface PatientsServiceMapper {

    PatientsServiceMapper INSTANCE = Mappers.getMapper(PatientsServiceMapper.class);

    // input mappings
    // java.util.Map-Patient partialPatientUpdate
    Patient asPatient(java.util.Map input);

    @Mapping(target = "id", ignore = true)
    Patient update(@MappingTarget Patient entity, java.util.Map input);
    // Patient-Patient updatePatient
    Patient asPatient(Patient input);

    @Mapping(target = "id", ignore = true)
    Patient update(@MappingTarget Patient entity, Patient input);
    // output mappings
    // Patient-PatientProfile getPatientProfileById
    PatientProfile asPatientProfile(Patient entity);

    @AfterMapping
    default void manageRelationships(@MappingTarget Patient entity) {
        if (entity.getMedicalContacts() != null) {
            entity.getMedicalContacts().forEach(medicalContacts -> medicalContacts.setPatient(entity));
        }
        if (entity.getPersonalContacts() != null) {
            entity.getPersonalContacts().forEach(personalContacts -> personalContacts.setPatient(entity));
        }
        if (entity.getPatientAddresses() != null) {
            entity.getPatientAddresses().forEach(patientAddresses -> patientAddresses.setPatient(entity));
        }
        if (entity.getHospitalAddresses() != null) {
            entity.getHospitalAddresses().forEach(hospitalAddresses -> hospitalAddresses.setPatient(entity));
        }
        if (entity.getPatientWearables() != null) {
            entity.getPatientWearables().forEach(patientWearables -> patientWearables.setPatient(entity));
        }
    }
}
