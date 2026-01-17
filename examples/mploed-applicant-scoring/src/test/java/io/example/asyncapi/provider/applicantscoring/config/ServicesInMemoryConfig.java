package io.example.asyncapi.provider.applicantscoring.config;

import io.zenwave360.example.applicantscoring.domain.*;
import io.zenwave360.example.applicantscoring.events.*;
import io.zenwave360.example.applicantscoring.service.*;
import io.zenwave360.example.applicantscoring.service.impl.*;
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

    protected final EventsProducerInMemoryContext eventsProducerInMemoryContext = new EventsProducerInMemoryContext();

    protected final ApplicantScoringServiceImpl applicantScoringService = new ApplicantScoringServiceImpl(
            applicantScoringRepository(), eventsProducerInMemoryContext.applicantScoringEventsProducer());

    @Bean
    public <T extends ApplicantScoringService> T applicantScoringService() {
        return (T) applicantScoringService;
    }

    static List<ApplicantScoring> _applicantScorings;

    public void reloadTestData() {

        var testDataLoader = new TestDataLoader(
                List.of(ApplicantScoring.class, ApplicationNumber.class, City.class, BalanceAtBank.class));
        var applicantScorings = _applicantScorings != null
                ? _applicantScorings
                : testDataLoader.loadCollectionTestDataAsObjects(ApplicantScoring.class);
        applicantScoringRepository().deleteAll();
        applicantScoringRepository().saveAll(applicantScorings);
    }

    public EventsProducerInMemoryContext getEventsProducerInMemoryContext() {
        return eventsProducerInMemoryContext;
    }
}
