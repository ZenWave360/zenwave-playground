package io.zenwave360.example.clinicaltool.modules.surveys.service.dtos;

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.validation.constraints.*;

/**
 * QuestionTranslationOutput.
 */
@lombok.Getter @lombok.Setter
public  class QuestionTranslationOutput  implements Serializable {


    @NotNull
    private Long questionId ;

    
    private boolean required = true;

    
    private Integer rangeStart ;

    
    private Integer rangeEnd ;

    @NotNull @Size(max = 254)
    private String text ;

    @NotNull
    private QuestionType questionType ;

    
    private List<OptionTranslationOutput> options = new ArrayList<>();

    
    private boolean includeOther = false;









        public QuestionTranslationOutput addOptions(OptionTranslationOutput options) {
        this.options.add(options);
        return this;
        }


}
