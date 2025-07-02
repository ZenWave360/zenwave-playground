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
@Entity
@Table(name = "personal_contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
data class PersonalContact(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    var id: Long? = null,

    @Version
    var version: Int? = null,

    @NotNull @Size(max = 100)@Column(name = "name", nullable = false, length = 100)
    var name: String?  = null,

    @NotNull @Size(max = 100)@Column(name = "surname", nullable = false, length = 100)
    var surname: String?  = null,

    @Size(max = 100)@Column(name = "surname2", length = 100)
    var surname2: String?  = null,

    @NotNull @Size(max = 20)@Column(name = "phone_number", nullable = false, length = 20)
    var phoneNumber: String?  = null,

    @NotNull @Size(max = 100) @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}")@Column(name = "email", nullable = false, length = 100)
    var email: String?  = null,

    @Column(name = "patient_relationship_type")
    @Convert(converter = PatientRelationshipType.PatientRelationshipTypeConverter::class)
    
    var patientRelationshipType: PatientRelationshipType?  = null,

    @NotNull@Column(name = "emergency_contact", nullable = false)
    var emergencyContact: Boolean?  = null



    ,
        
    @NotNull
    
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "patient_id")
@com.fasterxml.jackson.annotation.JsonBackReference


    var patient: Patient?  = null
    
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
        if (other !is PersonalContact) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
