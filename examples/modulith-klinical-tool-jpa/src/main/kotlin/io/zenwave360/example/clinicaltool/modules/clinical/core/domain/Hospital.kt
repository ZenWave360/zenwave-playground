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
@Table(name = "hospital")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
data class Hospital(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    @Version var version: Int? = null,
    @NotNull
    @Size(max = 254)
    @Column(name = "name", nullable = false, unique = true, length = 254)
    var name: String? = null,

    /** Primary language of the hospital */
    @NotNull @Size(max = 3) @Column(name = "lang", nullable = false, length = 3) var lang: String? = null,

    /** ECT (Europe/Madrid) */
    @NotNull @Size(max = 3) @Column(name = "timezone", nullable = false, length = 3) var timezone: String? = null,
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return this::class.java.name + "#" + id
    }

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Hospital) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
