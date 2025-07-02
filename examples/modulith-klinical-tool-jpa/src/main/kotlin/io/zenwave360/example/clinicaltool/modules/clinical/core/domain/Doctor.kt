package io.zenwave360.example.clinicaltool.modules.clinical.core.domain

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
@Table(name = "doctor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)@EntityListeners(AuditingEntityListener::class)
data class Doctor(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    var id: Long? = null,

    @Version
    var version: Int? = null,

    @Column(name = "user_id")
    var userId: Long?  = null,

    @Column(name = "profile_picture_id")
    var profilePictureId: Long?  = null,

    @NotNull@Column(name = "hospital_id", nullable = false)
    var hospitalId: Long?  = null,

    @NotNull @Size(max = 100)@Column(name = "name", nullable = false, length = 100)
    var name: String?  = null,

    @NotNull @Size(max = 100)@Column(name = "surname", nullable = false, length = 100)
    var surname: String?  = null,

    @Size(max = 100)@Column(name = "surname2", length = 100)
    var surname2: String?  = null,

    @Size(max = 100) @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}")@Column(name = "email", length = 100)
    var email: String?  = null,

    @Size(max = 20)@Column(name = "phone_number", length = 20)
    var phoneNumber: String?  = null,

    /**
    * Primary language of the doctor
    */
    @Size(max = 3)@Column(name = "lang", length = 3)
    var lang: String?  = null



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
        if (other !is Doctor) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
