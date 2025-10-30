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
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)@EntityListeners(AuditingEntityListener::class)
data class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    var id: Long? = null,

    @Version
    var version: Int? = null,

    @Column(name = "user_id")
    var userId: Long?  = null,

    @NotNull@Column(name = "hospital_id", nullable = false)
    var hospitalId: Long?  = null,

    @Column(name = "profile_picture_id")
    var profilePictureId: Long?  = null,

    @NotNull @Size(max = 20)@Column(name = "phone_number", nullable = false, length = 20) @org.hibernate.annotations.NaturalId
    var phoneNumber: String?  = null,

    @NotNull @Size(max = 100)@Column(name = "his_number", nullable = false, length = 100) @org.hibernate.annotations.NaturalId
    var hisNumber: String?  = null,

    @NotNull @Size(max = 100) @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}")@Column(name = "email", nullable = false, length = 100)
    var email: String?  = null,

    @Embedded
    @AttributeOverride(name = "name", column = Column(name = "general_info_name"))

    @AttributeOverride(name = "surname", column = Column(name = "general_info_surname"))

    @AttributeOverride(name = "surname2", column = Column(name = "general_info_surname2"))

    @AttributeOverride(name = "identityDocumentType", column = Column(name = "general_info_identityDocumentType"))

    @AttributeOverride(name = "identityDocumentNumber", column = Column(name = "general_info_identityDocumentNumber"))

    @AttributeOverride(name = "birthDate", column = Column(name = "general_info_birthDate"))

    @AttributeOverride(name = "gender", column = Column(name = "general_info_gender"))

    @AttributeOverride(name = "lang", column = Column(name = "general_info_lang"))
    var generalInfo: GeneralInfo?  = null,

    @Embedded
    @AttributeOverride(name = "insuranceCompanyId", column = Column(name = "health_insurance_info_insuranceCompanyId"))

    @AttributeOverride(name = "insuranceCardNumber", column = Column(name = "health_insurance_info_insuranceCardNumber"))
    var healthInsuranceInfo: HealthInsuranceInfo?  = null,


    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)
    @Column(name = "document_ids")
    var documentIds: MutableList<Long> = mutableListOf()



    ,

    @NotNull

@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@com.fasterxml.jackson.annotation.JsonManagedReference


    var medicalContacts: Set<MedicalContact> = mutableSetOf(),



    @NotNull

@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@com.fasterxml.jackson.annotation.JsonManagedReference


    var personalContacts: Set<PersonalContact> = mutableSetOf(),



    @NotNull

@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@com.fasterxml.jackson.annotation.JsonManagedReference


    var patientAddresses: Set<PatientAddress> = mutableSetOf(),



    @NotNull

@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@com.fasterxml.jackson.annotation.JsonManagedReference


    var hospitalAddresses: Set<HospitalAddress> = mutableSetOf(),



    @NotNull

@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@com.fasterxml.jackson.annotation.JsonManagedReference


    var patientWearables: Set<PatientWearable> = mutableSetOf()

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
    // manage relationships
    fun addMedicalContacts(medicalContacts: MedicalContact): Patient {
        this.medicalContacts += medicalContacts
        medicalContacts.patient = this
        return this
    }

    fun removeMedicalContacts(medicalContacts: MedicalContact): Patient {
        this.medicalContacts -= medicalContacts
        medicalContacts.patient = null
        return this
    }

    // manage relationships
    fun addPersonalContacts(personalContacts: PersonalContact): Patient {
        this.personalContacts += personalContacts
        personalContacts.patient = this
        return this
    }

    fun removePersonalContacts(personalContacts: PersonalContact): Patient {
        this.personalContacts -= personalContacts
        personalContacts.patient = null
        return this
    }

    // manage relationships
    fun addPatientAddresses(patientAddresses: PatientAddress): Patient {
        this.patientAddresses += patientAddresses
        patientAddresses.patient = this
        return this
    }

    fun removePatientAddresses(patientAddresses: PatientAddress): Patient {
        this.patientAddresses -= patientAddresses
        patientAddresses.patient = null
        return this
    }

    // manage relationships
    fun addHospitalAddresses(hospitalAddresses: HospitalAddress): Patient {
        this.hospitalAddresses += hospitalAddresses
        hospitalAddresses.patient = this
        return this
    }

    fun removeHospitalAddresses(hospitalAddresses: HospitalAddress): Patient {
        this.hospitalAddresses -= hospitalAddresses
        hospitalAddresses.patient = null
        return this
    }

    // manage relationships
    fun addPatientWearables(patientWearables: PatientWearable): Patient {
        this.patientWearables += patientWearables
        patientWearables.patient = this
        return this
    }

    fun removePatientWearables(patientWearables: PatientWearable): Patient {
        this.patientWearables -= patientWearables
        patientWearables.patient = null
        return this
    }


override fun toString(): String {
        return this::class.java.name + "#" + id
    }

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Patient) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
