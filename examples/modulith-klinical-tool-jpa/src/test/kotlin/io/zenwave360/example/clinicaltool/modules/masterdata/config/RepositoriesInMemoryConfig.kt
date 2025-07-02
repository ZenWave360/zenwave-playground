package io.zenwave360.example.clinicaltool.modules.masterdata.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

import io.zenwave360.example.clinicaltool.modules.masterdata.*
import io.zenwave360.example.clinicaltool.modules.masterdata.inmemory.*


//@Configuration
open class RepositoriesInMemoryConfig {


    protected val masterDataRepository = MasterDataRepositoryInMemory()

    @Bean
    @Primary
    fun masterDataRepository(): MasterDataRepositoryInMemory {
        return masterDataRepository
    }

}
