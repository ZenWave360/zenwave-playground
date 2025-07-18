package io.zenwave360.example.clinicaltool.modules.surveys.service.dtos;

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * OptionTranslationOutput.
 */
@lombok.Getter @lombok.Setter
public  class OptionTranslationOutput  implements Serializable {


    @NotNull @Size(max = 254)
    private String name ;

    @NotNull
    private String text ;





}
