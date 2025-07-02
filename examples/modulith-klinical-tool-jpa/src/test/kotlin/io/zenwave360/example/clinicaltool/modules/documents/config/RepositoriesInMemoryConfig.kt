package io.zenwave360.example.clinicaltool.modules.documents.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

import io.zenwave360.example.clinicaltool.modules.documents.*
import io.zenwave360.example.clinicaltool.modules.documents.inmemory.*


//@Configuration
open class RepositoriesInMemoryConfig {


    protected val documentInfoRepository = DocumentInfoRepositoryInMemory()

    @Bean
    @Primary
    fun documentInfoRepository(): DocumentInfoRepositoryInMemory {
        return documentInfoRepository
    }

}
