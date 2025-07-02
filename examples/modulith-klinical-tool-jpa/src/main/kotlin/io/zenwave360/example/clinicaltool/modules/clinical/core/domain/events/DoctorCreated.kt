package io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events

import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*
import jakarta.validation.constraints.*

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*

/**
 *
 */
@org.springframework.modulith.NamedInterface("events.DoctorCreated")
 data class DoctorCreated  (


    var doctor: Doctor? = null

) : Serializable {

    companion object {
        private const val serialVersionUID: Long = 1L
    }



}
