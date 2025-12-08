package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import java.math.*
import java.time.*

/** Inbound Service Port for managing [Patient]. */
@org.springframework.modulith.NamedInterface("PatientsService")
interface PatientsService {

    /**  */
    fun loadPatient(phoneNumber: String, hisNumber: String): Patient

    /** With Events: [PatientCreated]. */
    fun createPatient(input: Patient): Patient

    /**  */
    fun partialPatientUpdate(phoneNumber: String, hisNumber: String, input: Map<String, Any?>): Patient

    /**  */
    fun updatePatient(id: Long, input: Patient): Patient?

    /**  */
    fun getPatient(id: Long): Patient?

    /**  */
    fun getPatientProfileById(id: Long): PatientProfile

    /**  */
    fun associateDocumentWithPatient(input: DocumentSignatureRequestedInput): Unit
}
