package io.zenwave360.example.clinicaltool.modules.web.webapp.mappers

import io.zenwave360.example.clinicaltool.common.mappers.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import io.zenwave360.example.clinicaltool.modules.web.webapp.dtos.*

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import java.math.*
import java.time.*
import java.util.*
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface HospitalDTOsMapper {

    companion object {
        val INSTANCE: HospitalDTOsMapper = Mappers.getMapper(HospitalDTOsMapper::class.java)
    }

    // request mappings
    fun asDoctor(dto: DoctorDTO): Doctor
    fun asHospital(dto: HospitalDTO): Hospital

    // response mappings
    
    fun asPatientHospitalDTO(entity: PatientHospital): PatientHospitalDTO
    
    
    fun asHospitalDTO(entity: Hospital): HospitalDTO
    
    
    fun asDoctorDTO(entity: Doctor): DoctorDTO
    
    
    fun asDoctorDTOList(entityList: List<Doctor>): List<DoctorDTO>
    
    fun asHospitalDTOList(entityList: List<Hospital>): List<HospitalDTO>
    
    fun asPatientHospitalDTOList(entityList: List<PatientHospital>): List<PatientHospitalDTO>

}
