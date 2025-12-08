package io.zenwave360.example.clinicaltool.modules.documents.config

import io.zenwave360.example.clinicaltool.modules.documents.*
import io.zenwave360.example.clinicaltool.modules.documents.inmemory.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

// @Configuration
open class RepositoriesInMemoryConfig {

    protected val documentInfoRepository = DocumentInfoRepositoryInMemory()

    @Bean
    @Primary
    fun documentInfoRepository(): DocumentInfoRepositoryInMemory {
        return documentInfoRepository
    }
}
