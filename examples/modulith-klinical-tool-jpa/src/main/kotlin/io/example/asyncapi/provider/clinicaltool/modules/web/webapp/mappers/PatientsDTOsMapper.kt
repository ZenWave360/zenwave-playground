package io.example.asyncapi.provider.clinicaltool.modules.web.webapp.mappers

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
interface PatientsDTOsMapper {

    companion object {
        val INSTANCE: PatientsDTOsMapper = Mappers.getMapper(PatientsDTOsMapper::class.java)
    }

    // request mappings
    fun asPatient(dto: PatientDTO): Patient

    // response mappings

    fun asPatientDTO(entity: Patient): PatientDTO


}
