package io.zenwave360.example.clinicaltool.modules.surveys.service

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*
import java.math.*
import java.time.*

/** Inbound Service Port for managing [Survey, Question]. */
@org.springframework.modulith.NamedInterface("SurveysBackofficeService")
interface SurveysBackofficeService {

    /**  */
    fun listSurveys(): List<Survey>

    /**  */
    fun getSurvey(id: Long): Survey?

    /**  */
    fun createSurvey(input: Survey): Survey

    /**  */
    fun updateSurvey(id: Long, input: Survey): Survey?

    /**  */
    fun deleteSurvey(id: Long): Unit

    /**  */
    fun listQuestions(): List<Question>

    /**  */
    fun getQuestion(id: Long): Question?

    /**  */
    fun createQuestion(input: Question): Question

    /**  */
    fun updateQuestion(id: Long, input: Question): Question?

    /**  */
    fun deleteQuestion(id: Long): Unit
}
