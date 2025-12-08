package io.zenwave360.example.clinicaltool.modules.surveys.web

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*
import io.zenwave360.example.clinicaltool.modules.surveys.web.*
import io.zenwave360.example.clinicaltool.modules.surveys.web.dtos.*
import io.zenwave360.example.clinicaltool.common.web.*
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
 * REST controller for SurveysBackofficeApi.
 */
@RestController
@RequestMapping("/api")
open class SurveysBackofficeApiController(
    private val surveysBackofficeService: SurveysBackofficeService
) : SurveysBackofficeApi {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var request: NativeWebRequest

    private val mapper = SurveysBackofficeDTOsMapper.INSTANCE



    override fun listSurveys(): ResponseEntity<List<SurveyDTO>> {
        log.debug("REST request to listSurveys: ")
        val survey =  surveysBackofficeService.listSurveys()
        val responseDTO = mapper.asSurveyDTOList(survey)
        return ResponseEntity.status(200).body(responseDTO)
    }

    override fun createSurvey(reqBody: SurveyDTO): ResponseEntity<SurveyDTO> {
        log.debug("REST request to createSurvey: {}", reqBody)
        val input = mapper.asSurvey(reqBody)
        val survey =  surveysBackofficeService.createSurvey(input)
        val responseDTO = mapper.asSurveyDTO(survey)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun getSurvey(id: Long): ResponseEntity<SurveyDTO> {
        log.debug("REST request to getSurvey: {}", id)
        val survey =  surveysBackofficeService.getSurvey(id)
        return if (survey != null) {
            val responseDTO = mapper.asSurveyDTO(survey)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun updateSurvey(id: Long, reqBody: SurveyDTO): ResponseEntity<SurveyDTO> {
        log.debug("REST request to updateSurvey: {}, {}", id, reqBody)
        val input = mapper.asSurvey(reqBody)
        val survey =  surveysBackofficeService.updateSurvey(id, input)
        return if (survey != null) {
            val responseDTO = mapper.asSurveyDTO(survey)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun deleteSurvey(id: Long): ResponseEntity<Unit> {
        log.debug("REST request to deleteSurvey: {}", id)
        surveysBackofficeService.deleteSurvey(id)
        return ResponseEntity.status(204).build()
    }

    override fun listQuestions(): ResponseEntity<List<QuestionDTO>> {
        log.debug("REST request to listQuestions: ")
        val question =  surveysBackofficeService.listQuestions()
        val responseDTO = mapper.asQuestionDTOList(question)
        return ResponseEntity.status(200).body(responseDTO)
    }

    override fun createQuestion(reqBody: QuestionDTO): ResponseEntity<QuestionDTO> {
        log.debug("REST request to createQuestion: {}", reqBody)
        val input = mapper.asQuestion(reqBody)
        val question =  surveysBackofficeService.createQuestion(input)
        val responseDTO = mapper.asQuestionDTO(question)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun getQuestion(id: Long): ResponseEntity<QuestionDTO> {
        log.debug("REST request to getQuestion: {}", id)
        val question =  surveysBackofficeService.getQuestion(id)
        return if (question != null) {
            val responseDTO = mapper.asQuestionDTO(question)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun updateQuestion(id: Long, reqBody: QuestionDTO): ResponseEntity<QuestionDTO> {
        log.debug("REST request to updateQuestion: {}, {}", id, reqBody)
        val input = mapper.asQuestion(reqBody)
        val question =  surveysBackofficeService.updateQuestion(id, input)
        return if (question != null) {
            val responseDTO = mapper.asQuestionDTO(question)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun deleteQuestion(id: Long): ResponseEntity<Unit> {
        log.debug("REST request to deleteQuestion: {}", id)
        surveysBackofficeService.deleteQuestion(id)
        return ResponseEntity.status(204).build()
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
