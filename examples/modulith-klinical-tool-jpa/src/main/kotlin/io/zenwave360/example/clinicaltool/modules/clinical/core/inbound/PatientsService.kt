package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import java.math.*
import java.time.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

/**
 * Inbound Service Port for managing [Patient, ProvisionalPatient].
 */
@org.springframework.modulith.NamedInterface("PatientsService")
interface PatientsService {


         /**
      * Loads a saved temporal patient or new one if not found.
      *
      */

     fun loadPatient(phoneNumber: String, hisNumber: String): Patient


         /**
      * Persist a temporal patient in the database.
      *
      */

     fun partialPatientUpdate(phoneNumber: String, hisNumber: String, input: java.util.Map<String,Any?>): Patient


         /**
      * Persite a patient in the database and deletes any temporal data.
      *
      * With Events: [PatientCreated].

      */

     fun createPatient(input: Patient): Patient


         /**
      * Updates a patient in the database.
      *
      */

     fun updatePatient(id: Long, input: Patient): Optional<Patient>


         /**
      * Load a patient by id
      *
      */

     fun getPatient(id: Long): Optional<Patient>


         /**
      * Load a patient profile by id to mobile app.
      *
      */

     fun getPatientProfileById(id: Long): PatientProfile


         /**
      * 
      *
      */

     fun requestOptOut(id: Long): Unit


         /**
      * 
      *
      */

     fun associateDocumentWithPatient(input: DocumentSignatureRequestedInput): Unit



}
