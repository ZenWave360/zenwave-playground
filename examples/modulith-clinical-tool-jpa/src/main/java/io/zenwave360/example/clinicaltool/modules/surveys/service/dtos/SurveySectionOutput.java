package io.zenwave360.example.clinicaltool.modules.surveys.service.dtos;

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * SurveySectionOutput.
 */
@lombok.Getter @lombok.Setter
public  class SurveySectionOutput  implements Serializable {


    @NotNull @Size(max = 254)
    private String name ;

    @NotNull @Size(max = 254)
    private String title ;

    
    private String summary ;

    
    private List<QuestionTranslationOutput> questions = new ArrayList<>();






        public SurveySectionOutput addQuestions(QuestionTranslationOutput questions) {
        this.questions.add(questions);
        return this;
        }

}
