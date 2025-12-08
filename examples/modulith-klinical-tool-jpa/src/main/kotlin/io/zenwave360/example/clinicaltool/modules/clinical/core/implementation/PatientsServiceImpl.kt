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

/** Service Implementation for managing [Patient]. */
@Service
@Transactional(readOnly = true)
open class PatientsServiceImpl(
    private val patientRepository: PatientRepository,
    private val eventPublisher: EventPublisher,
) : PatientsService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val patientsServiceMapper: PatientsServiceMapper = PatientsServiceMapper.INSTANCE

    private val eventsMapper: EventsMapper = EventsMapper.INSTANCE

    override fun loadPatient(phoneNumber: String, hisNumber: String): Patient {
        log.debug("Request loadPatient: {} {}", phoneNumber, hisNumber)

        return patientRepository.findByPhoneNumberAndHisNumber(phoneNumber, hisNumber)
            ?: throw NoSuchElementException("Patient not found with id: phoneNumber=$phoneNumber, hisNumber=$hisNumber")
    }

    @Transactional
    override fun createPatient(input: Patient): Patient {
        log.debug("[CRUD] Request to save Patient: {}", input)
        var patient = patientsServiceMapper.update(Patient(), input)
        patient = patientRepository.save(patient)
        // TODO: may need to reload the entity to fetch relationships 'mapped by id'

        // emit events
        eventPublisher.onPatientCreated(eventsMapper.asPatientCreated(patient))
        return patient
    }

    override fun partialPatientUpdate(phoneNumber: String, hisNumber: String, input: Map<String, Any?>): Patient {
        log.debug("Request partialPatientUpdate: {} {} {}", phoneNumber, hisNumber, input)

        return patientRepository
            .findByPhoneNumberAndHisNumber(phoneNumber, hisNumber)
            ?.let { existingPatient -> patientsServiceMapper.update(existingPatient, input) }
            ?.let { patientRepository.save(it) }
            ?: throw NoSuchElementException("Patient not found with id: phoneNumber=$phoneNumber, hisNumber=$hisNumber")
    }

    @Transactional
    override fun updatePatient(id: Long, input: Patient): Patient? {
        log.debug("Request updatePatient: {} {}", id, input)

        return patientRepository
            .findByIdOrNull(id)
            ?.let { existingPatient -> patientsServiceMapper.update(existingPatient, input) }
            ?.let { patientRepository.save(it) }
    }

    override fun getPatient(id: Long): Patient? {
        log.debug("[CRUD] Request to get Patient : {}", id)
        val patient = patientRepository.findByIdOrNull(id)
        return patient
    }

    override fun getPatientProfileById(id: Long): PatientProfile {
        log.debug("Request getPatientProfileById: {}", id)

        return patientRepository.findByIdOrNull(id)?.let { patientsServiceMapper.asPatientProfile(it) }
            ?: throw NoSuchElementException("Patient not found with id: $id")
    }

    override fun associateDocumentWithPatient(input: DocumentSignatureRequestedInput): Unit {
        log.debug("Request associateDocumentWithPatient: {}", input)

        val patient = Patient()
        // TODO: implement this method

    }
}
