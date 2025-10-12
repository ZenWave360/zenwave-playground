package io.example.asyncapi.provider.clinicaltool.modules.web.webapp


import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import io.zenwave360.example.clinicaltool.modules.web.webapp.*
import io.zenwave360.example.clinicaltool.modules.web.webapp.dtos.*
import io.zenwave360.example.clinicaltool.modules.web.webapp.mappers.*

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

private typealias PatchMap = java.util.Map<String, Any?>

/**
 * REST controller for PatientsApi.
 */
@RestController
@RequestMapping("/api")
open class PatientsApiController(
    private val patientsService: PatientsService
) : PatientsApi {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var request: NativeWebRequest

    private val mapper = PatientsDTOsMapper.INSTANCE

    fun getRequest(): Optional<NativeWebRequest> {
        return Optional.ofNullable(request)
    }



    override fun loadPatient(hisNumber: String, phoneNumber: String): ResponseEntity<PatientDTO> {
        log.debug("REST request to loadPatient: {}, {}", hisNumber, phoneNumber)
        val patient =  patientsService.loadPatient(phoneNumber, hisNumber)
        val responseDTO = mapper.asPatientDTO(patient)
        return ResponseEntity.status(200).body(responseDTO)
    }

    override fun partialPatientUpdate(hisNumber: String, phoneNumber: String, input: PatchMap): ResponseEntity<PatientDTO> {
        log.debug("REST request to partialPatientUpdate: {}, {}, {}", hisNumber, phoneNumber, input)
        val patient =  patientsService.partialPatientUpdate(phoneNumber, hisNumber, input)
        val responseDTO = mapper.asPatientDTO(patient)
        return ResponseEntity.status(200).body(responseDTO)
    }

    override fun createPatient(reqBody: PatientDTO): ResponseEntity<PatientDTO> {
        log.debug("REST request to createPatient: {}", reqBody)
        val input = mapper.asPatient(reqBody)
        val patient =  patientsService.createPatient(input)
        val responseDTO = mapper.asPatientDTO(patient)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun updatePatient(id: Long, reqBody: PatientDTO): ResponseEntity<PatientDTO> {
        log.debug("REST request to updatePatient: {}, {}", id, reqBody)
        val input = mapper.asPatient(reqBody)
        val patient =  patientsService.updatePatient(id, input)
        return if (patient.isPresent) {
            val responseDTO = mapper.asPatientDTO(patient.get())
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun getPatient(id: Long): ResponseEntity<PatientDTO> {
        log.debug("REST request to getPatient: {}", id)
        val patient =  patientsService.getPatient(id)
        return if (patient.isPresent) {
            val responseDTO = mapper.asPatientDTO(patient.get())
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun requestOptOut(id: Long): ResponseEntity<Unit> {
        log.debug("REST request to requestOptOut: {}", id)
        patientsService.requestOptOut(id)
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
