package io.zenwave360.example.clinicaltool.modules.surveys.service;

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Inbound Service Port for managing [Survey, Question].
 */
@org.springframework.modulith.NamedInterface("SurveysBackofficeService")
public interface SurveysBackofficeService {

     /**
      * 
      *
      */

    public List<Survey> listSurveys()
;
     /**
      * 
      *
      */

    public Optional<Survey> getSurvey(Long id)
;
     /**
      * 
      *
      */

    public Survey createSurvey(Survey input)
;
     /**
      * 
      *
      */

    public Optional<Survey> updateSurvey(Long id, Survey input)
;
     /**
      * 
      *
      */

    public void deleteSurvey(Long id)
;
     /**
      * 
      *
      */

    public List<Question> listQuestions()
;
     /**
      * 
      *
      */

    public Optional<Question> getQuestion(Long id)
;
     /**
      * 
      *
      */

    public Question createQuestion(Question input)
;
     /**
      * 
      *
      */

    public Optional<Question> updateQuestion(Long id, Question input)
;
     /**
      * 
      *
      */

    public void deleteQuestion(Long id)
;


}
