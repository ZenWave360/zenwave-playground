package io.example.asyncapi.provider.applicantscoring.config;

import io.zenwave360.example.applicantscoring.repository.jpa.*;
import io.zenwave360.example.applicantscoring.repository.jpa.inmemory.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

// @Configuration
public class RepositoriesInMemoryConfig {

    protected final ApplicantScoringRepository applicantScoringRepository = new ApplicantScoringRepositoryInMemory();

    @Bean
    @Primary
    public <T extends ApplicantScoringRepository> T applicantScoringRepository() {
        return (T) applicantScoringRepository;
    }
}
