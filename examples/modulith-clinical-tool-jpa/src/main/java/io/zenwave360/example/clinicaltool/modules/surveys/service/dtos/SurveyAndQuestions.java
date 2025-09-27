package io.zenwave360.example.clinicaltool.modules.surveys.service.dtos;

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * SurveyAndQuestions.
 */
@lombok.Getter
@lombok.Setter
public class SurveyAndQuestions implements Serializable {

    @NotNull
    private Long surveyId;

    @NotNull
    @Size(max = 254)
    private String name;

    @NotNull
    @Size(max = 254)
    private String title;

    @NotNull
    private Long hospitalId;

    @NotNull
    @Size(max = 3)
    private String lang;

    private List<SurveySectionOutput> sections = new ArrayList<>();

    public SurveyAndQuestions addSections(SurveySectionOutput sections) {
        this.sections.add(sections);
        return this;
    }
}
