package io.example.asyncapi.provider.clinicaltool.modules.surveys.service.impl.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*

import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface SurveysServiceMapper {

    companion object {
        val INSTANCE: SurveysServiceMapper = Mappers.getMapper(SurveysServiceMapper::class.java)
    }

// input mappings
    // SurveyByNameAndPatientnull-SurveyAnswers getSurveyAndQuestionsForPatient
    fun asSurveyAnswers(name: String, patientId: Long, lang: String): SurveyAnswers
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: SurveyAnswers, name: String, patientId: Long, lang: String?): SurveyAnswers
    // SurveyAnswers-SurveyAnswers answerSurvey
    fun asSurveyAnswers(input: SurveyAnswers): SurveyAnswers
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: SurveyAnswers, input: SurveyAnswers): SurveyAnswers
    // java.util.Map-SurveyAnswers updateSurveyAnswers
    fun asSurveyAnswers(input: java.util.Map<String,Any?>): SurveyAnswers
    @Mapping(target = "id", ignore = true)fun update(@MappingTarget entity: SurveyAnswers, input: java.util.Map<String,Any?>): SurveyAnswers
// output mappings
    // SurveyAnswers-SurveyAndQuestions getSurveyAndQuestionsForPatient
    fun asSurveyAndQuestions(entity: SurveyAnswers): SurveyAndQuestions
}
