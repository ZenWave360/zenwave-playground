package io.zenwave360.example.clinicaltool.modules.clinical.core.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*

/**  */
@Embeddable
data class HealthInsuranceInfo(
    @NotNull
    @Size(max = 100)
    @Column(name = "insurance_company_id", nullable = false, length = 100)
    var insuranceCompanyId: String? = null,
    @NotNull
    @Size(max = 20)
    @Column(name = "insurance_card_number", nullable = false, length = 20)
    var insuranceCardNumber: String? = null,
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return this::class.java.name + "@" + Integer.toHexString(hashCode())
    }
}
