package io.zenwave360.example.clinicaltool.modules.documents.domain

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import java.io.Serializable

/**
 *
 */
@Entity
@Table(name = "document_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
data class DocumentData(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    var id: Long? = null,

    @Version
    var version: Int? = null,

    @NotNull @Column(name = "data", nullable = false) @Lob
    var data: ByteArray? = null,

    @NotNull
    @MapsId
    @JoinColumn(name = "id") @OneToOne(fetch = FetchType.LAZY)

    var document: DocumentInfo? = null

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
        if (other !is DocumentData) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
