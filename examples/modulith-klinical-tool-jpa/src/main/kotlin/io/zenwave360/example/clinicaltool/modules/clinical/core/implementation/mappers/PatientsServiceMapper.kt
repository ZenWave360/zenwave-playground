package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers

@Mapper(uses = [BaseMapper::class])
interface PatientsServiceMapper {

    companion object {
        val INSTANCE: PatientsServiceMapper = Mappers.getMapper(PatientsServiceMapper::class.java)
    }

    // input mappings
    // DocumentSignatureRequestedInputnull-Patient associateDocumentWithPatient

    fun asPatient(input: DocumentSignatureRequestedInput): Patient

    @Mapping(target = "id", ignore = true)
    fun update(@MappingTarget entity: Patient, input: DocumentSignatureRequestedInput): Patient

    // java.util.Map-Patient partialPatientUpdate
    fun asPatient(input: Map<String, Any?>): Patient {
        return Patient().apply {
            // TODO: implement this method
        }
    }

    fun update(entity: Patient, input: Map<String, Any?>): Patient {
        return entity.apply {
            // TODO: implement this method
        }
    }

    // Patient-Patient updatePatient

    fun asPatient(input: Patient): Patient

    @Mapping(target = "id", ignore = true) fun update(@MappingTarget entity: Patient, input: Patient): Patient

    // output mappings
    // Patient-PatientProfile getPatientProfileById
    fun asPatientProfile(entity: Patient): PatientProfile

    @AfterMapping
    fun manageRelationships(@MappingTarget entity: Patient) {
        entity.medicalContacts?.forEach { medicalContacts -> medicalContacts.patient = entity }
        entity.personalContacts?.forEach { personalContacts -> personalContacts.patient = entity }
        entity.patientAddresses?.forEach { patientAddresses -> patientAddresses.patient = entity }
        entity.hospitalAddresses?.forEach { hospitalAddresses -> hospitalAddresses.patient = entity }
        entity.patientWearables?.forEach { patientWearables -> patientWearables.patient = entity }
    }
}
