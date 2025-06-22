package io.zenwave360.example.clinicaltool.adapters.web.mobile;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.adapters.web.mobile.*;
import io.zenwave360.example.clinicaltool.adapters.web.mobile.dtos.*;
import io.zenwave360.example.clinicaltool.adapters.web.mobile.mappers.*;

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
 * REST controller for PatientsApi.
 */
@RestController
@RequestMapping("/api")
public class PatientsApiController implements PatientsApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;


    private PatientsService patientsService;
    @Autowired
    public PatientsApiController setPatientsService(PatientsService patientsService) {
        this.patientsService = patientsService;
        return this;
    }


    private PatientsDTOsMapper mapper = PatientsDTOsMapper.INSTANCE;

    public PatientsApiController(PatientsService patientsService) {
        
        this.patientsService = patientsService;
        
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }



    @Override
    public ResponseEntity<PatientProfileDTO> getPatientProfileById(Long id) {
        log.debug("REST request to getPatientProfileById: {}", id);var patientProfile =  patientsService.getPatientProfileById(id);
        PatientProfileDTO responseDTO = mapper.asPatientProfileDTO(patientProfile);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<Void> requestOptOut(Long id) {
        log.debug("REST request to requestOptOut: {}", id);
        patientsService.requestOptOut(id);
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
