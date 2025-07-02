package io.zenwave360.example.clinicaltool.modules.surveys.domain

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
@Table(name = "survey")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)@EntityListeners(AuditingEntityListener::class)
data class Survey(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    var id: Long? = null,

    @Version
    var version: Int? = null,

    /**
    * Unique identifier for the survey
    */
    @NotNull @Size(max = 254)@Column(name = "name", nullable = false, length = 254) @org.hibernate.annotations.NaturalId 
    var name: String?  = null,

    @NotNull@Column(name = "hospital_id", nullable = false) @org.hibernate.annotations.NaturalId 
    var hospitalId: Long?  = null,

    /**
    * Public title for the survey
    */
    @NotNull @Size(max = 254)@Column(name = "title", nullable = false, length = 254)
    var title: String?  = null,

    /**
    * Default language for this survey
    */
    @NotNull @Size(max = 3)@Column(name = "lang", nullable = false, length = 3)
    var lang: String?  = null,

    
    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)
    @Column(name = "sections")
    var sections: MutableList<SurveySection> = mutableListOf()



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
        if (other !is Survey) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
