package io.zenwave360.example.clinicaltool.modules.surveys.web

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*
import io.zenwave360.example.clinicaltool.modules.surveys.web.*
import io.zenwave360.example.clinicaltool.modules.surveys.web.dtos.*
import io.zenwave360.example.clinicaltool.modules.surveys.web.mappers.*

import java.net.URI
import java.net.URISyntaxException
import java.math.*
import java.time.*
import java.util.*
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.mapstruct.factory.Mappers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.web.context.request.NativeWebRequest


/**
 * REST controller for SurveysApi.
 */
@RestController
@RequestMapping("/api")
open class SurveysApiController(
    private val surveysService: SurveysService
) : SurveysApi {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var request: NativeWebRequest

    private val mapper = SurveysDTOsMapper.INSTANCE

    fun getRequest(): Optional<NativeWebRequest> {
        return Optional.ofNullable(request)
    }



    override fun getSurveyAndQuestionsForPatient(name: String, patientId: Long, lang: String): ResponseEntity<SurveyAndQuestionsDTO> {
        log.debug("REST request to getSurveyAndQuestionsForPatient: {}, {}, {}", name, patientId, lang)
        val surveyAndQuestions =  surveysService.getSurveyAndQuestionsForPatient(name, patientId, lang)
        val responseDTO = mapper.asSurveyAndQuestionsDTO(surveyAndQuestions)
        return ResponseEntity.status(200).body(responseDTO)
    }

    override fun answerSurvey(surveyId: Long, patientId: Long, date: LocalDate, reqBody: SurveyAnswersDTO): ResponseEntity<SurveyAnswersDTO> {
        log.debug("REST request to answerSurvey: {}, {}, {}, {}", surveyId, patientId, date, reqBody)
        val input = mapper.asSurveyAnswers(reqBody)
        val surveyAnswers =  surveysService.answerSurvey(surveyId, patientId, date, input)
        val responseDTO = mapper.asSurveyAnswersDTO(surveyAnswers)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun updateSurveyAnswers(surveyId: Long, patientId: Long, date: LocalDate, surveryId: Long, input: Map): ResponseEntity<SurveyAnswersDTO> {
        log.debug("REST request to updateSurveyAnswers: {}, {}, {}, {}, {}", surveyId, patientId, date, surveryId, input)
        val surveyAnswers =  surveysService.updateSurveyAnswers(surveyId, patientId, date, input)
        return if (surveyAnswers.isPresent) {
            val responseDTO = mapper.asSurveyAnswersDTO(surveyAnswers.get())
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun getSurveyAnswers(surveyId: Long, patientId: Long, date: LocalDate): ResponseEntity<SurveyAnswersDTO> {
        log.debug("REST request to getSurveyAnswers: {}, {}, {}", surveyId, patientId, date)
        val surveyAnswers =  surveysService.getSurveyAnswers(surveyId, patientId, date)
        return if (surveyAnswers.isPresent) {
            val responseDTO = mapper.asSurveyAnswersDTO(surveyAnswers.get())
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    protected fun pageOf(page: Int?, limit: Int?, sort: List<String>?): Pageable {
        val sortOrder = sort?.let {
            Sort.by(it.map { sortParam ->
                val parts = sortParam.split(":")
                val property = parts[0]
                val direction = if (parts.size > 1) Sort.Direction.fromString(parts[1]) else Sort.Direction.ASC
                Sort.Order(direction, property)
            })
        } ?: Sort.unsorted()
        return PageRequest.of(page ?: 0, limit ?: 10, sortOrder)
    }
}
