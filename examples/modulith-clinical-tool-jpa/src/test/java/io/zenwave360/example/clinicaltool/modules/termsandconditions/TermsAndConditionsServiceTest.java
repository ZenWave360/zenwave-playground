package io.zenwave360.example.clinicaltool.modules.termsandconditions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.config.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.inmemory.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.mappers.*;
import java.time.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acceptance Test for TermsAndConditionsService.
 */
class TermsAndConditionsServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    TermsAndConditionsServiceImpl termsAndConditionsService = context.termsAndConditionsService();

    AcceptedTermsAndConditionsRepositoryInMemory acceptedTermsAndConditionsRepository =
            context.acceptedTermsAndConditionsRepository();

    TermsAndConditionsRepositoryInMemory termsAndConditionsRepository = context.termsAndConditionsRepository();

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void listTermsAndConditionsTest() {}

    @Test
    void getTermsAndConditionsTest() {
        Long id = 1L;
        var termsAndConditions = termsAndConditionsService.getTermsAndConditions(id);
        assertTrue(termsAndConditions.isPresent());
    }

    @Test
    void createTermsAndConditionsTest() {
        TermsAndConditions input = new TermsAndConditions();
        input.setContent("These are the terms and conditions for using the application.");
        input.setLang("en");
        input.setContentVersion("1.0.0");
        input.setStartDate(LocalDate.now());
        var termsAndConditions = termsAndConditionsService.createTermsAndConditions(input);
        assertNotNull(termsAndConditions.getId());
        assertTrue(termsAndConditionsRepository.containsEntity(termsAndConditions));
    }

    @Test
    void updateTermsAndConditionsTest() {
        Long id = 1L;
        TermsAndConditions input = new TermsAndConditions();
        input.setContent("Updated terms and conditions for using the application.");
        input.setLang("es");
        input.setContentVersion("2.0.0");
        input.setStartDate(LocalDate.now());
        var termsAndConditions = termsAndConditionsService.updateTermsAndConditions(id, input);
        assertTrue(termsAndConditions.isPresent());
        assertTrue(termsAndConditionsRepository.containsEntity(termsAndConditions.get()));
    }

    @Test
    void getCurrentTermsAndConditionsTest() {}

    @Test
    void acceptTermsAndConditionsTest() {}
}
