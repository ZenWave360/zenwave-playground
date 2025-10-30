package io.zenwave360.example.clinicaltool.modules.masterdata;

import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.mappers.*;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing [MasterData].
 */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class MasterDataServiceImpl implements MasterDataService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MasterDataServiceMapper masterDataServiceMapper = MasterDataServiceMapper.INSTANCE;

    private final MasterDataRepository masterDataRepository;

    @Transactional
    public MasterData createMasterData(MasterData input) {
        log.debug("[CRUD] Request to save MasterData: {}", input);
        var masterData = masterDataServiceMapper.update(new MasterData(), input);
        masterData = masterDataRepository.save(masterData);
        // TODO: may need to reload the entity to fetch relationships 'mapped by id'
        return masterData;
    }

    public Optional<MasterData> getMasterData(Long id) {
        log.debug("[CRUD] Request to get MasterData : {}", id);
        var masterData = masterDataRepository.findById(id);
        return masterData;
    }

    @Transactional
    public Optional<MasterData> updateMasterData(Long id, MasterData input) {
        log.debug("Request updateMasterData: {} {}", id, input);

        var masterData = masterDataRepository
                .findById(id)
                .map(existingMasterData -> {
                    return masterDataServiceMapper.update(existingMasterData, input);
                })
                .map(masterDataRepository::save);
        return masterData;
    }

    public Page<MasterData> listMasterData(Pageable pageable) {
        log.debug("Request listMasterData: {}", pageable);

        var masterData = masterDataRepository.findAll(pageable);
        return masterData;
    }

    @Transactional
    public void deleteMasterData(Long id) {
        log.debug("[CRUD] Request to delete MasterData : {}", id);
        masterDataRepository.deleteById(id);
    }

    public List<MasterDataKeyValue> listMasterDataOfType(MasterDataType type, String lang) {
        log.debug("Request listMasterDataOfType: {} {}", type, lang);

        var masterData = masterDataRepository.findByType(type);
        return masterDataServiceMapper.asMasterDataKeyValueList(masterData, lang);
    }
}
