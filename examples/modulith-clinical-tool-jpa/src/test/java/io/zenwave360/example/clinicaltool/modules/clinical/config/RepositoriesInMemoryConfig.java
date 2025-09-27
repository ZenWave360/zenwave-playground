package io.zenwave360.example.clinicaltool.modules.clinical.config;

import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.*;
import io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

// @Configuration
public class RepositoriesInMemoryConfig {

    protected final HospitalRepository hospitalRepository = new HospitalRepositoryInMemory();

    @Bean
    @Primary
    public <T extends HospitalRepository> T hospitalRepository() {
        return (T) hospitalRepository;
    }

    protected final DoctorRepository doctorRepository = new DoctorRepositoryInMemory();

    @Bean
    @Primary
    public <T extends DoctorRepository> T doctorRepository() {
        return (T) doctorRepository;
    }

    protected final PatientRepository patientRepository = new PatientRepositoryInMemory();

    @Bean
    @Primary
    public <T extends PatientRepository> T patientRepository() {
        return (T) patientRepository;
    }

    protected final ProvisionalPatientRepository provisionalPatientRepository =
            new ProvisionalPatientRepositoryInMemory();

    @Bean
    @Primary
    public <T extends ProvisionalPatientRepository> T provisionalPatientRepository() {
        return (T) provisionalPatientRepository;
    }
}
