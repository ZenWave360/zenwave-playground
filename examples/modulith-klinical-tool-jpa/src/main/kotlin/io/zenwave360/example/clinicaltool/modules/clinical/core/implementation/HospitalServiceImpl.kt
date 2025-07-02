package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.events.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.*

import java.math.*
import java.time.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

/**
 * Service Implementation for managing [Hospital, Doctor].
 */
@Service
@Transactional(readOnly = true)
open class HospitalServiceImpl(

    private val hospitalRepository: HospitalRepository,

    private val doctorRepository: DoctorRepository


    , private val eventPublisher: EventPublisher

) : HospitalService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val hospitalServiceMapper: HospitalServiceMapper = HospitalServiceMapper.INSTANCE


    private val eventsMapper: EventsMapper = EventsMapper.INSTANCE




    override  fun getHospital(id: Long): Optional<Hospital>
 {
    log.debug("[CRUD] Request to get Hospital : {}", id)
    val hospital = hospitalRepository.findById(id)
    return hospital


}

    @Transactional

    override  fun createHospital(input: Hospital): Hospital
 {
    log.debug("[CRUD] Request to save Hospital: {}", input)
    var hospital = hospitalServiceMapper.update(Hospital(), input)
    hospital = hospitalRepository.save(hospital)
    // TODO: may need to reload the entity to fetch relationships 'mapped by id'
    return hospital


}

    @Transactional

    override  fun updateHospital(id: Long, input: Hospital): Optional<Hospital>
 {
    log.debug("Request updateHospital: {} {}", id, input);

    val hospital = hospitalRepository.findById(id).map { existingHospital ->
        hospitalServiceMapper.update(existingHospital, input)
    }
    .map { hospitalRepository.save(it) }
    return hospital


}


    override  fun listHospitals(): List<Hospital>
 {
    log.debug("Request listHospitals");

    val hospitals = hospitalRepository.findAll()
    return hospitals


}

    @Transactional

    override  fun createDoctor(input: Doctor): Doctor
 {
    log.debug("[CRUD] Request to save Doctor: {}", input)
    var doctor = hospitalServiceMapper.update(Doctor(), input)
    doctor = doctorRepository.save(doctor)
    // TODO: may need to reload the entity to fetch relationships 'mapped by id'
    
        // emit events
            val doctorCreated = eventsMapper.asDoctorCreated(input)
            eventPublisher.onDoctorCreated(doctorCreated)
    return doctor


}

    @Transactional

    override  fun updateDoctor(id: Long, input: Doctor): Optional<Doctor>
 {
    log.debug("Request updateDoctor: {} {}", id, input);

    val doctor = doctorRepository.findById(id).map { existingDoctor ->
        hospitalServiceMapper.update(existingDoctor, input)
    }
    .map { doctorRepository.save(it) }
    return doctor


}


    override  fun getDoctor(id: Long): Optional<Doctor>
 {
    log.debug("[CRUD] Request to get Doctor : {}", id)
    val doctor = doctorRepository.findById(id)
    return doctor


}


    override  fun listDoctors(): List<Doctor>
 {
    log.debug("Request listDoctors");

    val doctors = doctorRepository.findAll()
    return doctors


}


    override  fun listHospitalDoctors(hospitalId: Long): List<Doctor>
 {
    log.debug("Request listHospitalDoctors: {}", hospitalId);

    val doctors = doctorRepository.findAll()
    return doctors


}


    override  fun listHospitalPatients(hospitalId: Long): List<PatientHospital>
 {
    log.debug("Request listHospitalPatients: {}", hospitalId);

    val hospitals = hospitalRepository.findAll()
    return hospitalServiceMapper.asPatientHospitalList(hospitals)


}



}
