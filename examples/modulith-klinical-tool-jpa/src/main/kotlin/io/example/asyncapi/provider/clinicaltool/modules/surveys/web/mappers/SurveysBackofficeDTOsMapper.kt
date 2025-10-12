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
interface SurveysBackofficeDTOsMapper {

    companion object {
        val INSTANCE: SurveysBackofficeDTOsMapper = Mappers.getMapper(SurveysBackofficeDTOsMapper::class.java)
    }

    // request mappings
    fun asSurvey(dto: SurveyDTO): Survey
    fun asQuestion(dto: QuestionDTO): Question

    // response mappings

    fun asQuestionDTOList(entityList: List<Question>): List<QuestionDTO>

    fun asQuestionDTO(entity: Question): QuestionDTO


    fun asSurveyDTOList(entityList: List<Survey>): List<SurveyDTO>

    fun asSurveyDTO(entity: Survey): SurveyDTO


}
