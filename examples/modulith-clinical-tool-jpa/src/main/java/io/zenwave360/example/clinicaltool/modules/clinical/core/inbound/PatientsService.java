package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;

import java.util.*;

/**
 * Inbound Service Port for managing [Patient, ProvisionalPatient].
 */
@org.springframework.modulith.NamedInterface("PatientsService")
public interface PatientsService {

     /**
      * Loads a saved temporal patient or new one if not found.
      *
      */

    public Patient loadPatient(String phoneNumber, String hisNumber)
;
     /**
      * Persist a temporal patient in the database.
      *
      */

    public Patient partialPatientUpdate(String phoneNumber, String hisNumber, java.util.Map input)
;
     /**
      * Persite a patient in the database and deletes any temporal data.
      *
      * With Events: [PatientCreated].

      */

    public Patient createPatient(Patient input)
;
     /**
      * Updates a patient in the database.
      *
      */

    public Optional<Patient> updatePatient(Long id, Patient input)
;
     /**
      * Load a patient by id
      *
      */

    public Optional<Patient> getPatient(Long id)
;
     /**
      * Load a patient profile by id to mobile app.
      *
      */

    public PatientProfile getPatientProfileById(Long id)
;
     /**
      *
      *
      */

    public void requestOptOut(Long id)
;
     /**
      *
      *
      */

    public void associateDocumentWithPatient(DocumentSignatureRequestedInput input)
;


}
