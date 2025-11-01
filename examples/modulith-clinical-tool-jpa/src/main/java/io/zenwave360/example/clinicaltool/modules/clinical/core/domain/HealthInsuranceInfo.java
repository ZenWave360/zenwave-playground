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
public class HealthInsuranceInfo implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 100)
    @Column(name = "insurance_company_id", nullable = false, length = 100)
    private String insuranceCompanyId;

    @NotNull
    @Size(max = 20)
    @Column(name = "insurance_card_number", nullable = false, length = 20)
    private String insuranceCardNumber;
}
