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
    void listTermsAndConditionsTest() { // TODO: implement this test
        // var results = termsAndConditionsService.listTermsAndConditions(PageRequest.of(0, 10));
        // assertNotNull(results);
    }

    @Test
    void getTermsAndConditionsTest() { // TODO: implement this test
        Long id = null;
        var termsAndConditions = termsAndConditionsService.getTermsAndConditions(id);
        assertTrue(termsAndConditions.isPresent());
    }

    @Test
    void createTermsAndConditionsTest() { // TODO: implement this test
        TermsAndConditions input = null; // TODO
        // TODO fill input data
        // input.setContent("");
        // input.setLang("");
        // input.setContentVersion("");
        // input.setStartDate(LocalDate.now());
        var termsAndConditions = termsAndConditionsService.createTermsAndConditions(input);
        assertNotNull(termsAndConditions.getId());
        assertTrue(termsAndConditionsRepository.containsEntity(termsAndConditions));
    }

    @Test
    void updateTermsAndConditionsTest() { // TODO: implement this test
        Long id = null;
        TermsAndConditions input = null; // TODO
        // TODO fill input data
        // input.setContent("");
        // input.setLang("");
        // input.setContentVersion("");
        // input.setStartDate(LocalDate.now());
        // assertTrue(termsAndConditionsRepository.containsKey(id));
        var termsAndConditions = termsAndConditionsService.updateTermsAndConditions(id, input);
        assertTrue(termsAndConditions.isPresent());
        assertTrue(termsAndConditionsRepository.containsEntity(termsAndConditions.get()));
    }

    @Test
    void getCurrentTermsAndConditionsTest() { // TODO: implement this test// TODO: implement this test
    }

    @Test
    void acceptTermsAndConditionsTest() { // TODO: implement this test// TODO: implement this test
    }
}
