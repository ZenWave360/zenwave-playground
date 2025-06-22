package io.zenwave360.example.clinicaltool.modules.masterdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import io.zenwave360.example.clinicaltool.modules.masterdata.core.outbound.jpa.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.infrastructure.jpa.inmemory.*;


//@Configuration
public class RepositoriesInMemoryConfig {


    protected final MasterDataRepository masterDataRepository = new MasterDataRepositoryInMemory();
    @Bean @Primary
    public <T extends MasterDataRepository> T masterDataRepository() {
        return (T) masterDataRepository;
    }

}
