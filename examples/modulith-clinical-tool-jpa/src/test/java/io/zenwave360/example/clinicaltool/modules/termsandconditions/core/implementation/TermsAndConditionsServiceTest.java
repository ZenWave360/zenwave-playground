package io.zenwave360.example.clinicaltool.modules.termsandconditions.core.implementation;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.config.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.implementation.mappers.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.outbound.jpa.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.infrastructure.jpa.inmemory.*;

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
 * Acceptance Test for TermsAndConditionsService.
 */
class TermsAndConditionsServiceTest  {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    TermsAndConditionsServiceImpl termsAndConditionsService = context.termsAndConditionsService();

    AcceptedTermsAndConditionsRepositoryInMemory acceptedTermsAndConditionsRepository = context.acceptedTermsAndConditionsRepository();

    TermsAndConditionsRepositoryInMemory termsAndConditionsRepository = context.termsAndConditionsRepository();

	@BeforeEach
	void setUp() {
		context.reloadTestData();
	}



    @Test
    void listTermsAndConditionsTest() {// TODO: implement this test
        // var results = termsAndConditionsService.listTermsAndConditions(PageRequest.of(0, 10));
        // assertNotNull(results);
}

    @Test
    void getTermsAndConditionsTest() {// TODO: implement this test
        Long id = null;
        var termsAndConditions = termsAndConditionsService.getTermsAndConditions(id);
        assertTrue(termsAndConditions.isPresent());
}

    @Test
    void createTermsAndConditionsTest() {// TODO: implement this test
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
    void updateTermsAndConditionsTest() {// TODO: implement this test
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
    void getCurrentTermsAndConditionsTest() {// TODO: implement this test// TODO: implement this test
}

    @Test
    void acceptTermsAndConditionsTest() {// TODO: implement this test// TODO: implement this test
}

}
