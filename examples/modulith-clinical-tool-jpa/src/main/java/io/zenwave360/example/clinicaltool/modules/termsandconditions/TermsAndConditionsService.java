package io.zenwave360.example.clinicaltool.modules.termsandconditions;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Inbound Service Port for managing [AcceptedTermsAndConditions, TermsAndConditions].
 */
@org.springframework.modulith.NamedInterface("TermsAndConditionsService")
public interface TermsAndConditionsService {

     /**
      * 
      *
      */

    public List<TermsAndConditions> listTermsAndConditions()
;
     /**
      * 
      *
      */

    public Optional<TermsAndConditions> getTermsAndConditions(Long id)
;
     /**
      * 
      *
      */

    public TermsAndConditions createTermsAndConditions(TermsAndConditions input)
;
     /**
      * 
      *
      */

    public Optional<TermsAndConditions> updateTermsAndConditions(Long id, TermsAndConditions input)
;
     /**
      * 
      *
      */

    public Optional<TermsAndConditions> getCurrentTermsAndConditions(String lang)
;
     /**
      * 
      *
      */

    public void acceptTermsAndConditions(AcceptedTermsAndConditionsInput input)
;


}
