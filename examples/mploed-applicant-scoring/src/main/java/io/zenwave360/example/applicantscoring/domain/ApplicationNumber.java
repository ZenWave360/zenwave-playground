package io.zenwave360.example.applicantscoring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 */
@lombok.Getter
@lombok.Setter
@Embeddable
public class ApplicationNumber implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 3, max = 254)
    @Column(name = "application_number", nullable = false, unique = true, length = 254)
    private String applicationNumber;
}
