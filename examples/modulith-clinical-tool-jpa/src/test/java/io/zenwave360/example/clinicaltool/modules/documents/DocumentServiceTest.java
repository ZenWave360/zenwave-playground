package io.zenwave360.example.clinicaltool.modules.documents;

import io.zenwave360.example.clinicaltool.modules.documents.config.*;
import io.zenwave360.example.clinicaltool.modules.documents.domain.*;
import io.zenwave360.example.clinicaltool.modules.documents.*;
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*;
import io.zenwave360.example.clinicaltool.modules.documents.mappers.*;
import io.zenwave360.example.clinicaltool.modules.documents.*;
import io.zenwave360.example.clinicaltool.modules.documents.inmemory.*;

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
 * Acceptance Test for DocumentService.
 */
class DocumentServiceTest  {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    DocumentServiceImpl documentService = context.documentService();

    DocumentInfoRepositoryInMemory documentInfoRepository = context.documentInfoRepository();

	@BeforeEach
	void setUp() {
		context.reloadTestData();
	}



    @Test
    void listDocumentInfoTest() {// TODO: implement this test
}

    @Test
    void downloadDocumentTest() {// TODO: implement this test
}

    @Test
    void uploadDocumentTest() {// TODO: implement this test
}

    @Test
    void deleteDocumentInfoTest() {
        Long id = null;
        // assertTrue(documentInfoRepository.containsKey(id));
        documentService.deleteDocumentInfo(id);
        // assertFalse(documentInfoRepository.containsKey(id));
}

}
