package io.zenwave360.example.clinicaltool.modules.termsandconditions.config;

import io.zenwave360.example.clinicaltool.common.TestDataLoader;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*;
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

    protected final TermsAndConditionsServiceImpl termsAndConditionsService =
            new TermsAndConditionsServiceImpl(acceptedTermsAndConditionsRepository(), termsAndConditionsRepository());

    @Bean
    public <T extends TermsAndConditionsService> T termsAndConditionsService() {
        return (T) termsAndConditionsService;
    }

    static List<AcceptedTermsAndConditions> _acceptedTermsAndConditions;
    static List<TermsAndConditions> _termsAndConditions;

    public void reloadTestData() {

        var testDataLoader = new TestDataLoader(List.of(AcceptedTermsAndConditions.class, TermsAndConditions.class));
        var acceptedTermsAndConditions = _acceptedTermsAndConditions != null
                ? _acceptedTermsAndConditions
                : testDataLoader.loadCollectionTestDataAsObjects(AcceptedTermsAndConditions.class);
        acceptedTermsAndConditionsRepository().deleteAll();
        acceptedTermsAndConditionsRepository().saveAll(acceptedTermsAndConditions);
        var termsAndConditions = _termsAndConditions != null
                ? _termsAndConditions
                : testDataLoader.loadCollectionTestDataAsObjects(TermsAndConditions.class);
        termsAndConditionsRepository().deleteAll();
        termsAndConditionsRepository().saveAll(termsAndConditions);
    }
}
