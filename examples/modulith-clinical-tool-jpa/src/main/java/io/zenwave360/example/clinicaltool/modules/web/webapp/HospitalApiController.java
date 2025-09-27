package io.zenwave360.example.clinicaltool.modules.web.webapp;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.modules.web.webapp.dtos.*;
import io.zenwave360.example.clinicaltool.modules.web.webapp.mappers.*;
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
 * REST controller for HospitalApi.
 */
@RestController
@RequestMapping("/api")
public class HospitalApiController implements HospitalApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;

    private HospitalService hospitalService;

    @Autowired
    public HospitalApiController setHospitalService(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
        return this;
    }

    private HospitalDTOsMapper mapper = HospitalDTOsMapper.INSTANCE;

    public HospitalApiController(HospitalService hospitalService) {

        this.hospitalService = hospitalService;
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<HospitalDTO> getHospital(Long id) {
        log.debug("REST request to getHospital: {}", id);
        var hospital = hospitalService.getHospital(id);
        if (hospital.isPresent()) {
            HospitalDTO responseDTO = mapper.asHospitalDTO(hospital.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<HospitalDTO> updateHospital(Long id, HospitalDTO reqBody) {
        log.debug("REST request to updateHospital: {}, {}", id, reqBody);
        var input = mapper.asHospital(reqBody);
        var hospital = hospitalService.updateHospital(id, input);
        if (hospital.isPresent()) {
            HospitalDTO responseDTO = mapper.asHospitalDTO(hospital.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<HospitalDTO> createHospital(HospitalDTO reqBody) {
        log.debug("REST request to createHospital: {}", reqBody);
        var input = mapper.asHospital(reqBody);
        var hospital = hospitalService.createHospital(input);
        HospitalDTO responseDTO = mapper.asHospitalDTO(hospital);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<List<HospitalDTO>> listHospitals() {
        log.debug("REST request to listHospitals: ");
        var hospital = hospitalService.listHospitals();
        var responseDTO = mapper.asHospitalDTOList(hospital);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<DoctorDTO> createDoctor(DoctorDTO reqBody) {
        log.debug("REST request to createDoctor: {}", reqBody);
        var input = mapper.asDoctor(reqBody);
        var doctor = hospitalService.createDoctor(input);
        DoctorDTO responseDTO = mapper.asDoctorDTO(doctor);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<List<DoctorDTO>> listDoctors() {
        log.debug("REST request to listDoctors: ");
        var doctor = hospitalService.listDoctors();
        var responseDTO = mapper.asDoctorDTOList(doctor);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<DoctorDTO> updateDoctor(Long id, DoctorDTO reqBody) {
        log.debug("REST request to updateDoctor: {}, {}", id, reqBody);
        var input = mapper.asDoctor(reqBody);
        var doctor = hospitalService.updateDoctor(id, input);
        if (doctor.isPresent()) {
            DoctorDTO responseDTO = mapper.asDoctorDTO(doctor.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<DoctorDTO> getDoctor(Long id) {
        log.debug("REST request to getDoctor: {}", id);
        var doctor = hospitalService.getDoctor(id);
        if (doctor.isPresent()) {
            DoctorDTO responseDTO = mapper.asDoctorDTO(doctor.get());
            return ResponseEntity.status(200).body(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<DoctorDTO>> listHospitalDoctors(Long hospitalId) {
        log.debug("REST request to listHospitalDoctors: {}", hospitalId);
        var doctor = hospitalService.listHospitalDoctors(hospitalId);
        var responseDTO = mapper.asDoctorDTOList(doctor);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<List<PatientHospitalDTO>> listHospitalPatients(Long hospitalId) {
        log.debug("REST request to listHospitalPatients: {}", hospitalId);
        var patientHospital = hospitalService.listHospitalPatients(hospitalId);
        var responseDTO = mapper.asPatientHospitalDTOList(patientHospital);
        return ResponseEntity.status(200).body(responseDTO);
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
