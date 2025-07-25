package io.zenwave360.example.clinicaltool.modules.surveys.service.dtos;

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * SurveysByHospital.
 */
@lombok.Getter @lombok.Setter
public  class SurveysByHospital  implements Serializable {


    @NotNull
    private Long hospitalId ;

    @Size(max = 3)
    private String lang ;





}
