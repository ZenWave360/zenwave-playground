package io.zenwave360.example.clinicaltool.modules.web.webapp

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import io.zenwave360.example.clinicaltool.modules.web.webapp.*
import io.zenwave360.example.clinicaltool.modules.web.webapp.dtos.*
import io.zenwave360.example.clinicaltool.common.adapters.web.*
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

/**
 * REST controller for HospitalApi.
 */
@RestController
@RequestMapping("/api")
open class HospitalApiController(
    private val hospitalService: HospitalService
) : HospitalApi {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var request: NativeWebRequest

    private val mapper = HospitalDTOsMapper.INSTANCE



    override fun getHospital(id: Long): ResponseEntity<HospitalDTO> {
        log.debug("REST request to getHospital: {}", id)
        val hospital =  hospitalService.getHospital(id)
        return if (hospital != null) {
            val responseDTO = mapper.asHospitalDTO(hospital)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun updateHospital(id: Long, reqBody: HospitalDTO): ResponseEntity<HospitalDTO> {
        log.debug("REST request to updateHospital: {}, {}", id, reqBody)
        val input = mapper.asHospital(reqBody)
        val hospital =  hospitalService.updateHospital(id, input)
        return if (hospital != null) {
            val responseDTO = mapper.asHospitalDTO(hospital)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun createHospital(reqBody: HospitalDTO): ResponseEntity<HospitalDTO> {
        log.debug("REST request to createHospital: {}", reqBody)
        val input = mapper.asHospital(reqBody)
        val hospital =  hospitalService.createHospital(input)
        val responseDTO = mapper.asHospitalDTO(hospital)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun listHospitals(): ResponseEntity<List<HospitalDTO>> {
        log.debug("REST request to listHospitals: ")
        val hospital =  hospitalService.listHospitals()
        val responseDTO = mapper.asHospitalDTOList(hospital)
        return ResponseEntity.status(200).body(responseDTO)
    }

    override fun createDoctor(reqBody: DoctorDTO): ResponseEntity<DoctorDTO> {
        log.debug("REST request to createDoctor: {}", reqBody)
        val input = mapper.asDoctor(reqBody)
        val doctor =  hospitalService.createDoctor(input)
        val responseDTO = mapper.asDoctorDTO(doctor)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun listDoctors(): ResponseEntity<List<DoctorDTO>> {
        log.debug("REST request to listDoctors: ")
        val doctor =  hospitalService.listDoctors()
        val responseDTO = mapper.asDoctorDTOList(doctor)
        return ResponseEntity.status(200).body(responseDTO)
    }

    override fun updateDoctor(id: Long, reqBody: DoctorDTO): ResponseEntity<DoctorDTO> {
        log.debug("REST request to updateDoctor: {}, {}", id, reqBody)
        val input = mapper.asDoctor(reqBody)
        val doctor =  hospitalService.updateDoctor(id, input)
        return if (doctor != null) {
            val responseDTO = mapper.asDoctorDTO(doctor)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun getDoctor(id: Long): ResponseEntity<DoctorDTO> {
        log.debug("REST request to getDoctor: {}", id)
        val doctor =  hospitalService.getDoctor(id)
        return if (doctor != null) {
            val responseDTO = mapper.asDoctorDTO(doctor)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun listHospitalDoctors(hospitalId: Long): ResponseEntity<List<DoctorDTO>> {
        log.debug("REST request to listHospitalDoctors: {}", hospitalId)
        val doctor =  hospitalService.listHospitalDoctors(hospitalId)
        val responseDTO = mapper.asDoctorDTOList(doctor)
        return ResponseEntity.status(200).body(responseDTO)
    }

    override fun listHospitalPatients(hospitalId: Long): ResponseEntity<List<PatientHospitalDTO>> {
        log.debug("REST request to listHospitalPatients: {}", hospitalId)
        val patientHospital =  hospitalService.listHospitalPatients(hospitalId)
        val responseDTO = mapper.asPatientHospitalDTOList(patientHospital)
        return ResponseEntity.status(200).body(responseDTO)
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
