package io.zenwave360.example.clinicaltool.modules.masterdata;

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest;
import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*;
import jakarta.persistence.EntityManager;
import java.time.*;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MasterDataRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    MasterDataRepository masterDataRepository;

    @Test
    void findAllTest() {
        var results = masterDataRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }

    @Test
    void findByIdTest() {
        var id = 1L;
        var masterData = masterDataRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(masterData.getId());
        Assertions.assertNotNull(masterData.getVersion());
    }

    @Test
    void saveTest() {
        MasterData masterData = new MasterData();
        masterData.setType(MasterDataType.values()[0]);
        masterData.setKey("");
        masterData.setValue("");
        masterData.setTranslations(List.of(new MasterDataTranslation()));

        // Persist aggregate root
        var created = masterDataRepository.save(masterData);

        // reloading to get relationships persisted by id
        entityManager.flush();
        entityManager.refresh(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getVersion());
    }

    @Test
    void updateTest() {
        var id = 1L;
        var masterData = masterDataRepository.findById(id).orElseThrow();
        masterData.setType(MasterDataType.values()[0]);
        masterData.setKey("");
        masterData.setValue("");
        masterData.setTranslations(List.of(new MasterDataTranslation()));

        masterData = masterDataRepository.save(masterData);
        Assertions.assertEquals(MasterDataType.values()[0], masterData.getType());
        Assertions.assertEquals("", masterData.getKey());
        Assertions.assertEquals("", masterData.getValue());
        //        Assertions.assertEquals(List.of(new MasterDataTranslation()), masterData.getTranslations());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        masterDataRepository.deleteById(id);
        var notFound = masterDataRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
