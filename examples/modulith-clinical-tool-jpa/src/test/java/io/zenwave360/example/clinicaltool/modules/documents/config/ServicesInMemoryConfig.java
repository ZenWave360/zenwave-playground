package io.zenwave360.example.clinicaltool.modules.documents.config;

import io.zenwave360.example.clinicaltool.modules.documents.*;
import io.zenwave360.example.clinicaltool.modules.documents.domain.*;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Services InMemory Config. It can be used standalone or with @SpringBootTest.
 */
@Configuration
@Profile("in-memory")
public class ServicesInMemoryConfig extends RepositoriesInMemoryConfig {

    protected final DocumentServiceImpl documentService = new DocumentServiceImpl(documentInfoRepository());

    @Bean
    public <T extends DocumentService> T documentService() {
        return (T) documentService;
    }

    static List<DocumentInfo> _documentInfos;

    public void reloadTestData() {

        var testDataLoader = new TestDataLoader(List.of(DocumentInfo.class, DocumentData.class));
        var documentInfos = _documentInfos != null
                ? _documentInfos
                : testDataLoader.loadCollectionTestDataAsObjects(DocumentInfo.class);
        documentInfoRepository().deleteAll();
        documentInfoRepository().saveAll(documentInfos);
    }
}
