package io.zenwave360.example.clinicaltool.modules.clinical.config

import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.*
import io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

// @Configuration
open class RepositoriesInMemoryConfig {

    protected val hospitalRepository = HospitalRepositoryInMemory()

    @Bean
    @Primary
    fun hospitalRepository(): HospitalRepositoryInMemory {
        return hospitalRepository
    }

    protected val doctorRepository = DoctorRepositoryInMemory()

    @Bean
    @Primary
    fun doctorRepository(): DoctorRepositoryInMemory {
        return doctorRepository
    }

    protected val patientRepository = PatientRepositoryInMemory()

    @Bean
    @Primary
    fun patientRepository(): PatientRepositoryInMemory {
        return patientRepository
    }
}
