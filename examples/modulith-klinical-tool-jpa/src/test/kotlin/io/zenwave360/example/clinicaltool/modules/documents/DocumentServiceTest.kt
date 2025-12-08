package io.zenwave360.example.clinicaltool.modules.documents

import io.zenwave360.example.clinicaltool.modules.documents.*
import io.zenwave360.example.clinicaltool.modules.documents.config.*
import io.zenwave360.example.clinicaltool.modules.documents.domain.*
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*
import io.zenwave360.example.clinicaltool.modules.documents.inmemory.*
import io.zenwave360.example.clinicaltool.modules.documents.mappers.*
import java.time.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/** Acceptance Test for DocumentService. */
class DocumentServiceTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    val context = ServicesInMemoryConfig()
    val documentService: DocumentService = context.documentService()

    val documentInfoRepository: DocumentInfoRepositoryInMemory = context.documentInfoRepository()

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }

    @Test
    fun listDocumentInfoTest() { // TODO: implement this test
    }

    @Test
    fun downloadDocumentTest() { // TODO: implement this test
    }

    @Test
    fun uploadDocumentTest() { // TODO: implement this test
    }

    @Test
    fun deleteDocumentInfoTest() {
        val id: Long = 1L
        // assertTrue(documentInfoRepository.containsKey(id))
        documentService.deleteDocumentInfo(id)
        // assertFalse(documentInfoRepository.containsKey(id))
    }
}
