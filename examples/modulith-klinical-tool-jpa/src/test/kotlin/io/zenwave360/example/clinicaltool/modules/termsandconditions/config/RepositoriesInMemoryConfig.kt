package io.zenwave360.example.clinicaltool.modules.termsandconditions.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

import io.zenwave360.example.clinicaltool.modules.termsandconditions.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.inmemory.*


//@Configuration
open class RepositoriesInMemoryConfig {


    protected val acceptedTermsAndConditionsRepository = AcceptedTermsAndConditionsRepositoryInMemory()

    @Bean
    @Primary
    fun acceptedTermsAndConditionsRepository(): AcceptedTermsAndConditionsRepositoryInMemory {
        return acceptedTermsAndConditionsRepository
    }
    protected val termsAndConditionsRepository = TermsAndConditionsRepositoryInMemory()

    @Bean
    @Primary
    fun termsAndConditionsRepository(): TermsAndConditionsRepositoryInMemory {
        return termsAndConditionsRepository
    }

}
