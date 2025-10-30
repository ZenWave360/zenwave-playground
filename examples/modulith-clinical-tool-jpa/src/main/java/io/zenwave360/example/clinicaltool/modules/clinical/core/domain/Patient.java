package io.zenwave360.example.clinicaltool.modules.clinical.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 */
@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EntityListeners(AuditingEntityListener.class)
public class Patient implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "hospital_id", nullable = false)
    private Long hospitalId;

    @Column(name = "profile_picture_id")
    private Long profilePictureId;

    @NotNull
    @Size(max = 20)
    @Column(name = "phone_number", nullable = false, length = 20)
    @org.hibernate.annotations.NaturalId
    private String phoneNumber;

    @NotNull
    @Size(max = 100)
    @Column(name = "his_number", nullable = false, length = 100)
    @org.hibernate.annotations.NaturalId
    private String hisNumber;

    @NotNull
    @Size(max = 100)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "general_info_name"))
    @AttributeOverride(name = "surname", column = @Column(name = "general_info_surname"))
    @AttributeOverride(name = "surname2", column = @Column(name = "general_info_surname2"))
    @AttributeOverride(name = "identityDocumentType", column = @Column(name = "general_info_identityDocumentType"))
    @AttributeOverride(name = "identityDocumentNumber", column = @Column(name = "general_info_identityDocumentNumber"))
    @AttributeOverride(name = "birthDate", column = @Column(name = "general_info_birthDate"))
    @AttributeOverride(name = "gender", column = @Column(name = "general_info_gender"))
    @AttributeOverride(name = "lang", column = @Column(name = "general_info_lang"))
    private GeneralInfo generalInfo;

    @Embedded
    @AttributeOverride(name = "insuranceCompanyId", column = @Column(name = "health_insurance_info_insuranceCompanyId"))
    @AttributeOverride(
            name = "insuranceCardNumber",
            column = @Column(name = "health_insurance_info_insuranceCardNumber"))
    private HealthInsuranceInfo healthInsuranceInfo;

    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.JSON)
    @Column(name = "document_ids")
    private List<Long> documentIds = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private Set<MedicalContact> medicalContacts = new HashSet<>();

    @NotNull
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private Set<PersonalContact> personalContacts = new HashSet<>();

    @NotNull
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private Set<PatientAddress> patientAddresses = new HashSet<>();

    @NotNull
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private Set<HospitalAddress> hospitalAddresses = new HashSet<>();

    @NotNull
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private Set<PatientWearable> patientWearables = new HashSet<>();

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    protected String createdBy;

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "TIMESTAMP", updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP")
    protected LocalDateTime lastModifiedDate;
    // manage relationships
    public Patient addMedicalContacts(MedicalContact medicalContacts) {
        this.medicalContacts.add(medicalContacts);
        medicalContacts.setPatient(this);
        return this;
    }

    public Patient removeMedicalContacts(MedicalContact medicalContacts) {
        this.medicalContacts.remove(medicalContacts);
        medicalContacts.setPatient(null);
        return this;
    }

    // manage relationships
    public Patient addPersonalContacts(PersonalContact personalContacts) {
        this.personalContacts.add(personalContacts);
        personalContacts.setPatient(this);
        return this;
    }

    public Patient removePersonalContacts(PersonalContact personalContacts) {
        this.personalContacts.remove(personalContacts);
        personalContacts.setPatient(null);
        return this;
    }

    // manage relationships
    public Patient addPatientAddresses(PatientAddress patientAddresses) {
        this.patientAddresses.add(patientAddresses);
        patientAddresses.setPatient(this);
        return this;
    }

    public Patient removePatientAddresses(PatientAddress patientAddresses) {
        this.patientAddresses.remove(patientAddresses);
        patientAddresses.setPatient(null);
        return this;
    }

    // manage relationships
    public Patient addHospitalAddresses(HospitalAddress hospitalAddresses) {
        this.hospitalAddresses.add(hospitalAddresses);
        hospitalAddresses.setPatient(this);
        return this;
    }

    public Patient removeHospitalAddresses(HospitalAddress hospitalAddresses) {
        this.hospitalAddresses.remove(hospitalAddresses);
        hospitalAddresses.setPatient(null);
        return this;
    }

    // manage relationships
    public Patient addPatientWearables(PatientWearable patientWearables) {
        this.patientWearables.add(patientWearables);
        patientWearables.setPatient(this);
        return this;
    }

    public Patient removePatientWearables(PatientWearable patientWearables) {
        this.patientWearables.remove(patientWearables);
        patientWearables.setPatient(null);
        return this;
    }

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) o;
        return getId() != null && getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
