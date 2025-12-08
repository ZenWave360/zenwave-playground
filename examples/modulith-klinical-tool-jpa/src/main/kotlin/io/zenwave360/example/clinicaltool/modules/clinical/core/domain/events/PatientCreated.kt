package io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*

/**  */
@org.springframework.modulith.NamedInterface("events.PatientCreated")
data class PatientCreated(var patient: Patient? = null) : Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
