package io.zenwave360.example.clinicaltool.modules.termsandconditions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.outbound.jpa.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.infrastructure.jpa.inmemory.*;


//@Configuration
public class RepositoriesInMemoryConfig {


    protected final AcceptedTermsAndConditionsRepository acceptedTermsAndConditionsRepository = new AcceptedTermsAndConditionsRepositoryInMemory();
    @Bean @Primary
    public <T extends AcceptedTermsAndConditionsRepository> T acceptedTermsAndConditionsRepository() {
        return (T) acceptedTermsAndConditionsRepository;
    }
    protected final TermsAndConditionsRepository termsAndConditionsRepository = new TermsAndConditionsRepositoryInMemory();
    @Bean @Primary
    public <T extends TermsAndConditionsRepository> T termsAndConditionsRepository() {
        return (T) termsAndConditionsRepository;
    }

}
