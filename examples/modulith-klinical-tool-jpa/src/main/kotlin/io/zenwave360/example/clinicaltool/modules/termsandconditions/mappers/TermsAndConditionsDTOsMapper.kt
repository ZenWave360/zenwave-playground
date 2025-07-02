package io.zenwave360.example.clinicaltool.modules.termsandconditions.mappers

import io.zenwave360.example.clinicaltool.common.mappers.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import java.math.*
import java.time.*
import java.util.*
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface TermsAndConditionsDTOsMapper {

    companion object {
        val INSTANCE: TermsAndConditionsDTOsMapper = Mappers.getMapper(TermsAndConditionsDTOsMapper::class.java)
    }

    // request mappings
    fun asTermsAndConditions(dto: TermsAndConditionsDTO): TermsAndConditions
    fun asAcceptedTermsAndConditionsInput(dto: AcceptedTermsAndConditionsInputDTO): AcceptedTermsAndConditionsInput

    // response mappings
    
    fun asTermsAndConditionsDTO(entity: TermsAndConditions): TermsAndConditionsDTO
    
    
    fun asTermsAndConditionsDTOList(entityList: List<TermsAndConditions>): List<TermsAndConditionsDTO>

}
