package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*

import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface HospitalServiceMapper {

    companion object {
        val INSTANCE: HospitalServiceMapper = Mappers.getMapper(HospitalServiceMapper::class.java)
    }

// input mappings
    // Doctor-Doctor updateDoctor
    fun asDoctor(input: Doctor): Doctor
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: Doctor, input: Doctor): Doctor
    // HospitalIdnull-Doctor listHospitalDoctors
    fun asDoctor(hospitalId: Long): Doctor
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: Doctor, hospitalId: Long): Doctor
    // Hospital-Hospital updateHospital
    fun asHospital(input: Hospital): Hospital
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: Hospital, input: Hospital): Hospital
    // HospitalIdnull-Hospital listHospitalPatients
    fun asHospital(hospitalId: Long): Hospital
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: Hospital, hospitalId: Long): Hospital
// output mappings
    // Hospital-PatientHospital listHospitalPatients
    fun asPatientHospital(entity: Hospital): PatientHospital
    fun asPatientHospitalList(entity: List<Hospital>): List<PatientHospital>
}
