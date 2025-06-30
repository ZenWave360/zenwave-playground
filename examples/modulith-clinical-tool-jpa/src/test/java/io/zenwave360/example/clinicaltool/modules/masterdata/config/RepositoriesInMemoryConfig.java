package io.zenwave360.example.clinicaltool.modules.masterdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import io.zenwave360.example.clinicaltool.modules.masterdata.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.inmemory.*;


//@Configuration
public class RepositoriesInMemoryConfig {


    protected final MasterDataRepository masterDataRepository = new MasterDataRepositoryInMemory();
    @Bean @Primary
    public <T extends MasterDataRepository> T masterDataRepository() {
        return (T) masterDataRepository;
    }

}
