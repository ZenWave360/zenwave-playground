package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.events.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.*;

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
 * Service Implementation for managing [Hospital, Doctor].
 */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final HospitalServiceMapper hospitalServiceMapper = HospitalServiceMapper.INSTANCE;


    private final HospitalRepository hospitalRepository;

    private final DoctorRepository doctorRepository;



    private final EventsMapper eventsMapper = EventsMapper.INSTANCE;
	private final EventPublisher eventPublisher;




    public Optional<Hospital> getHospital(Long id)
 {
    log.debug("[CRUD] Request to get Hospital : {}", id);
    var hospital = hospitalRepository.findById(id);
    return hospital;


}

    @Transactional

    public Hospital createHospital(Hospital input)
 {
    log.debug("[CRUD] Request to save Hospital: {}", input);
    var hospital = hospitalServiceMapper.update(new Hospital(), input);
    hospital = hospitalRepository.save(hospital);
    // TODO: may need to reload the entity to fetch relationships 'mapped by id'
    return hospital;


}

    @Transactional

    public Optional<Hospital> updateHospital(Long id, Hospital input)
 {
    log.debug("Request updateHospital: {} {}", id, input);

    var hospital = hospitalRepository.findById(id).map(existingHospital -> {
        return hospitalServiceMapper.update(existingHospital, input);
    })
    .map(hospitalRepository::save)
    ;
    return hospital;


}


    public List<Hospital> listHospitals()
 {
    log.debug("Request listHospitals");

    var hospitals = hospitalRepository.findAll();
    return hospitals;


}

    @Transactional

    public Doctor createDoctor(Doctor input)
 {
    log.debug("[CRUD] Request to save Doctor: {}", input);
    var doctor = hospitalServiceMapper.update(new Doctor(), input);
    doctor = doctorRepository.save(doctor);
    // TODO: may need to reload the entity to fetch relationships 'mapped by id'
        // emit events
            var doctorCreated = eventsMapper.asDoctorCreated(input);
            eventPublisher.onDoctorCreated(doctorCreated);
    return doctor;


}

    @Transactional

    public Optional<Doctor> updateDoctor(Long id, Doctor input)
 {
    log.debug("Request updateDoctor: {} {}", id, input);

    var doctor = doctorRepository.findById(id).map(existingDoctor -> {
        return hospitalServiceMapper.update(existingDoctor, input);
    })
    .map(doctorRepository::save)
    ;
    return doctor;


}


    public Optional<Doctor> getDoctor(Long id)
 {
    log.debug("[CRUD] Request to get Doctor : {}", id);
    var doctor = doctorRepository.findById(id);
    return doctor;


}


    public List<Doctor> listDoctors()
 {
    log.debug("Request listDoctors");

    var doctors = doctorRepository.findAll();
    return doctors;


}


    public List<Doctor> listHospitalDoctors(Long hospitalId)
 {
    log.debug("Request listHospitalDoctors: {}", hospitalId);

    var doctors = doctorRepository.findAll();
    return doctors;


}


    public List<PatientHospital> listHospitalPatients(Long hospitalId)
 {
    log.debug("Request listHospitalPatients: {}", hospitalId);

    var hospitals = hospitalRepository.findAll();
    return hospitalServiceMapper.asPatientHospitalList(hospitals);


}



}
