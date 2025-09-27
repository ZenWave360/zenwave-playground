package io.zenwave360.example.clinicaltool.modules.masterdata.config;

import io.zenwave360.example.clinicaltool.modules.masterdata.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.inmemory.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

// @Configuration
public class RepositoriesInMemoryConfig {

    protected final MasterDataRepository masterDataRepository = new MasterDataRepositoryInMemory();

    @Bean
    @Primary
    public <T extends MasterDataRepository> T masterDataRepository() {
        return (T) masterDataRepository;
    }
}
