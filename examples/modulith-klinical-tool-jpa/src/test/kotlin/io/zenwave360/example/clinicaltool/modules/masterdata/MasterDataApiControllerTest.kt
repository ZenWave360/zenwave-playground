package io.zenwave360.example.clinicaltool.modules.masterdata

import io.zenwave360.example.clinicaltool.modules.masterdata.*
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*
import io.zenwave360.example.clinicaltool.modules.masterdata.config.ServicesInMemoryConfig

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.math.*
import java.time.*
import java.util.*

/**
 * Test controller for MasterDataApiController.
 */
class MasterDataApiControllerTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val context = ServicesInMemoryConfig()

    private val controller = MasterDataApiController( context.masterDataService() )

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun createMasterDataTest() {
        val reqBody: MasterDataDTO = MasterDataDTO(type = MasterDataTypeDTO.GENDER, key = "aaa", value = "aaa")
        val response = controller.createMasterData(reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun listMasterDataTest() {
        val page: Int = 0
val limit: Int = 0
val sort: List<String> = mutableListOf()
        val response = controller.listMasterData(page, limit, sort)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun getMasterDataTest() {
        val id: Long = 0L
        val response = controller.getMasterData(id)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun updateMasterDataTest() {
        val id: Long = 0L
val reqBody: MasterDataDTO = MasterDataDTO(type = MasterDataTypeDTO.GENDER, key = "aaa", value = "aaa")
        val response = controller.updateMasterData(id, reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun deleteMasterDataTest() {
        val id: Long = 0L
        val response = controller.deleteMasterData(id)
        Assertions.assertEquals(204, response.statusCode.value())
    }

    @Test
    fun listMasterDataOfTypeTest() {
        val type: String = ""
val lang: String = ""
        val response = controller.listMasterDataOfType(type, lang)
        Assertions.assertEquals(200, response.statusCode.value())
    }


}
