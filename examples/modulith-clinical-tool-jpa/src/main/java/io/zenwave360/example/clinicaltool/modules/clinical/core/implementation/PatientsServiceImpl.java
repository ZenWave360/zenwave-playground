package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.Patient;
import io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers.EventsMapper;
import io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers.PatientsServiceMapper;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.PatientsService;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.DocumentSignatureRequestedInput;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.PatientProfile;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.events.EventPublisher;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.PatientRepository;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.ProvisionalPatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing [Patient, ProvisionalPatient].
 */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class PatientsServiceImpl implements PatientsService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final PatientsServiceMapper patientsServiceMapper = PatientsServiceMapper.INSTANCE;

    private final PatientRepository patientRepository;

    private final ProvisionalPatientRepository provisionalPatientRepository;

    private final EventsMapper eventsMapper = EventsMapper.INSTANCE;
    private final EventPublisher eventPublisher;

    public Patient loadPatient(String phoneNumber, String hisNumber) {
        log.debug("Request loadPatient: {} {}", phoneNumber, hisNumber);
        return patientRepository.findByPhoneNumberAndHisNumber(phoneNumber, hisNumber).orElseThrow();
    }

    public Patient partialPatientUpdate(String phoneNumber, String hisNumber, java.util.Map input) {
        log.debug("Request partialPatientUpdate: {} {} {}", phoneNumber, hisNumber, input);
        var patient = patientRepository.findByPhoneNumberAndHisNumber(phoneNumber, hisNumber).map(existingPatient -> {
                    return patientsServiceMapper.update(existingPatient, input);
                })
                .map(patientRepository::save)
                .orElseThrow();
        return patient;
    }

    @Transactional
    public Patient createPatient(Patient input) {
        log.debug("[CRUD] Request to save Patient: {}", input);
        var patient = patientsServiceMapper.update(new Patient(), input);
        patient = patientRepository.save(patient);
        // emit events
        var patientCreated = eventsMapper.asPatientCreated(input);
        eventPublisher.onPatientCreated(patientCreated);
        return patient;
    }

    @Transactional
    public Optional<Patient> updatePatient(Long id, Patient input) {
        log.debug("Request updatePatient: {} {}", id, input);
        var patient = patientRepository.findById(id).map(existingPatient -> {
                    return patientsServiceMapper.update(existingPatient, input);
                })
                .map(patientRepository::save);
        return patient;
    }


    public Optional<Patient> getPatient(Long id) {
        log.debug("[CRUD] Request to get Patient : {}", id);
        var patient = patientRepository.findById(id);
        return patient;
    }

    public PatientProfile getPatientProfileById(Long id) {
        log.debug("Request getPatientProfileById: {}", id);
        return patientRepository.findById(id).map(patientsServiceMapper::asPatientProfile).orElseThrow();
    }


    public void requestOptOut(Long id) {
        log.debug("Request requestOptOut: {}", id);
        var patient = new Patient();
        // TODO: implement this method
    }


    public void associateDocumentWithPatient(DocumentSignatureRequestedInput input) {
        log.debug("Request associateDocumentWithPatient: {}", input);
        var patient = new Patient();
        // TODO: implement this method
    }

}
