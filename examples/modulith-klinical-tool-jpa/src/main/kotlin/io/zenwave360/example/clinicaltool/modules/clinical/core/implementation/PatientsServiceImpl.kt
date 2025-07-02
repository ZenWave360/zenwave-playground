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
 * Service Implementation for managing [Patient, ProvisionalPatient].
 */
@Service
@Transactional(readOnly = true)
open class PatientsServiceImpl(

    private val patientRepository: PatientRepository,

    private val provisionalPatientRepository: ProvisionalPatientRepository


    , private val eventPublisher: EventPublisher

) : PatientsService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val patientsServiceMapper: PatientsServiceMapper = PatientsServiceMapper.INSTANCE


    private val eventsMapper: EventsMapper = EventsMapper.INSTANCE




    override  fun loadPatient(phoneNumber: String, hisNumber: String): Patient
 {
    log.debug("Request loadPatient: {} {}", phoneNumber, hisNumber);

    return patientRepository.findByPhoneNumberAndHisNumber(phoneNumber, hisNumber).orElseThrow()


}


    override  fun partialPatientUpdate(phoneNumber: String, hisNumber: String, input: java.util.Map<String,Any?>): Patient
 {
    log.debug("Request partialPatientUpdate: {} {} {}", phoneNumber, hisNumber, input);

    val patient = patientRepository.findByPhoneNumberAndHisNumber(phoneNumber, hisNumber).map { existingPatient ->
        patientsServiceMapper.update(existingPatient, input)
    }
    .map { patientRepository.save(it) }
    .orElseThrow()
    return patient


}

    @Transactional

    override  fun createPatient(input: Patient): Patient
 {
    log.debug("[CRUD] Request to save Patient: {}", input)
    var patient = patientsServiceMapper.update(Patient(), input)
    patient = patientRepository.save(patient)
    // TODO: may need to reload the entity to fetch relationships 'mapped by id'
    
        // emit events
            val patientCreated = eventsMapper.asPatientCreated(input)
            eventPublisher.onPatientCreated(patientCreated)
    return patient


}

    @Transactional

    override  fun updatePatient(id: Long, input: Patient): Optional<Patient>
 {
    log.debug("Request updatePatient: {} {}", id, input);

    val patient = patientRepository.findById(id).map { existingPatient ->
        patientsServiceMapper.update(existingPatient, input)
    }
    .map { patientRepository.save(it) }
    return patient


}


    override  fun getPatient(id: Long): Optional<Patient>
 {
    log.debug("[CRUD] Request to get Patient : {}", id)
    val patient = patientRepository.findById(id)
    return patient


}


    override  fun getPatientProfileById(id: Long): PatientProfile
 {
    log.debug("Request getPatientProfileById: {}", id);

    return patientRepository.findById(id).map { patientsServiceMapper.asPatientProfile(it) }.orElseThrow()


}


    override  fun requestOptOut(id: Long): Unit
 {
    log.debug("Request requestOptOut: {}", id);

    val patient = Patient()
    // TODO: implement this method



}


    override  fun associateDocumentWithPatient(input: DocumentSignatureRequestedInput): Unit
 {
    log.debug("Request associateDocumentWithPatient: {}", input);

    val patient = Patient()
    // TODO: implement this method



}



}
