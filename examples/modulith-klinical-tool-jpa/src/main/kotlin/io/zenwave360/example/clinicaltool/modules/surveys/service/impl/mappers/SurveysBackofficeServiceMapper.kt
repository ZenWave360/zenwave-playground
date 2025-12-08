package io.zenwave360.example.clinicaltool.modules.surveys.service.impl.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers

@Mapper(uses = [BaseMapper::class])
interface SurveysBackofficeServiceMapper {

    companion object {
        val INSTANCE: SurveysBackofficeServiceMapper = Mappers.getMapper(SurveysBackofficeServiceMapper::class.java)
    }

    // input mappings
    // Survey-Survey updateSurvey

    fun asSurvey(input: Survey): Survey

    @Mapping(target = "id", ignore = true) fun update(@MappingTarget entity: Survey, input: Survey): Survey

    // Question-Question updateQuestion

    fun asQuestion(input: Question): Question

    @Mapping(target = "id", ignore = true) fun update(@MappingTarget entity: Question, input: Question): Question
    // output mappings
}
