package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import java.math.*
import java.time.*

/** Inbound Service Port for managing [Hospital, Doctor]. */
@org.springframework.modulith.NamedInterface("HospitalService")
interface HospitalService {

    /**  */
    fun getHospital(id: Long): Hospital?

    /**  */
    fun createHospital(input: Hospital): Hospital

    /**  */
    fun updateHospital(id: Long, input: Hospital): Hospital?

    /**  */
    fun listHospitals(): List<Hospital>

    /** With Events: [DoctorCreated]. */
    fun createDoctor(input: Doctor): Doctor

    /**  */
    fun updateDoctor(id: Long, input: Doctor): Doctor?

    /**  */
    fun getDoctor(id: Long): Doctor?

    /**  */
    fun listDoctors(): List<Doctor>

    /**  */
    fun listHospitalDoctors(hospitalId: Long): List<Doctor>

    /**  */
    fun listHospitalPatients(hospitalId: Long): List<PatientHospital>
}
