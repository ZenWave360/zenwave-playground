package io.example.asyncapi.provider.clinicaltool.modules.surveys.web.mappers

import io.zenwave360.example.clinicaltool.common.mappers.*
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*
import io.zenwave360.example.clinicaltool.modules.surveys.web.dtos.*

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import java.math.*
import java.time.*
import java.util.*
import org.springframework.data.domain.Page

@Mapper(uses = [BaseMapper::class])
interface SurveysDTOsMapper {

    companion object {
        val INSTANCE: SurveysDTOsMapper = Mappers.getMapper(SurveysDTOsMapper::class.java)
    }

    // request mappings
    fun asSurveyAnswers(dto: SurveyAnswersDTO): SurveyAnswers

    // response mappings

    fun asSurveyAnswersDTO(entity: SurveyAnswers): SurveyAnswersDTO


    fun asSurveyAndQuestionsDTO(entity: SurveyAndQuestions): SurveyAndQuestionsDTO


}
