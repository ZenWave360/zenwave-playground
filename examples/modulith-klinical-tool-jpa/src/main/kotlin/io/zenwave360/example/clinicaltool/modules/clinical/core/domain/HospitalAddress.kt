package io.zenwave360.example.clinicaltool.modules.clinical.core.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy

/**  */
@Entity
@Table(name = "hospital_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
data class HospitalAddress(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    @Version var version: Int? = null,
    @NotNull @Size(max = 100) @Column(name = "street", nullable = false, length = 100) var street: String? = null,
    @NotNull @Size(max = 100) @Column(name = "city", nullable = false, length = 100) var city: String? = null,
    @NotNull
    @Size(max = 10)
    @Column(name = "postal_code", nullable = false, length = 10)
    var postalCode: String? = null,
    @NotNull
    @Size(max = 3)
    @Column(name = "country_code", nullable = false, length = 3)
    var countryCode: String? = null,
    @Size(max = 254) @Column(name = "additional_info", length = 254) var additionalInfo: String? = null,
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    var patient: Patient? = null

    override fun toString(): String {
        return this::class.java.name + "#" + id
    }

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HospitalAddress) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
