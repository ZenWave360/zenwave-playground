package io.zenwave360.example.clinicaltool.modules.clinical.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 */
@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "patient_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PatientAddress implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    private Integer version;

    @NotNull
    @Size(max = 100)
    @Column(name = "street", nullable = false, length = 100)
    private String street;

    @NotNull
    @Size(max = 100)
    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @NotNull
    @Size(max = 10)
    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;

    @NotNull
    @Size(max = 3)
    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Size(max = 254)
    @Column(name = "additional_info", length = 254)
    private String additionalInfo;

    @NotNull
    @Column(name = "current", nullable = false)
    private Boolean current;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @com.fasterxml.jackson.annotation.JsonBackReference
    private Patient patient;

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PatientAddress)) {
            return false;
        }
        PatientAddress other = (PatientAddress) o;
        return getId() != null && getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
