package io.zenwave360.example.clinicaltool.modules.surveys.web;

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*;
import io.zenwave360.example.clinicaltool.modules.surveys.web.dtos.*;
import io.zenwave360.example.clinicaltool.modules.surveys.web.mappers.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * REST controller for SurveysApi.
 */
@RestController
@RequestMapping("/api")
public class SurveysApiController implements SurveysApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;

    private SurveysService surveysService;

    @Autowired
    public SurveysApiController setSurveysService(SurveysService surveysService) {
        this.surveysService = surveysService;
        return this;
    }

    private SurveysDTOsMapper mapper = SurveysDTOsMapper.INSTANCE;

    public SurveysApiController(SurveysService surveysService) {

        this.surveysService = surveysService;
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<SurveyAndQuestionsDTO> getSurveyAndQuestionsForPatient(
            String name, Long patientId, String lang) {
        log.debug("REST request to getSurveyAndQuestionsForPatient: {}, {}, {}", name, patientId, lang);
        var surveyAndQuestions = surveysService.getSurveyAndQuestionsForPatient(name, patientId, lang);
        SurveyAndQuestionsDTO responseDTO = mapper.asSurveyAndQuestionsDTO(surveyAndQuestions);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<SurveyAnswersDTO> answerSurvey(
            Long surveyId, Long patientId, LocalDate date, SurveyAnswersDTO reqBody) {
        log.debug("REST request to answerSurvey: {}, {}, {}, {}", surveyId, patientId, date, reqBody);
        var input = mapper.asSurveyAnswers(reqBody);
        var surveyAnswers = surveysService.answerSurvey(surveyId, patientId, date, input);
        SurveyAnswersDTO responseDTO = mapper.asSurveyAnswersDTO(surveyAnswers);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<SurveyAnswersDTO> updateSurveyAnswers(
            Long surveyId, Long patientId, LocalDate date, Map input) {
        log.debug("REST request to updateSurveyAnswers: {}, {}, {}, {}", surveyId, patientId, date, input);
        var surveyAnswers = surveysService.updateSurveyAnswers(surveyId, patientId, date, input);
        if (surveyAnswers.isPresent()) {
            SurveyAnswersDTO responseDTO = mapper.asSurveyAnswersDTO(surveyAnswers.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<SurveyAnswersDTO> getSurveyAnswers(Long surveyId, Long patientId, LocalDate date) {
        log.debug("REST request to getSurveyAnswers: {}, {}, {}", surveyId, patientId, date);
        var surveyAnswers = surveysService.getSurveyAnswers(surveyId, patientId, date);
        if (surveyAnswers.isPresent()) {
            SurveyAnswersDTO responseDTO = mapper.asSurveyAnswersDTO(surveyAnswers.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    protected Pageable pageOf(Integer page, Integer limit, List<String> sort) {
        Sort sortOrder = sort != null
                ? Sort.by(sort.stream()
                        .map(sortParam -> {
                            String[] parts = sortParam.split(":");
                            String property = parts[0];
                            Sort.Direction direction =
                                    parts.length > 1 ? Sort.Direction.fromString(parts[1]) : Sort.Direction.ASC;
                            return new Sort.Order(direction, property);
                        })
                        .toList())
                : Sort.unsorted();
        return PageRequest.of(page != null ? page : 0, limit != null ? limit : 10, sortOrder);
    }
}
