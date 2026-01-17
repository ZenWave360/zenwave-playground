package io.zenwave360.example.clinicaltool.modules.clinical.config;

import io.zenwave360.example.clinicaltool.common.TestDataLoader;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.PatientsService;
import io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.events.*;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Services InMemory Config. It can be used standalone or with @SpringBootTest.
 */
@Configuration
@Profile("in-memory")
public class ServicesInMemoryConfig extends RepositoriesInMemoryConfig {

    private InMemoryEventPublisher eventPublisher = new InMemoryEventPublisher();
    protected final HospitalServiceImpl hospitalService =
            new HospitalServiceImpl(hospitalRepository(), doctorRepository(), eventPublisher);
    protected final PatientsServiceImpl patientsService = new PatientsServiceImpl(patientRepository(), eventPublisher);

    @Bean
    public <T extends HospitalService> T hospitalService() {
        return (T) hospitalService;
    }

    @Bean
    public <T extends PatientsService> T patientsService() {
        return (T) patientsService;
    }

    static List<Hospital> _hospitals;
    static List<Doctor> _doctors;
    static List<Patient> _patients;

    public void reloadTestData() {

        var testDataLoader = new TestDataLoader(List.of(
                Hospital.class,
                Doctor.class,
                Patient.class,
                GeneralInfo.class,
                HealthInsuranceInfo.class,
                PatientAddress.class,
                HospitalAddress.class,
                PatientWearable.class,
                MedicalContact.class,
                PersonalContact.class));
        var hospitals =
                _hospitals != null ? _hospitals : testDataLoader.loadCollectionTestDataAsObjects(Hospital.class);
        hospitalRepository().deleteAll();
        hospitalRepository().saveAll(hospitals);
        var doctors = _doctors != null ? _doctors : testDataLoader.loadCollectionTestDataAsObjects(Doctor.class);
        doctorRepository().deleteAll();
        doctorRepository().saveAll(doctors);
        var patients = _patients != null ? _patients : testDataLoader.loadCollectionTestDataAsObjects(Patient.class);
        patientRepository().deleteAll();
        patientRepository().saveAll(patients);
        eventPublisher.getEvents().clear();
    }

    @Bean
    public InMemoryEventPublisher eventPublisher() {
        return eventPublisher;
    }
}
