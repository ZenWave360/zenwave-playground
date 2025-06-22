package io.zenwave360.example.clinicaltool.modules.documents.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import io.zenwave360.example.clinicaltool.modules.documents.core.outbound.jpa.*;
import io.zenwave360.example.clinicaltool.modules.documents.infrastructure.jpa.inmemory.*;


//@Configuration
public class RepositoriesInMemoryConfig {


    protected final DocumentInfoRepository documentInfoRepository = new DocumentInfoRepositoryInMemory();
    @Bean @Primary
    public <T extends DocumentInfoRepository> T documentInfoRepository() {
        return (T) documentInfoRepository;
    }

}
