package io.zenwave360.example.clinicaltool.modules.documents.domain

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
@Table(name = "document_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
data class DocumentInfo(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) var id: Long? = null,
    @Version var version: Int? = null,
    @Column(name = "uuid") var uuid: String? = null,
    @NotNull @Column(name = "file_name", nullable = false) var fileName: String? = null,
    @NotNull @Column(name = "document_type", nullable = false) var documentType: String? = null,
    @NotNull @Column(name = "content_type", nullable = false) var contentType: String? = null,
    @Column(name = "tags") var tags: MutableList<String> = mutableListOf(),
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    @NotNull
    @OneToOne(mappedBy = "document", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var documentData: DocumentData? = null
        set(value) {
            field = value
            value?.document = this
        }

    // manage relationships

    override fun toString(): String {
        return this::class.java.name + "#" + id
    }

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DocumentInfo) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
