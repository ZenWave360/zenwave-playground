package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import java.io.Serializable
import java.math.*
import java.time.*
import jakarta.validation.constraints.*

/**
 * PatientHospital.
 */
data class PatientHospital(

    
    val patientId: Long?  = null,

    
    val hospitalId: Long?  = null,

    
    val hisNumber: String?  = null,

    
    val fullName: String?  = null,

    
    val gender: GenderType?  = null,

    
    val dni: String?  = null,

    
    val birthDate: LocalDate?  = null,

    
    val phone: String?  = null,

    
    val email: String?  = null,

    
    val insuranceCardNumber: String?  = null,

    
    val address: String?  = null

)  : Serializable {













}
