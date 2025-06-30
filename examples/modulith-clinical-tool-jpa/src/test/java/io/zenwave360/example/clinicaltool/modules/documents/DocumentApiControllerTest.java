package io.zenwave360.example.clinicaltool.modules.documents;

import io.zenwave360.example.clinicaltool.modules.documents.*;
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*;
import io.zenwave360.example.clinicaltool.modules.documents.config.ServicesInMemoryConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.*;
import java.time.*;
import java.util.*;

/**
 * Test controller for DocumentApiController.
 */
public class DocumentApiControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    DocumentApiController controller = new DocumentApiController( context.documentService() );

	@BeforeEach
	void setUp() {
		context.reloadTestData();
	}


    @Test
    public void listDocumentInfoTest() {
        Long documentIds = null;
        var response = controller.listDocumentInfo(documentIds);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void downloadDocumentTest() {
        Long id = null;
Boolean preview = null;
        var response = controller.downloadDocument(id, preview);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void deleteDocumentInfoTest() {
        Long id = null;
        var response = controller.deleteDocumentInfo(id);
        Assertions.assertEquals(204, response.getStatusCode().value());
    }

    @Test
    public void uploadDocumentTest() {
        org.springframework.web.multipart.MultipartFile file = null;
Long id = null;
Integer version = null;
String uuid = null;
String fileName = null;
String documentType = null;
String contentType = null;
List<String> tags = null;
DocumentDataDTO documentData = null;
        var response = controller.uploadDocument(file, id, version, uuid, fileName, documentType, contentType, tags, documentData);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }


}
