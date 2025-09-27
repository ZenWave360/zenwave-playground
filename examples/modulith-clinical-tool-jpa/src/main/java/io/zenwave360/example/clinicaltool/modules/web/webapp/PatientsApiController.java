package io.zenwave360.example.clinicaltool.modules.web.webapp;

import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.PatientsService;
import io.zenwave360.example.clinicaltool.modules.web.webapp.dtos.*;
import io.zenwave360.example.clinicaltool.modules.web.webapp.mappers.*;
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
    public ResponseEntity<PatientDTO> loadPatient(String hisNumber, String phoneNumber) {
        log.debug("REST request to loadPatient: {}, {}", hisNumber, phoneNumber);
        var patient = patientsService.loadPatient(phoneNumber, hisNumber);
        PatientDTO responseDTO = mapper.asPatientDTO(patient);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<PatientDTO> partialPatientUpdate(String hisNumber, String phoneNumber, Map input) {
        log.debug("REST request to partialPatientUpdate: {}, {}, {}", hisNumber, phoneNumber, input);
        var patient = patientsService.partialPatientUpdate(phoneNumber, hisNumber, input);
        PatientDTO responseDTO = mapper.asPatientDTO(patient);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<PatientDTO> createPatient(PatientDTO reqBody) {
        log.debug("REST request to createPatient: {}", reqBody);
        var input = mapper.asPatient(reqBody);
        var patient = patientsService.createPatient(input);
        PatientDTO responseDTO = mapper.asPatientDTO(patient);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<PatientDTO> updatePatient(Long id, PatientDTO reqBody) {
        log.debug("REST request to updatePatient: {}, {}", id, reqBody);
        var input = mapper.asPatient(reqBody);
        var patient = patientsService.updatePatient(id, input);
        if (patient.isPresent()) {
            PatientDTO responseDTO = mapper.asPatientDTO(patient.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<PatientDTO> getPatient(Long id) {
        log.debug("REST request to getPatient: {}", id);
        var patient = patientsService.getPatient(id);
        if (patient.isPresent()) {
            PatientDTO responseDTO = mapper.asPatientDTO(patient.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> requestOptOut(Long id) {
        log.debug("REST request to requestOptOut: {}", id);
        patientsService.requestOptOut(id);
        return ResponseEntity.status(200).build();
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
