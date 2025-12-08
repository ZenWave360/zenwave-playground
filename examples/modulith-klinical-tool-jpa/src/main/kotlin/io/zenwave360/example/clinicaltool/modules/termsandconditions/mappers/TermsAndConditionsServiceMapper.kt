package io.zenwave360.example.clinicaltool.modules.termsandconditions.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers

@Mapper(uses = [BaseMapper::class])
interface TermsAndConditionsServiceMapper {

    companion object {
        val INSTANCE: TermsAndConditionsServiceMapper = Mappers.getMapper(TermsAndConditionsServiceMapper::class.java)
    }

    // input mappings
    // TermsAndConditions-TermsAndConditions updateTermsAndConditions

    fun asTermsAndConditions(input: TermsAndConditions): TermsAndConditions

    @Mapping(target = "id", ignore = true)
    fun update(@MappingTarget entity: TermsAndConditions, input: TermsAndConditions): TermsAndConditions

    // Langnull-TermsAndConditions getCurrentTermsAndConditions
    fun asTermsAndConditions(lang: String): TermsAndConditions {
        return TermsAndConditions().apply {
            // TODO: implement this method
            // this.lang = lang
        }
    }

    fun update(entity: TermsAndConditions, lang: String): TermsAndConditions {
        return entity.apply {
            // TODO: implement this method
            // this.lang = lang
        }
    }
    // output mappings
}
