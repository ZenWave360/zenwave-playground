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
@Table(name = "patient_wearable")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
data class PatientWearable(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    @Version var version: Int? = null,
    @NotNull @Column(name = "wearable_type", nullable = false) var wearableType: String? = null,
    @Size(max = 20) @Column(name = "serial_number", length = 20) var serialNumber: String? = null,
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
        if (other !is PatientWearable) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
