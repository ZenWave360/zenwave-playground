package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.events.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.*
import java.math.*
import java.time.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/** Service Implementation for managing [Hospital, Doctor]. */
@Service
@Transactional(readOnly = true)
open class HospitalServiceImpl(
    private val hospitalRepository: HospitalRepository,
    private val doctorRepository: DoctorRepository,
    private val eventPublisher: EventPublisher,
) : HospitalService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val hospitalServiceMapper: HospitalServiceMapper = HospitalServiceMapper.INSTANCE

    private val eventsMapper: EventsMapper = EventsMapper.INSTANCE

    override fun getHospital(id: Long): Hospital? {
        log.debug("[CRUD] Request to get Hospital : {}", id)
        val hospital = hospitalRepository.findByIdOrNull(id)
        return hospital
    }

    @Transactional
    override fun createHospital(input: Hospital): Hospital {
        log.debug("[CRUD] Request to save Hospital: {}", input)
        var hospital = hospitalServiceMapper.update(Hospital(), input)
        hospital = hospitalRepository.save(hospital)
        // TODO: may need to reload the entity to fetch relationships 'mapped by id'
        return hospital
    }

    @Transactional
    override fun updateHospital(id: Long, input: Hospital): Hospital? {
        log.debug("Request updateHospital: {} {}", id, input)

        return hospitalRepository
            .findByIdOrNull(id)
            ?.let { existingHospital -> hospitalServiceMapper.update(existingHospital, input) }
            ?.let { hospitalRepository.save(it) }
    }

    override fun listHospitals(): List<Hospital> {
        log.debug("Request listHospitals")

        val hospitals = hospitalRepository.findAll()
        return hospitals
    }

    @Transactional
    override fun createDoctor(input: Doctor): Doctor {
        log.debug("[CRUD] Request to save Doctor: {}", input)
        var doctor = hospitalServiceMapper.update(Doctor(), input)
        doctor = doctorRepository.save(doctor)
        // TODO: may need to reload the entity to fetch relationships 'mapped by id'

        // emit events
        eventPublisher.onDoctorCreated(eventsMapper.asDoctorCreated(input))
        return doctor
    }

    @Transactional
    override fun updateDoctor(id: Long, input: Doctor): Doctor? {
        log.debug("Request updateDoctor: {} {}", id, input)

        return doctorRepository
            .findByIdOrNull(id)
            ?.let { existingDoctor -> hospitalServiceMapper.update(existingDoctor, input) }
            ?.let { doctorRepository.save(it) }
    }

    override fun getDoctor(id: Long): Doctor? {
        log.debug("[CRUD] Request to get Doctor : {}", id)
        val doctor = doctorRepository.findByIdOrNull(id)
        return doctor
    }

    override fun listDoctors(): List<Doctor> {
        log.debug("Request listDoctors")

        val doctors = doctorRepository.findAll()
        return doctors
    }

    override fun listHospitalDoctors(hospitalId: Long): List<Doctor> {
        log.debug("Request listHospitalDoctors: {}", hospitalId)

        val doctors = doctorRepository.findAll()
        return doctors
    }

    override fun listHospitalPatients(hospitalId: Long): List<PatientHospital> {
        log.debug("Request listHospitalPatients: {}", hospitalId)

        val hospitals = hospitalRepository.findAll()
        return hospitalServiceMapper.asPatientHospitalList(hospitals)
    }
}
