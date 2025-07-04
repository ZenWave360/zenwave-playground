package io.zenwave360.example.applicantscoring.service;

import io.zenwave360.example.applicantscoring.domain.*;
import io.zenwave360.example.applicantscoring.service.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Inbound Service Port for managing [ApplicantScoring].
 */
public interface ApplicantScoringService {

     /**
      * 
      *
      */

    public Optional<ApplicantScoring> getApplicationScoring(Long id)
;
     /**
      * 
      *
      * With Events: [ApplicantScoringEvent].

      */

    public ApplicantScoring createApplicantScoring(ApplicationNumberInput input)
;
     /**
      * 
      *
      * With Events: [ApplicantScoringEvent].

      */

    public Optional<ApplicantScoring> updateCity(Long id, CityInput input)
;
     /**
      * 
      *
      * With Events: [ApplicantScoringEvent].

      */

    public Optional<ApplicantScoring> updateBalanceAtBank(Long id, BalanceAtBankInput input)
;
     /**
      * 
      *
      * With Events: [ApplicantScoringEvent].

      */

    public Optional<ApplicantScoring> updateApplicantScoring(Long id, ApplicantScoringInput input)
;


}
