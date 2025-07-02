package io.zenwave360.example.clinicaltool.modules.masterdata

import io.zenwave360.example.clinicaltool.modules.masterdata.config.*
import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import io.zenwave360.example.clinicaltool.modules.masterdata.*
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*
import io.zenwave360.example.clinicaltool.modules.masterdata.mappers.*
import io.zenwave360.example.clinicaltool.modules.masterdata.*
import io.zenwave360.example.clinicaltool.modules.masterdata.inmemory.*

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
 * Acceptance Test for MasterDataService.
 */
class MasterDataServiceTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    val context = ServicesInMemoryConfig()
    val masterDataService: MasterDataService = context.masterDataService()

    val masterDataRepository: MasterDataRepositoryInMemory = context.masterDataRepository()


    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun createMasterDataTest() {
        val input: MasterData = MasterData() // TODO
        // TODO fill input data
        // input.type = MasterDataType.values()[0]
        // input.key = ""
        // input.value = ""
        // input.translations = List.of(MasterDataTranslation())
        val masterData = masterDataService.createMasterData(input)
        assertNotNull(masterData.id)
        assertTrue(masterDataRepository.containsEntity(masterData))
}

    @Test
    fun getMasterDataTest() {
        val id: Long = 1L
        val masterData = masterDataService.getMasterData(id)
        assertTrue(masterData.isPresent)
}

    @Test
    fun updateMasterDataTest() {
        val id: Long = 1L
        val input: MasterData = MasterData() // TODO
        // TODO fill input data
        // input.type = MasterDataType.values()[0]
        // input.key = ""
        // input.value = ""
        // input.translations = List.of(MasterDataTranslation())
        // assertTrue(masterDataRepository.containsKey(id))
        val masterData = masterDataService.updateMasterData(id, input)
        assertTrue(masterData.isPresent)
        assertTrue(masterDataRepository.containsEntity(masterData.get()))
}

    @Test
    fun listMasterDataTest() {
        // val results = masterDataService.listMasterData(PageRequest.of(0, 10))
        // assertNotNull(results)
}

    @Test
    fun deleteMasterDataTest() {
        val id: Long = 1L
        // assertTrue(masterDataRepository.containsKey(id))
        masterDataService.deleteMasterData(id)
        // assertFalse(masterDataRepository.containsKey(id))
}

    @Test
    fun listMasterDataOfTypeTest() {// TODO: implement this test
}

}
