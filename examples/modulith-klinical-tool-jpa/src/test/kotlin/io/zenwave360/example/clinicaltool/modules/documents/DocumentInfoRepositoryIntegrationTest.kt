package io.zenwave360.example.clinicaltool.modules.documents

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.documents.domain.*
import io.zenwave360.example.clinicaltool.modules.documents.DocumentInfoRepository

import java.util.HashSet
import java.util.HashMap
import java.util.List
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import jakarta.persistence.EntityManager

class DocumentInfoRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var documentInfoRepository: DocumentInfoRepository

    @Test
    fun findAllTest() {
        val results = documentInfoRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val documentInfo = documentInfoRepository.findById(id).orElseThrow()
        Assertions.assertNotNull(documentInfo.id)
        Assertions.assertNotNull(documentInfo.version)
    }

    @Test
    fun saveTest() {
        val documentInfo = DocumentInfo()
        documentInfo.uuid = ""
        documentInfo.fileName = ""
        documentInfo.documentType = ""
        documentInfo.contentType = ""
        documentInfo.tags = List.of("")


        // OneToOne documentData owner: true
        val documentDataId = 1L
        val documentData = DocumentData()
        documentData.data = null
        documentInfo.documentData = documentData


        // Persist aggregate root
        val created = documentInfoRepository.save(documentInfo)

        // reloading to get relationships persisted by id
        entityManager.flush()
        entityManager.refresh(created)
        Assertions.assertNotNull(created.id)
        Assertions.assertNotNull(created.version)


        Assertions.assertNotNull(documentInfo.documentData?.id != null)
    }

    @Test
    fun updateTest() {
        val id = 1L
        val documentInfo = documentInfoRepository.findById(id).orElseThrow()
        documentInfo.uuid = ""
        documentInfo.fileName = ""
        documentInfo.documentType = ""
        documentInfo.contentType = ""
        documentInfo.tags = List.of("")

        val updated = documentInfoRepository.save(documentInfo)
        Assertions.assertEquals("", updated.uuid)
        Assertions.assertEquals("", updated.fileName)
        Assertions.assertEquals("", updated.documentType)
        Assertions.assertEquals("", updated.contentType)
        Assertions.assertEquals(List.of(""), updated.tags)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        documentInfoRepository.deleteById(id)
        val notFound = documentInfoRepository.findById(id)
        Assertions.assertFalse(notFound.isPresent)
    }
}