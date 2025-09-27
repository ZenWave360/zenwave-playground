package io.zenwave360.example.clinicaltool.modules.surveys.service;

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * Inbound Service Port for managing [SurveyAnswers].
 */
@org.springframework.modulith.NamedInterface("SurveysService")
public interface SurveysService {

    /**
     *
     *
     */
    public SurveyAndQuestions getSurveyAndQuestionsForPatient(String name, Long patientId, String lang);
    /**
     *
     *
     */
    public SurveyAnswers answerSurvey(Long surveyId, Long patientId, LocalDate date, SurveyAnswers input);
    /**
     *
     *
     */
    public Optional<SurveyAnswers> updateSurveyAnswers(
            Long surveyId, Long patientId, LocalDate date, java.util.Map input);
    /**
     *
     *
     */
    public Optional<SurveyAnswers> getSurveyAnswers(Long surveyId, Long patientId, LocalDate date);
}
