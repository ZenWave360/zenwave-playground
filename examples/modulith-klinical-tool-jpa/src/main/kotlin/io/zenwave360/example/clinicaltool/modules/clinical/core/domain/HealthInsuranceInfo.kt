package io.zenwave360.example.clinicaltool.modules.clinical.core.domain

import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*
import jakarta.persistence.*
import jakarta.validation.constraints.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy

/**
* 
*/
@Embeddable
data class HealthInsuranceInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    var id: Long? = null,

    @Version
    var version: Int? = null,

    @NotNull @Size(max = 100)@Column(name = "insurance_company_id", nullable = false, length = 100)
    var insuranceCompanyId: String?  = null,

    @NotNull @Size(max = 20)@Column(name = "insurance_card_number", nullable = false, length = 20)
    var insuranceCardNumber: String?  = null



)  : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

override fun toString(): String {
        return this::class.java.name + "#" + id
    }

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HealthInsuranceInfo) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
