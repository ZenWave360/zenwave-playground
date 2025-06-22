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
 * Service Implementation for managing [Survey, Question].
 */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class SurveysBackofficeServiceImpl implements SurveysBackofficeService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final SurveysBackofficeServiceMapper surveysBackofficeServiceMapper = SurveysBackofficeServiceMapper.INSTANCE;


    private final SurveyRepository surveyRepository;

    private final QuestionRepository questionRepository;






    public List<Survey> listSurveys()
 {
    log.debug("Request listSurveys");

    var surveys = surveyRepository.findAll();
    return surveys;


}


    public Optional<Survey> getSurvey(Long id)
 {
    log.debug("[CRUD] Request to get Survey : {}", id);
    var survey = surveyRepository.findById(id);
    return survey;


}

    @Transactional

    public Survey createSurvey(Survey input)
 {
    log.debug("[CRUD] Request to save Survey: {}", input);
    var survey = surveysBackofficeServiceMapper.update(new Survey(), input);
    survey = surveyRepository.save(survey);
    // TODO: may need to reload the entity to fetch relationships 'mapped by id'
    return survey;


}

    @Transactional

    public Optional<Survey> updateSurvey(Long id, Survey input)
 {
    log.debug("Request updateSurvey: {} {}", id, input);

    var survey = surveyRepository.findById(id).map(existingSurvey -> {
        return surveysBackofficeServiceMapper.update(existingSurvey, input);
    })
    .map(surveyRepository::save)
    ;
    return survey;


}

    @Transactional

    public void deleteSurvey(Long id)
 {
    log.debug("Request deleteSurvey: {}", id);

    log.debug("Request to delete Survey : {}", id);
    surveyRepository.deleteById(id);


}


    public List<Question> listQuestions()
 {
    log.debug("Request listQuestions");

    var questions = questionRepository.findAll();
    return questions;


}


    public Optional<Question> getQuestion(Long id)
 {
    log.debug("[CRUD] Request to get Question : {}", id);
    var question = questionRepository.findById(id);
    return question;


}

    @Transactional

    public Question createQuestion(Question input)
 {
    log.debug("[CRUD] Request to save Question: {}", input);
    var question = surveysBackofficeServiceMapper.update(new Question(), input);
    question = questionRepository.save(question);
    // TODO: may need to reload the entity to fetch relationships 'mapped by id'
    return question;


}

    @Transactional

    public Optional<Question> updateQuestion(Long id, Question input)
 {
    log.debug("Request updateQuestion: {} {}", id, input);

    var question = questionRepository.findById(id).map(existingQuestion -> {
        return surveysBackofficeServiceMapper.update(existingQuestion, input);
    })
    .map(questionRepository::save)
    ;
    return question;


}

    @Transactional

    public void deleteQuestion(Long id)
 {
    log.debug("Request deleteQuestion: {}", id);

    log.debug("Request to delete Question : {}", id);
    questionRepository.deleteById(id);


}



}
