package io.zenwave360.example.clinicaltool.modules.masterdata;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import io.zenwave360.example.clinicaltool.modules.masterdata.config.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.inmemory.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.mappers.*;
import java.time.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acceptance Test for MasterDataService.
 */
class MasterDataServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    MasterDataServiceImpl masterDataService = context.masterDataService();

    MasterDataRepositoryInMemory masterDataRepository = context.masterDataRepository();

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void createMasterDataTest() {
        MasterData input = null; // TODO
        // TODO fill input data
        // input.setType(MasterDataType.values()[0]);
        // input.setKey("");
        // input.setValue("");
        // input.setTranslations(List.of(new MasterDataTranslation()));
        var masterData = masterDataService.createMasterData(input);
        assertNotNull(masterData.getId());
        assertTrue(masterDataRepository.containsEntity(masterData));
    }

    @Test
    void getMasterDataTest() {
        Long id = null;
        var masterData = masterDataService.getMasterData(id);
        assertTrue(masterData.isPresent());
    }

    @Test
    void updateMasterDataTest() {
        Long id = null;
        MasterData input = null; // TODO
        // TODO fill input data
        // input.setType(MasterDataType.values()[0]);
        // input.setKey("");
        // input.setValue("");
        // input.setTranslations(List.of(new MasterDataTranslation()));
        // assertTrue(masterDataRepository.containsKey(id));
        var masterData = masterDataService.updateMasterData(id, input);
        assertTrue(masterData.isPresent());
        assertTrue(masterDataRepository.containsEntity(masterData.get()));
    }

    @Test
    void listMasterDataTest() {
        // var results = masterDataService.listMasterData(PageRequest.of(0, 10));
        // assertNotNull(results);
    }

    @Test
    void deleteMasterDataTest() {
        Long id = null;
        // assertTrue(masterDataRepository.containsKey(id));
        masterDataService.deleteMasterData(id);
        // assertFalse(masterDataRepository.containsKey(id));
    }

    @Test
    void listMasterDataOfTypeTest() { // TODO: implement this test
    }
}
