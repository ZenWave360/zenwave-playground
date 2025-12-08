package io.zenwave360.example.clinicaltool.modules.documents;

import io.zenwave360.example.clinicaltool.modules.documents.config.ServicesInMemoryConfig;
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test controller for DocumentApiController.
 */
public class DocumentApiControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    DocumentApiController controller = new DocumentApiController(context.documentService());

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    public void listDocumentInfoTest() {
        List<Long> documentIds = List.of(1L);
        var response = controller.listDocumentInfo(documentIds);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void downloadDocumentTest() {
        Long id = 1L;
        Boolean preview = false;
        var response = controller.downloadDocument(id, preview);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void deleteDocumentInfoTest() {
        Long id = 1L;
        var response = controller.deleteDocumentInfo(id);
        Assertions.assertEquals(204, response.getStatusCode().value());
    }

    @Test
    public void uploadDocumentTest() {
        org.springframework.mock.web.MockMultipartFile file = new org.springframework.mock.web.MockMultipartFile(
            "file",
            "test-document.pdf",
            "application/pdf",
            "test content".getBytes()
        );
        String uuid = "550e8400-e29b-41d4-a716-446655440001";
        List<String> tags = List.of("test", "document");
        var response = controller.uploadDocument(uuid, tags, file);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }
}
