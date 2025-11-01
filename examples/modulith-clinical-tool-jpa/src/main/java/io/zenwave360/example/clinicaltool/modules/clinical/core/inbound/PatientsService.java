package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * Inbound Service Port for managing [Patient, ProvisionalPatient].
 */
@org.springframework.modulith.NamedInterface("PatientsService")
public interface PatientsService {

    /**
     *
     *
     */
    public Patient loadPatient(String phoneNumber, String hisNumber);
    /**
     *
     *
     * With Events: [PatientCreated].
     *
     */
    public Patient createPatient(Patient input);
    /**
     *
     *
     */
    public Patient partialPatientUpdate(String phoneNumber, String hisNumber, java.util.Map input);
    /**
     *
     *
     */
    public Optional<Patient> updatePatient(Long id, Patient input);
    /**
     *
     *
     */
    public Optional<Patient> getPatient(Long id);
    /**
     *
     *
     */
    public PatientProfile getPatientProfileById(Long id);
    /**
     *
     *
     */
    public void associateDocumentWithPatient(DocumentSignatureRequestedInput input);
}
