package io.zenwave360.example.clinicaltool.modules.masterdata

import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import io.zenwave360.example.clinicaltool.modules.masterdata.*
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*
import io.zenwave360.example.clinicaltool.modules.masterdata.*
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*
import io.zenwave360.example.clinicaltool.common.*
import io.zenwave360.example.clinicaltool.modules.masterdata.mappers.*

import java.net.URI
import java.net.URISyntaxException
import java.math.*
import java.time.*
import java.util.*
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.mapstruct.factory.Mappers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.web.context.request.NativeWebRequest

/**
 * REST controller for MasterDataApi.
 */
@RestController
@RequestMapping("/api")
open class MasterDataApiController(
    private val masterDataService: MasterDataService
) : MasterDataApi {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var request: NativeWebRequest

    private val mapper = MasterDataDTOsMapper.INSTANCE



    override fun createMasterData(reqBody: MasterDataDTO): ResponseEntity<MasterDataDTO> {
        log.debug("REST request to createMasterData: {}", reqBody)
        val input = mapper.asMasterData(reqBody)
        val masterData =  masterDataService.createMasterData(input)
        val responseDTO = mapper.asMasterDataDTO(masterData)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun listMasterData(page: Int, limit: Int, sort: List<String>?): ResponseEntity<MasterDataPaginatedDTO> {
        log.debug("REST request to listMasterData: {}, {}, {}", page, limit, sort)
        val masterDataPage =  masterDataService.listMasterData(pageOf(page, limit, sort))
        val responseDTO = mapper.asMasterDataPaginatedDTO(masterDataPage)
        return ResponseEntity.status(200).body(responseDTO)
    }

    override fun getMasterData(id: Long): ResponseEntity<MasterDataDTO> {
        log.debug("REST request to getMasterData: {}", id)
        val masterData =  masterDataService.getMasterData(id)
        return if (masterData != null) {
            val responseDTO = mapper.asMasterDataDTO(masterData)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun updateMasterData(id: Long, reqBody: MasterDataDTO): ResponseEntity<MasterDataDTO> {
        log.debug("REST request to updateMasterData: {}, {}", id, reqBody)
        val input = mapper.asMasterData(reqBody)
        val masterData =  masterDataService.updateMasterData(id, input)
        return if (masterData != null) {
            val responseDTO = mapper.asMasterDataDTO(masterData)
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun deleteMasterData(id: Long): ResponseEntity<Unit> {
        log.debug("REST request to deleteMasterData: {}", id)
        masterDataService.deleteMasterData(id)
        return ResponseEntity.status(204).build()
    }

    override fun listMasterDataOfType(type: String, lang: String): ResponseEntity<List<MasterDataKeyValueDTO>> {
        log.debug("REST request to listMasterDataOfType: {}, {}", type, lang)
        val masterDataKeyValue =  masterDataService.listMasterDataOfType(MasterDataType.valueOf(type), lang)
        val responseDTO = mapper.asMasterDataKeyValueDTOList(masterDataKeyValue)
        return ResponseEntity.status(200).body(responseDTO)
    }


    protected fun pageOf(page: Int?, limit: Int?, sort: List<String>?): Pageable {
        val sortOrder = sort?.let {
            Sort.by(it.map { sortParam ->
                val parts = sortParam.split(":")
                val property = parts[0]
                val direction = if (parts.size > 1) Sort.Direction.fromString(parts[1]) else Sort.Direction.ASC
                Sort.Order(direction, property)
            })
        } ?: Sort.unsorted()
        return PageRequest.of(page ?: 0, limit ?: 10, sortOrder)
    }
}
