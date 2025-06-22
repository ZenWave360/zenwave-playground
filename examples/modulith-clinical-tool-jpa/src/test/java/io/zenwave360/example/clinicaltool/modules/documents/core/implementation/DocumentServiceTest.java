package io.zenwave360.example.clinicaltool.modules.documents.core.implementation;

import io.zenwave360.example.clinicaltool.modules.documents.config.*;
import io.zenwave360.example.clinicaltool.modules.documents.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.documents.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.documents.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.modules.documents.core.implementation.mappers.*;
import io.zenwave360.example.clinicaltool.modules.documents.core.outbound.jpa.*;
import io.zenwave360.example.clinicaltool.modules.documents.infrastructure.jpa.inmemory.*;

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
