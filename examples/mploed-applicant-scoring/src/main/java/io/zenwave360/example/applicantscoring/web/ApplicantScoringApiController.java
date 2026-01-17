package io.zenwave360.example.applicantscoring.web;

import io.zenwave360.example.applicantscoring.domain.*;
import io.zenwave360.example.applicantscoring.service.*;
import io.zenwave360.example.applicantscoring.service.dtos.*;
import io.zenwave360.example.applicantscoring.web.dtos.*;
import io.zenwave360.example.applicantscoring.web.mappers.*;
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
 * REST controller for ApplicantScoringApi.
 */
@RestController
@RequestMapping("/api")
public class ApplicantScoringApiController implements ApplicantScoringApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;

    private ApplicantScoringService applicantScoringService;

    @Autowired
    public ApplicantScoringApiController setApplicantScoringService(ApplicantScoringService applicantScoringService) {
        this.applicantScoringService = applicantScoringService;
        return this;
    }

    private ApplicantScoringDTOsMapper mapper = ApplicantScoringDTOsMapper.INSTANCE;

    public ApplicantScoringApiController(ApplicantScoringService applicantScoringService) {

        this.applicantScoringService = applicantScoringService;
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<ApplicantScoringDTO> getApplicationScoring(Long id) {
        log.debug("REST request to getApplicationScoring: {}", id);
        var applicantScoring = applicantScoringService.getApplicationScoring(id);
        if (applicantScoring.isPresent()) {
            ApplicantScoringDTO responseDTO = mapper.asApplicantScoringDTO(applicantScoring.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ApplicantScoringDTO> updateApplicantScoring(Long id, ApplicantScoringInputDTO reqBody) {
        log.debug("REST request to updateApplicantScoring: {}, {}", id, reqBody);
        var input = mapper.asApplicantScoringInput(reqBody);
        var applicantScoring = applicantScoringService.updateApplicantScoring(id, input);
        if (applicantScoring.isPresent()) {
            ApplicantScoringDTO responseDTO = mapper.asApplicantScoringDTO(applicantScoring.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ApplicantScoringDTO> createApplicantScoring(ApplicationNumberInputDTO reqBody) {
        log.debug("REST request to createApplicantScoring: {}", reqBody);
        var input = mapper.asApplicationNumberInput(reqBody);
        var applicantScoring = applicantScoringService.createApplicantScoring(input);
        ApplicantScoringDTO responseDTO = mapper.asApplicantScoringDTO(applicantScoring);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<ApplicantScoringDTO> updateCity(Long id, CityInputDTO reqBody) {
        log.debug("REST request to updateCity: {}, {}", id, reqBody);
        var input = mapper.asCityInput(reqBody);
        var applicantScoring = applicantScoringService.updateCity(id, input);
        if (applicantScoring.isPresent()) {
            ApplicantScoringDTO responseDTO = mapper.asApplicantScoringDTO(applicantScoring.get());
            return ResponseEntity.status(201).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<ApplicantScoringDTO> updateBalanceAtBank(Long id, BalanceAtBankInputDTO reqBody) {
        log.debug("REST request to updateBalanceAtBank: {}, {}", id, reqBody);
        var input = mapper.asBalanceAtBankInput(reqBody);
        var applicantScoring = applicantScoringService.updateBalanceAtBank(id, input);
        if (applicantScoring.isPresent()) {
            ApplicantScoringDTO responseDTO = mapper.asApplicantScoringDTO(applicantScoring.get());
            return ResponseEntity.status(201).body(responseDTO);
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
