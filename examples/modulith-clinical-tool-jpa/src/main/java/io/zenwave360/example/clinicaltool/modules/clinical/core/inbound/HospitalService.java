package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * Inbound Service Port for managing [Hospital, Doctor].
 */
@org.springframework.modulith.NamedInterface("HospitalService")
public interface HospitalService {

    /**
     *
     *
     */
    public Optional<Hospital> getHospital(Long id);
    /**
     *
     *
     */
    public Hospital createHospital(Hospital input);
    /**
     *
     *
     */
    public Optional<Hospital> updateHospital(Long id, Hospital input);
    /**
     *
     *
     */
    public List<Hospital> listHospitals();
    /**
     *
     *
     * With Events: [DoctorCreated].
     *
     */
    public Doctor createDoctor(Doctor input);
    /**
     *
     *
     */
    public Optional<Doctor> updateDoctor(Long id, Doctor input);
    /**
     *
     *
     */
    public Optional<Doctor> getDoctor(Long id);
    /**
     *
     *
     */
    public List<Doctor> listDoctors();
    /**
     *
     *
     */
    public List<Doctor> listHospitalDoctors(Long hospitalId);
    /**
     *
     *
     */
    public List<PatientHospital> listHospitalPatients(Long hospitalId);
}
