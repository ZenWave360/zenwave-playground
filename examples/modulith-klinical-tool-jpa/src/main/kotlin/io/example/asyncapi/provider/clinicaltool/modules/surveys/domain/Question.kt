package io.example.asyncapi.provider.clinicaltool.modules.surveys.domain

import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*
import jakarta.persistence.*
import jakarta.validation.constraints.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

/**
*
*/
@Entity
@Table(name = "question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)@EntityListeners(AuditingEntityListener::class)
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    var id: Long? = null,

    @Version
    var version: Int? = null,

    /**
    * Unique identifier for the question
    */
    @NotNull @Size(max = 254)@Column(name = "name", nullable = false, unique = true, length = 254)
    var name: String?  = null,

    @NotNull@Column(name = "question_type", nullable = false)
    @Convert(converter = QuestionType.QuestionTypeConverter::class)

    var questionType: QuestionType?  = null,

    @Column(name = "required")
    var required: Boolean? = true,

    @Column(name = "range_start")
    var rangeStart: Integer?  = null,

    @Column(name = "range_end")
    var rangeEnd: Integer?  = null,


    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)
    @Column(name = "translations")
    var translations: MutableList<QuestionTranslation> = mutableListOf(),


    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)
    @Column(name = "options")
    var options: MutableList<Option> = mutableListOf(),

    @Column(name = "include_other")
    var includeOther: Boolean? = false



)  : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    var createdBy: String? = null

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "TIMESTAMP", updatable = false)
    var createdDate: LocalDateTime? = null

    @LastModifiedBy
    @Column(name = "last_modified_by")
    var lastModifiedBy: String? = null

    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP")
    var lastModifiedDate: LocalDateTime? = null

override fun toString(): String {
        return this::class.java.name + "#" + id
    }

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Question) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
