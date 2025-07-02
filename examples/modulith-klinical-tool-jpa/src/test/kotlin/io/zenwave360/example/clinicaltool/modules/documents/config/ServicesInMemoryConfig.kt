package io.zenwave360.example.clinicaltool.modules.documents.config


import io.zenwave360.example.clinicaltool.modules.documents.domain.*


import io.zenwave360.example.clinicaltool.modules.documents.*
import io.zenwave360.example.clinicaltool.modules.documents.*


    

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/**
 * Services InMemory Config. It can be used standalone or with @SpringBootTest.
 */
@Configuration
@Profile("in-memory")
open class ServicesInMemoryConfig : RepositoriesInMemoryConfig() {



    protected val documentService = DocumentServiceImpl(documentInfoRepository()
        )
    @Bean
    open fun <T : DocumentService> documentService(): T {
        return documentService as T
    }


	companion object {
		var _documentInfos: List<DocumentInfo>? = null
	}

	fun reloadTestData() {
        
		val testDataLoader = TestDataLoader(listOf(DocumentInfo::class.java, DocumentData::class.java))
		val documentInfos = _documentInfos ?: testDataLoader.loadCollectionTestDataAsObjects(DocumentInfo::class.java)
		documentInfoRepository().deleteAll()
		documentInfoRepository().saveAll(documentInfos)
	}
}
