package io.zenwave360.example.clinicaltool.modules.clinical.config;

import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.DoctorRepository;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.HospitalRepository;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.PatientRepository;
import io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory.DoctorRepositoryInMemory;
import io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory.HospitalRepositoryInMemory;
import io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory.PatientRepositoryInMemory;
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
}
