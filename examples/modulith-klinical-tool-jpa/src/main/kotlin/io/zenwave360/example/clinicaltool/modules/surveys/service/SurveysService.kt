package io.zenwave360.example.clinicaltool.modules.surveys.service

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*
import java.math.*
import java.time.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

/**
 * Inbound Service Port for managing [SurveyAnswers].
 */
@org.springframework.modulith.NamedInterface("SurveysService")
interface SurveysService {


         /**
      *
      *
      */

     fun getSurveyAndQuestionsForPatient(name: String, patientId: Long, lang: String?): SurveyAndQuestions


         /**
      *
      *
      */

     fun answerSurvey(surveyId: Long, patientId: Long, date: LocalDate, input: SurveyAnswers): SurveyAnswers


         /**
      *
      *
      */

     fun updateSurveyAnswers(surveyId: Long, patientId: Long, date: LocalDate, input: java.util.Map<String,Any?>): Optional<SurveyAnswers>


         /**
      *
      *
      */

     fun getSurveyAnswers(surveyId: Long, patientId: Long, date: LocalDate): Optional<SurveyAnswers>



}
