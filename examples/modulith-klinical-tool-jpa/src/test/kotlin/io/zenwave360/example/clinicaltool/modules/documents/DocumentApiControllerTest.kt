package io.zenwave360.example.clinicaltool.modules.documents

import io.zenwave360.example.clinicaltool.modules.documents.*
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*
import io.zenwave360.example.clinicaltool.modules.documents.config.ServicesInMemoryConfig

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.math.*
import java.time.*
import java.util.*

/**
 * Test controller for DocumentApiController.
 */
class DocumentApiControllerTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val context = ServicesInMemoryConfig()

    private val controller = DocumentApiController( context.documentService() )

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun listDocumentInfoTest() {
        val documentIds: List<Long> = mutableListOf(1L)
        val response = controller.listDocumentInfo(documentIds)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun downloadDocumentTest() {
        val id: Long = 1L
        val preview: Boolean = false
        val response = controller.downloadDocument(id, preview)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun deleteDocumentInfoTest() {
        val id: Long = 1L
        val response = controller.deleteDocumentInfo(id)
        Assertions.assertEquals(204, response.statusCode.value())
    }

    @Test
    fun uploadDocumentTest() {
        val file: org.springframework.web.multipart.MultipartFile = org.springframework.mock.web.MockMultipartFile(
            "file",
            "test-document.pdf",
            "application/pdf",
            "test content".toByteArray()
        )
        val uuid: String = "550e8400-e29b-41d4-a716-446655440001"
        val tags: List<String> = mutableListOf("test", "document")
        val response = controller.uploadDocument(uuid, tags, file)
        Assertions.assertEquals(201, response.statusCode.value())
    }


}
