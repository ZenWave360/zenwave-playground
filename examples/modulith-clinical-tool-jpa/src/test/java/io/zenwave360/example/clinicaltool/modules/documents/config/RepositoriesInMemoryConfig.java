package io.zenwave360.example.clinicaltool.modules.documents.config;

import io.zenwave360.example.clinicaltool.modules.documents.*;
import io.zenwave360.example.clinicaltool.modules.documents.inmemory.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

// @Configuration
public class RepositoriesInMemoryConfig {

    protected final DocumentInfoRepository documentInfoRepository = new DocumentInfoRepositoryInMemory();

    @Bean
    @Primary
    public <T extends DocumentInfoRepository> T documentInfoRepository() {
        return (T) documentInfoRepository;
    }
}
