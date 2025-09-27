package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * DocumentSignatureRequestedInput.
 */
@lombok.Getter
@lombok.Setter
public class DocumentSignatureRequestedInput implements Serializable {

    @NotNull
    private Long documentInfoId;

    @NotNull
    private Long patientId;
}
