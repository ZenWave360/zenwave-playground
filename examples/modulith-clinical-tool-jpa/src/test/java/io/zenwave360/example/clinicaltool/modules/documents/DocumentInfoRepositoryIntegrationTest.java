package io.zenwave360.example.clinicaltool.modules.documents;

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest;
import io.zenwave360.example.clinicaltool.modules.documents.domain.*;
import jakarta.persistence.EntityManager;
import java.time.*;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DocumentInfoRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    DocumentInfoRepository documentInfoRepository;

    @Test
    void findAllTest() {
        var results = documentInfoRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }

    @Test
    void findByIdTest() {
        var id = 1L;
        var documentInfo = documentInfoRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(documentInfo.getId());
        Assertions.assertNotNull(documentInfo.getVersion());
    }

    @Test
    void saveTest() {
        DocumentInfo documentInfo = new DocumentInfo();
        documentInfo.setUuid("");
        documentInfo.setFileName("");
        documentInfo.setDocumentType("");
        documentInfo.setContentType("");
        documentInfo.setTags(List.of(""));

        // OneToOne documentData owner: true
        var documentDataId = 1L;
        var documentData = new DocumentData();
        documentData.setData("test document content".getBytes());
        documentInfo.setDocumentData(documentData);

        // Persist aggregate root
        var created = documentInfoRepository.save(documentInfo);

        // reloading to get relationships persisted by id
        entityManager.flush();
        entityManager.refresh(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getVersion());

        Assertions.assertNotNull(documentInfo.getDocumentData().getId() != null);
    }

    @Test
    void updateTest() {
        var id = 1L;
        var documentInfo = documentInfoRepository.findById(id).orElseThrow();
        documentInfo.setUuid("");
        documentInfo.setFileName("");
        documentInfo.setDocumentType("");
        documentInfo.setContentType("");
        documentInfo.setTags(List.of(""));

        documentInfo = documentInfoRepository.save(documentInfo);
        Assertions.assertEquals("", documentInfo.getUuid());
        Assertions.assertEquals("", documentInfo.getFileName());
        Assertions.assertEquals("", documentInfo.getDocumentType());
        Assertions.assertEquals("", documentInfo.getContentType());
        Assertions.assertEquals(List.of(""), documentInfo.getTags());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        documentInfoRepository.deleteById(id);
        var notFound = documentInfoRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
