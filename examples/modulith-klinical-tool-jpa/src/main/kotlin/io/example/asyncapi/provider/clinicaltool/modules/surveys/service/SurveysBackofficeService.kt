package io.example.asyncapi.provider.clinicaltool.modules.surveys.service

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*
import java.math.*
import java.time.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

/**
 * Inbound Service Port for managing [Survey, Question].
 */
@org.springframework.modulith.NamedInterface("SurveysBackofficeService")
interface SurveysBackofficeService {


         /**
      *
      *
      */

     fun listSurveys(): List<Survey>


         /**
      *
      *
      */

     fun getSurvey(id: Long): Optional<Survey>


         /**
      *
      *
      */

     fun createSurvey(input: Survey): Survey


         /**
      *
      *
      */

     fun updateSurvey(id: Long, input: Survey): Optional<Survey>


         /**
      *
      *
      */

     fun deleteSurvey(id: Long): Unit


         /**
      *
      *
      */

     fun listQuestions(): List<Question>


         /**
      *
      *
      */

     fun getQuestion(id: Long): Optional<Question>


         /**
      *
      *
      */

     fun createQuestion(input: Question): Question


         /**
      *
      *
      */

     fun updateQuestion(id: Long, input: Question): Optional<Question>


         /**
      *
      *
      */

     fun deleteQuestion(id: Long): Unit



}
