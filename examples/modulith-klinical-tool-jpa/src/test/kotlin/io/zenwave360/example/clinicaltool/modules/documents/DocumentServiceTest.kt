package io.zenwave360.example.clinicaltool.modules.documents

import io.zenwave360.example.clinicaltool.modules.documents.config.*
import io.zenwave360.example.clinicaltool.modules.documents.domain.*
import io.zenwave360.example.clinicaltool.modules.documents.*
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*
import io.zenwave360.example.clinicaltool.modules.documents.mappers.*
import io.zenwave360.example.clinicaltool.modules.documents.*
import io.zenwave360.example.clinicaltool.modules.documents.inmemory.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.PageRequest

import java.util.Map
import java.util.Optional
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.mockito.Mockito.*

/**
 * Acceptance Test for DocumentService.
 */
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
    fun listDocumentInfoTest() {// TODO: implement this test
}

    @Test
    fun downloadDocumentTest() {// TODO: implement this test
}

    @Test
    fun uploadDocumentTest() {// TODO: implement this test
}

    @Test
    fun deleteDocumentInfoTest() {
        val id: Long = 1L
        // assertTrue(documentInfoRepository.containsKey(id))
        documentService.deleteDocumentInfo(id)
        // assertFalse(documentInfoRepository.containsKey(id))
}

}
