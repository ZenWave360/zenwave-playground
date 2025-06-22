package io.zenwave360.example.clinicaltool.adapters.web.termsandconditions;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.adapters.web.termsandconditions.*;
import io.zenwave360.example.clinicaltool.adapters.web.termsandconditions.dtos.*;
import io.zenwave360.example.clinicaltool.adapters.web.termsandconditions.mappers.*;

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
 * REST controller for TermsAndConditionsApi.
 */
@RestController
@RequestMapping("/api")
public class TermsAndConditionsApiController implements TermsAndConditionsApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;


    private TermsAndConditionsService termsAndConditionsService;
    @Autowired
    public TermsAndConditionsApiController setTermsAndConditionsService(TermsAndConditionsService termsAndConditionsService) {
        this.termsAndConditionsService = termsAndConditionsService;
        return this;
    }


    private TermsAndConditionsDTOsMapper mapper = TermsAndConditionsDTOsMapper.INSTANCE;

    public TermsAndConditionsApiController(TermsAndConditionsService termsAndConditionsService) {
        
        this.termsAndConditionsService = termsAndConditionsService;
        
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }



    @Override
    public ResponseEntity<List<TermsAndConditionsDTO>> listTermsAndConditions() {
        log.debug("REST request to listTermsAndConditions: ");var termsAndConditions =  termsAndConditionsService.listTermsAndConditions();
        var responseDTO = mapper.asTermsAndConditionsDTOList(termsAndConditions);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<TermsAndConditionsDTO> createTermsAndConditions(TermsAndConditionsDTO reqBody) {
        log.debug("REST request to createTermsAndConditions: {}", reqBody);var input = mapper.asTermsAndConditions(reqBody);var termsAndConditions =  termsAndConditionsService.createTermsAndConditions(input);
        TermsAndConditionsDTO responseDTO = mapper.asTermsAndConditionsDTO(termsAndConditions);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<TermsAndConditionsDTO> getTermsAndConditions(Long id) {
        log.debug("REST request to getTermsAndConditions: {}", id);var termsAndConditions =  termsAndConditionsService.getTermsAndConditions(id);
        if (termsAndConditions.isPresent()) {
            TermsAndConditionsDTO responseDTO = mapper.asTermsAndConditionsDTO(termsAndConditions.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<TermsAndConditionsDTO> updateTermsAndConditions(Long id, TermsAndConditionsDTO reqBody) {
        log.debug("REST request to updateTermsAndConditions: {}, {}", id, reqBody);var input = mapper.asTermsAndConditions(reqBody);var termsAndConditions =  termsAndConditionsService.updateTermsAndConditions(id, input);
        if (termsAndConditions.isPresent()) {
            TermsAndConditionsDTO responseDTO = mapper.asTermsAndConditionsDTO(termsAndConditions.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<TermsAndConditionsDTO> getCurrentTermsAndConditions(String lang) {
        log.debug("REST request to getCurrentTermsAndConditions: {}", lang);var termsAndConditions =  termsAndConditionsService.getCurrentTermsAndConditions(lang);
        if (termsAndConditions.isPresent()) {
            TermsAndConditionsDTO responseDTO = mapper.asTermsAndConditionsDTO(termsAndConditions.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> acceptTermsAndConditions(AcceptedTermsAndConditionsInputDTO reqBody) {
        log.debug("REST request to acceptTermsAndConditions: {}", reqBody);var input = mapper.asAcceptedTermsAndConditionsInput(reqBody);
        termsAndConditionsService.acceptTermsAndConditions(input);
        return ResponseEntity.status(200).build();
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
