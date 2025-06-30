package io.zenwave360.example.clinicaltool.modules.surveys.web;

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*;
import io.zenwave360.example.clinicaltool.modules.surveys.web.*;
import io.zenwave360.example.clinicaltool.modules.surveys.web.dtos.*;
import io.zenwave360.example.clinicaltool.modules.surveys.web.mappers.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.context.request.NativeWebRequest;


/**
 * REST controller for SurveysBackofficeApi.
 */
@RestController
@RequestMapping("/api")
public class SurveysBackofficeApiController implements SurveysBackofficeApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;


    private SurveysBackofficeService surveysBackofficeService;
    @Autowired
    public SurveysBackofficeApiController setSurveysBackofficeService(SurveysBackofficeService surveysBackofficeService) {
        this.surveysBackofficeService = surveysBackofficeService;
        return this;
    }


    private SurveysBackofficeDTOsMapper mapper = SurveysBackofficeDTOsMapper.INSTANCE;

    public SurveysBackofficeApiController(SurveysBackofficeService surveysBackofficeService) {
        
        this.surveysBackofficeService = surveysBackofficeService;
        
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }



    @Override
    public ResponseEntity<List<SurveyDTO>> listSurveys() {
        log.debug("REST request to listSurveys: ");var survey =  surveysBackofficeService.listSurveys();
        var responseDTO = mapper.asSurveyDTOList(survey);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<SurveyDTO> createSurvey(SurveyDTO reqBody) {
        log.debug("REST request to createSurvey: {}", reqBody);var input = mapper.asSurvey(reqBody);var survey =  surveysBackofficeService.createSurvey(input);
        SurveyDTO responseDTO = mapper.asSurveyDTO(survey);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<SurveyDTO> getSurvey(Long id) {
        log.debug("REST request to getSurvey: {}", id);var survey =  surveysBackofficeService.getSurvey(id);
        if (survey.isPresent()) {
            SurveyDTO responseDTO = mapper.asSurveyDTO(survey.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<SurveyDTO> updateSurvey(Long id, SurveyDTO reqBody) {
        log.debug("REST request to updateSurvey: {}, {}", id, reqBody);var input = mapper.asSurvey(reqBody);var survey =  surveysBackofficeService.updateSurvey(id, input);
        if (survey.isPresent()) {
            SurveyDTO responseDTO = mapper.asSurveyDTO(survey.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteSurvey(Long id) {
        log.debug("REST request to deleteSurvey: {}", id);
        surveysBackofficeService.deleteSurvey(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<List<QuestionDTO>> listQuestions() {
        log.debug("REST request to listQuestions: ");var question =  surveysBackofficeService.listQuestions();
        var responseDTO = mapper.asQuestionDTOList(question);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<QuestionDTO> createQuestion(QuestionDTO reqBody) {
        log.debug("REST request to createQuestion: {}", reqBody);var input = mapper.asQuestion(reqBody);var question =  surveysBackofficeService.createQuestion(input);
        QuestionDTO responseDTO = mapper.asQuestionDTO(question);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<QuestionDTO> getQuestion(Long id) {
        log.debug("REST request to getQuestion: {}", id);var question =  surveysBackofficeService.getQuestion(id);
        if (question.isPresent()) {
            QuestionDTO responseDTO = mapper.asQuestionDTO(question.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<QuestionDTO> updateQuestion(Long id, QuestionDTO reqBody) {
        log.debug("REST request to updateQuestion: {}, {}", id, reqBody);var input = mapper.asQuestion(reqBody);var question =  surveysBackofficeService.updateQuestion(id, input);
        if (question.isPresent()) {
            QuestionDTO responseDTO = mapper.asQuestionDTO(question.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteQuestion(Long id) {
        log.debug("REST request to deleteQuestion: {}", id);
        surveysBackofficeService.deleteQuestion(id);
        return ResponseEntity.status(204).build();
    }
  protected Pageable pageOf(Integer page, Integer limit, List<String> sort) {
    Sort sortOrder = sort != null ? Sort.by(sort.stream().map(sortParam -> {
    String[] parts = sortParam.split(":");
    String property = parts[0];
    Sort.Direction direction = parts.length > 1 ? Sort.Direction.fromString(parts[1]) : Sort.Direction.ASC;
    return new Sort.Order(direction, property);
    }).toList()) : Sort.unsorted();
    return PageRequest.of(page != null ? page : 0, limit != null ? limit : 10, sortOrder);
  }
}
