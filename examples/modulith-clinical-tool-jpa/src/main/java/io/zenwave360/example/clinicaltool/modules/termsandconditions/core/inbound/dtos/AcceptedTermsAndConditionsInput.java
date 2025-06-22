package io.zenwave360.example.clinicaltool.modules.termsandconditions.core.inbound.dtos;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.domain.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * AcceptedTermsAndConditionsInput.
 */
@lombok.Getter @lombok.Setter
public  class AcceptedTermsAndConditionsInput  implements Serializable {


    @NotNull
    private Long termsAndConditionsId ;




}
