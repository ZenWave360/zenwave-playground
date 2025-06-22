package io.zenwave360.example.clinicaltool.modules.masterdata.core.implementation;

import io.zenwave360.example.clinicaltool.modules.masterdata.config.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.core.implementation.mappers.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.core.outbound.jpa.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.infrastructure.jpa.inmemory.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.Map;
import java.util.Optional;
import java.time.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Acceptance Test for MasterDataService.
 */
class MasterDataServiceTest  {

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
    void listMasterDataOfTypeTest() {// TODO: implement this test
}

}
