package io.zenwave360.example.clinicaltool.modules.termsandconditions

import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*
import io.zenwave360.example.clinicaltool.common.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.mappers.*

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
 * REST controller for TermsAndConditionsApi.
 */
@RestController
@RequestMapping("/api")
open class TermsAndConditionsApiController(
    private val termsAndConditionsService: TermsAndConditionsService
) : TermsAndConditionsApi {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var request: NativeWebRequest

    private val mapper = TermsAndConditionsDTOsMapper.INSTANCE



    override fun listTermsAndConditions(): ResponseEntity<List<TermsAndConditionsDTO>> {
        log.debug("REST request to listTermsAndConditions: ")
        val termsAndConditions =  termsAndConditionsService.listTermsAndConditions()
        val responseDTO = mapper.asTermsAndConditionsDTOList(termsAndConditions)
        return ResponseEntity.status(200).body(responseDTO)
    }

    override fun createTermsAndConditions(reqBody: TermsAndConditionsDTO): ResponseEntity<TermsAndConditionsDTO> {
        log.debug("REST request to createTermsAndConditions: {}", reqBody)
        val input = mapper.asTermsAndConditions(reqBody)
        val termsAndConditions =  termsAndConditionsService.createTermsAndConditions(input)
        val responseDTO = mapper.asTermsAndConditionsDTO(termsAndConditions)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun getTermsAndConditions(id: Long): ResponseEntity<TermsAndConditionsDTO> {
        log.debug("REST request to getTermsAndConditions: {}", id)
        val termsAndConditions =  termsAndConditionsService.getTermsAndConditions(id)
        return if (termsAndConditions != null) {
            val responseDTO = mapper.asTermsAndConditionsDTO(termsAndConditions)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun updateTermsAndConditions(id: Long, reqBody: TermsAndConditionsDTO): ResponseEntity<TermsAndConditionsDTO> {
        log.debug("REST request to updateTermsAndConditions: {}, {}", id, reqBody)
        val input = mapper.asTermsAndConditions(reqBody)
        val termsAndConditions =  termsAndConditionsService.updateTermsAndConditions(id, input)
        return if (termsAndConditions != null) {
            val responseDTO = mapper.asTermsAndConditionsDTO(termsAndConditions)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun getCurrentTermsAndConditions(lang: String): ResponseEntity<TermsAndConditionsDTO> {
        log.debug("REST request to getCurrentTermsAndConditions: {}", lang)
        val termsAndConditions =  termsAndConditionsService.getCurrentTermsAndConditions(lang)
        return if (termsAndConditions != null) {
            val responseDTO = mapper.asTermsAndConditionsDTO(termsAndConditions)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun acceptTermsAndConditions(reqBody: AcceptedTermsAndConditionsInputDTO): ResponseEntity<Unit> {
        log.debug("REST request to acceptTermsAndConditions: {}", reqBody)
        val input = mapper.asAcceptedTermsAndConditionsInput(reqBody)
        termsAndConditionsService.acceptTermsAndConditions(input)
        return ResponseEntity.status(200).build()
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
