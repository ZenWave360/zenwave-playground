package io.zenwave360.example.clinicaltool.modules.surveys.core.implementation;

import io.zenwave360.example.clinicaltool.modules.surveys.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.surveys.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.modules.surveys.core.implementation.mappers.*;
import io.zenwave360.example.clinicaltool.modules.surveys.core.outbound.jpa.*;

import java.math.*;
import java.time.*;
import java.util.*;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing [SurveyAnswers].
 */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class SurveysServiceImpl implements SurveysService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SurveysServiceMapper surveysServiceMapper = SurveysServiceMapper.INSTANCE;


    private final SurveyAnswersRepository surveyAnswersRepository;






    public SurveyAndQuestions getSurveyAndQuestionsForPatient(String name, Long patientId, String lang)
 {
    log.debug("Request getSurveyAndQuestionsForPatient: {} {} {}", name, patientId, lang);

    var surveyAnswers = surveysServiceMapper.update(new SurveyAnswers(), name, patientId, lang);
    // TODO: implement this method
    return surveysServiceMapper.asSurveyAndQuestions(surveyAnswers);


}


    public SurveyAnswers answerSurvey(Long surveyId, Long patientId, LocalDate date, SurveyAnswers input)
 {
    log.debug("Request answerSurvey: {} {} {} {}", surveyId, patientId, date, input);

    var surveyAnswers = surveyAnswersRepository.findBySurveyIdAndPatientIdAndDate(surveyId, patientId, date).map(existingSurveyAnswers -> {
        return surveysServiceMapper.update(existingSurveyAnswers, input);
    })
    .map(surveyAnswersRepository::save)
    .orElseThrow();
    return surveyAnswers;


}

    @Transactional

    public Optional<SurveyAnswers> updateSurveyAnswers(Long surveyId, Long patientId, LocalDate date, java.util.Map input)
 {
    log.debug("Request updateSurveyAnswers: {} {} {} {}", surveyId, patientId, date, input);

    var surveyAnswers = surveyAnswersRepository.findBySurveyIdAndPatientIdAndDate(surveyId, patientId, date).map(existingSurveyAnswers -> {
    return surveysServiceMapper.update(existingSurveyAnswers, input);
    })
    .map(surveyAnswersRepository::save)
    ;
    return surveyAnswers;


}


    public Optional<SurveyAnswers> getSurveyAnswers(Long surveyId, Long patientId, LocalDate date)
 {
    log.debug("Request getSurveyAnswers: {} {} {}", surveyId, patientId, date);
    var surveyAnswers = surveyAnswersRepository.findBySurveyIdAndPatientIdAndDate(surveyId, patientId, date);
    return surveyAnswers;


}



}
