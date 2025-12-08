package io.zenwave360.example.clinicaltool.modules.masterdata

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import jakarta.persistence.EntityManager
import java.time.*
import java.util.List
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class MasterDataRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired lateinit var entityManager: EntityManager

    @Autowired lateinit var masterDataRepository: MasterDataRepository

    @Test
    fun findAllTest() {
        val results = masterDataRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val masterData =
            masterDataRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        Assertions.assertNotNull(masterData.id)
        Assertions.assertNotNull(masterData.version)
    }

    @Test
    fun saveTest() {
        val masterData = MasterData()
        masterData.type = MasterDataType.values()[0]
        masterData.key = ""
        masterData.value = ""
        masterData.translations = mutableListOf(MasterDataTranslation())

        // Persist aggregate root
        val created = masterDataRepository.save(masterData)

        // reloading to get relationships persisted by id
        entityManager.flush()
        entityManager.refresh(created)
        Assertions.assertNotNull(created.id)
        Assertions.assertNotNull(created.version)
    }

    @Test
    fun updateTest() {
        val id = 1L
        val masterData =
            masterDataRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        masterData.type = MasterDataType.values()[0]
        masterData.key = ""
        masterData.value = ""
        masterData.translations = mutableListOf(MasterDataTranslation())

        val updated = masterDataRepository.save(masterData)
        Assertions.assertEquals(MasterDataType.values()[0], updated.type)
        Assertions.assertEquals("", updated.key)
        Assertions.assertEquals("", updated.value)
//        Assertions.assertEquals(mutableListOf(MasterDataTranslation()), updated.translations)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        masterDataRepository.deleteById(id)
        val notFound = masterDataRepository.findByIdOrNull(id)
        Assertions.assertNull(notFound)
    }
}
