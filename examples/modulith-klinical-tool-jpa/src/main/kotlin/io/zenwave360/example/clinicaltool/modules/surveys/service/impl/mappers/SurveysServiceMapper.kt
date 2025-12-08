package io.zenwave360.example.clinicaltool.modules.surveys.service.impl.mappers

import io.zenwave360.example.clinicaltool.common.mappers.BaseMapper
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers

@Mapper(uses = [BaseMapper::class])
interface SurveysServiceMapper {

    companion object {
        val INSTANCE: SurveysServiceMapper = Mappers.getMapper(SurveysServiceMapper::class.java)
    }

    // input mappings
    // SurveyByNameAndPatientnull-SurveyAnswers getSurveyAndQuestionsForPatient
    fun asSurveyAnswers(name: String, patientId: Long, lang: String?): SurveyAnswers {
        return SurveyAnswers().apply {
            // TODO: implement this method
            // this.name = name
            // this.patientId = patientId
            // this.lang = lang
        }
    }

    fun update(entity: SurveyAnswers, name: String, patientId: Long, lang: String?): SurveyAnswers {
        return entity.apply {
            // TODO: implement this method
            // this.name = name
            // this.patientId = patientId
            // this.lang = lang
        }
    }

    // SurveyAnswers-SurveyAnswers answerSurvey

    fun asSurveyAnswers(input: SurveyAnswers): SurveyAnswers

    @Mapping(target = "id", ignore = true)
    fun update(@MappingTarget entity: SurveyAnswers, input: SurveyAnswers): SurveyAnswers

    // java.util.Map-SurveyAnswers updateSurveyAnswers
    fun asSurveyAnswers(input: Map<String, Any?>): SurveyAnswers {
        return SurveyAnswers().apply {
            // TODO: implement this method
        }
    }

    fun update(entity: SurveyAnswers, input: Map<String, Any?>): SurveyAnswers {
        return entity.apply {
            // TODO: implement this method
        }
    }

    // output mappings
    // SurveyAnswers-SurveyAndQuestions getSurveyAndQuestionsForPatient
    fun asSurveyAndQuestions(entity: SurveyAnswers): SurveyAndQuestions
}
