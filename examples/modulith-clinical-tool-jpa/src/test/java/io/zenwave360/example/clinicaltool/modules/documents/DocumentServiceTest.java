package io.zenwave360.example.clinicaltool.modules.documents;

import static org.mockito.Mockito.*;

import io.zenwave360.example.clinicaltool.modules.documents.config.*;
import io.zenwave360.example.clinicaltool.modules.documents.domain.*;
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*;
import io.zenwave360.example.clinicaltool.modules.documents.inmemory.*;
import io.zenwave360.example.clinicaltool.modules.documents.mappers.*;
import java.time.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acceptance Test for DocumentService.
 */
class DocumentServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    DocumentServiceImpl documentService = context.documentService();

    DocumentInfoRepositoryInMemory documentInfoRepository = context.documentInfoRepository();

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void listDocumentInfoTest() {
    }

    @Test
    void downloadDocumentTest() {
    }

    @Test
    void uploadDocumentTest() {
    }

    @Test
    void deleteDocumentInfoTest() {
        Long id = 1L;
        documentService.deleteDocumentInfo(id);
    }
}
