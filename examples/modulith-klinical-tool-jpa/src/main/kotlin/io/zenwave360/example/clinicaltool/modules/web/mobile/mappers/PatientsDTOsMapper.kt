package io.zenwave360.example.clinicaltool.modules.web.mobile.mappers

import io.zenwave360.example.clinicaltool.common.mappers.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import io.zenwave360.example.clinicaltool.modules.web.mobile.dtos.*

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import java.math.*
import java.time.*
import java.util.*
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface PatientsDTOsMapper {

    companion object {
        val INSTANCE: PatientsDTOsMapper = Mappers.getMapper(PatientsDTOsMapper::class.java)
    }

    // request mappings

    // response mappings
    
    fun asPatientProfileDTO(entity: PatientProfile): PatientProfileDTO
    

}
