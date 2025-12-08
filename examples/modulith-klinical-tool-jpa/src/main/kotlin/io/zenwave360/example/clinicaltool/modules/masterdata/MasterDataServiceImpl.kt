package io.zenwave360.example.clinicaltool.modules.masterdata

import io.zenwave360.example.clinicaltool.modules.masterdata.*
import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*
import io.zenwave360.example.clinicaltool.modules.masterdata.mappers.*
import java.math.*
import java.time.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/** Service Implementation for managing [MasterData]. */
@Service
@Transactional(readOnly = true)
open class MasterDataServiceImpl(private val masterDataRepository: MasterDataRepository) : MasterDataService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val masterDataServiceMapper: MasterDataServiceMapper = MasterDataServiceMapper.INSTANCE

    @Transactional
    override fun createMasterData(input: MasterData): MasterData {
        log.debug("[CRUD] Request to save MasterData: {}", input)
        var masterData = masterDataServiceMapper.update(MasterData(), input)
        masterData = masterDataRepository.save(masterData)
        // TODO: may need to reload the entity to fetch relationships 'mapped by id'
        return masterData
    }

    override fun getMasterData(id: Long): MasterData? {
        log.debug("[CRUD] Request to get MasterData : {}", id)
        val masterData = masterDataRepository.findByIdOrNull(id)
        return masterData
    }

    @Transactional
    override fun updateMasterData(id: Long, input: MasterData): MasterData? {
        log.debug("Request updateMasterData: {} {}", id, input)

        return masterDataRepository
            .findByIdOrNull(id)
            ?.let { existingMasterData -> masterDataServiceMapper.update(existingMasterData, input) }
            ?.let { masterDataRepository.save(it) }
    }

    override fun listMasterData(pageable: Pageable): Page<MasterData> {
        log.debug("Request listMasterData: {}", pageable)

        val masterData = masterDataRepository.findAll(pageable)
        return masterData
    }

    @Transactional
    override fun deleteMasterData(id: Long): Unit {
        log.debug("[CRUD] Request to delete MasterData : {}", id)
        masterDataRepository.deleteById(id)
    }

    override fun listMasterDataOfType(type: MasterDataType?, lang: String?): List<MasterDataKeyValue> {
        log.debug("Request listMasterDataOfType: {} {}", type, lang)

        val masterData = masterDataRepository.findAll()
        return masterDataServiceMapper.asMasterDataKeyValueList(masterData)
    }
}
