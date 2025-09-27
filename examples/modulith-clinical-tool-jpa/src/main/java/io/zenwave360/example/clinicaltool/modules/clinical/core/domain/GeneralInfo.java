package io.zenwave360.example.clinicaltool.modules.clinical.core.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 *
 */
@lombok.Getter
@lombok.Setter
@Embeddable
public class GeneralInfo implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    private Integer version;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @Size(max = 100)
    @Column(name = "surname2", length = 100)
    private String surname2;

    @NotNull
    @Column(name = "identity_document_type", nullable = false)
    @Convert(converter = IdentityDocumentType.IdentityDocumentTypeConverter.class)
    private IdentityDocumentType identityDocumentType;

    @NotNull
    @Size(max = 20)
    @Column(name = "identity_document_number", nullable = false, length = 20)
    private String identityDocumentNumber;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Column(name = "gender", nullable = false)
    @Convert(converter = GenderType.GenderTypeConverter.class)
    private GenderType gender;

    /**
     * Primary language of the patient
     */
    @Size(max = 3)
    @Column(name = "lang", length = 3)
    private String lang;

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GeneralInfo)) {
            return false;
        }
        GeneralInfo other = (GeneralInfo) o;
        return getId() != null && getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
