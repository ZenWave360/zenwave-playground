package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation;

import io.zenwave360.example.clinicaltool.modules.clinical.config.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.*;
import io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.Map;
import java.util.Optional;
import java.time.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Acceptance Test for HospitalService.
 */
class HospitalServiceTest  {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    HospitalServiceImpl hospitalService = context.hospitalService();

    HospitalRepositoryInMemory hospitalRepository = context.hospitalRepository();

    DoctorRepositoryInMemory doctorRepository = context.doctorRepository();

	@BeforeEach
	void setUp() {
		context.reloadTestData();
	}



    @Test
    void getHospitalTest() {
        Long id = null;
        var hospital = hospitalService.getHospital(id);
        assertTrue(hospital.isPresent());// TODO: implement this test
}

    @Test
    void createHospitalTest() {
        Hospital input = null; // TODO
        // TODO fill input data
        // input.setName("");
        // input.setLang("");
        // input.setTimezone("");
        var hospital = hospitalService.createHospital(input);
        assertNotNull(hospital.getId());
        assertTrue(hospitalRepository.containsEntity(hospital));// TODO: implement this test
}

    @Test
    void updateHospitalTest() {
        Long id = null;
        Hospital input = null; // TODO
        // TODO fill input data
        // input.setName("");
        // input.setLang("");
        // input.setTimezone("");
        // assertTrue(hospitalRepository.containsKey(id));
        var hospital = hospitalService.updateHospital(id, input);
        assertTrue(hospital.isPresent());
        assertTrue(hospitalRepository.containsEntity(hospital.get()));// TODO: implement this test
}

    @Test
    void listHospitalsTest() {
        // var results = hospitalService.listHospitals(PageRequest.of(0, 10));
        // assertNotNull(results);// TODO: implement this test
}

    @Test
    void createDoctorTest() {// TODO: implement this test
        Doctor input = null; // TODO
        // TODO fill input data
        // input.setUserId(0L);
        // input.setProfilePictureId(0L);
        // input.setHospitalId(0L);
        // input.setName("");
        // input.setSurname("");
        // input.setSurname2("");
        // input.setEmail("");
        // input.setPhoneNumber("");
        // input.setLang("");
        var doctor = hospitalService.createDoctor(input);
        assertNotNull(doctor.getId());
        assertTrue(doctorRepository.containsEntity(doctor));
}

    @Test
    void updateDoctorTest() {// TODO: implement this test
        Long id = null;
        Doctor input = null; // TODO
        // TODO fill input data
        // input.setUserId(0L);
        // input.setProfilePictureId(0L);
        // input.setHospitalId(0L);
        // input.setName("");
        // input.setSurname("");
        // input.setSurname2("");
        // input.setEmail("");
        // input.setPhoneNumber("");
        // input.setLang("");
        // assertTrue(doctorRepository.containsKey(id));
        var doctor = hospitalService.updateDoctor(id, input);
        assertTrue(doctor.isPresent());
        assertTrue(doctorRepository.containsEntity(doctor.get()));
}

    @Test
    void getDoctorTest() {// TODO: implement this test
        Long id = null;
        var doctor = hospitalService.getDoctor(id);
        assertTrue(doctor.isPresent());
}

    @Test
    void listDoctorsTest() {// TODO: implement this test
        // var results = hospitalService.listDoctors(PageRequest.of(0, 10));
        // assertNotNull(results);
}

    @Test
    void listHospitalDoctorsTest() {// TODO: implement this test// TODO: implement this test
}

    @Test
    void listHospitalPatientsTest() {// TODO: implement this test// TODO: implement this test
}

}
