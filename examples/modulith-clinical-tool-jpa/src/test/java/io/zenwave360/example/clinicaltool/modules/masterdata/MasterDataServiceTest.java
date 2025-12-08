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
import java.util.List;

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
        MasterData input = new MasterData();
        input.setType(MasterDataType.GENDER);
        input.setKey("MALE");
        input.setValue("Male");

        MasterDataTranslation translation = new MasterDataTranslation();
        translation.setLang("en");
        translation.setText("Male");
        input.setTranslations(List.of(translation));

        var masterData = masterDataService.createMasterData(input);
        assertNotNull(masterData.getId());
        assertTrue(masterDataRepository.containsEntity(masterData));
    }

    @Test
    void getMasterDataTest() {
        Long id = 1L;
        var masterData = masterDataService.getMasterData(id);
        assertTrue(masterData.isPresent());
    }

    @Test
    void updateMasterDataTest() {
        Long id = 1L;
        MasterData input = new MasterData();
        input.setType(MasterDataType.COUNTRY);
        input.setKey("US");
        input.setValue("United States");

        MasterDataTranslation translation = new MasterDataTranslation();
        translation.setLang("en");
        translation.setText("United States");
        input.setTranslations(List.of(translation));

        var masterData = masterDataService.updateMasterData(id, input);
        assertTrue(masterData.isPresent());
        assertTrue(masterDataRepository.containsEntity(masterData.get()));
    }

    @Test
    void listMasterDataTest() {
    }

    @Test
    void deleteMasterDataTest() {
        Long id = 1L;
        masterDataService.deleteMasterData(id);
    }

    @Test
    void listMasterDataOfTypeTest() {
    }
}
