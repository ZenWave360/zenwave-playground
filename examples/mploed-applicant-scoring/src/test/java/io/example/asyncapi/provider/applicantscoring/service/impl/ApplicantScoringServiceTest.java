package io.example.asyncapi.provider.applicantscoring.service.impl;

import io.zenwave360.example.applicantscoring.config.*;
import io.zenwave360.example.applicantscoring.domain.*;
import io.zenwave360.example.applicantscoring.service.*;
import io.zenwave360.example.applicantscoring.service.dtos.*;
import io.zenwave360.example.applicantscoring.service.impl.mappers.*;
import io.zenwave360.example.applicantscoring.repository.jpa.*;
import io.zenwave360.example.applicantscoring.repository.jpa.inmemory.*;

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
 * Acceptance Test for ApplicantScoringService.
 */
class ApplicantScoringServiceTest  {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    ApplicantScoringServiceImpl applicantScoringService = context.applicantScoringService();

    ApplicantScoringRepositoryInMemory applicantScoringRepository = context.applicantScoringRepository();

	@BeforeEach
	void setUp() {
		context.reloadTestData();
	}



    @Test
    void getApplicationScoringTest() {// TODO: implement this test
}

    @Test
    void createApplicantScoringTest() {
        ApplicationNumberInput input = null; // TODO
        // TODO fill input data
        // input.setApplicationNumber(new ApplicationNumber());
        // input.setCity(new City());
        // input.setBalanceAtBank(new BalanceAtBank());
        var applicantScoring = applicantScoringService.createApplicantScoring(input);
        assertNotNull(applicantScoring.getId());
        assertTrue(applicantScoringRepository.containsEntity(applicantScoring));
}

    @Test
    void updateCityTest() {// TODO: implement this test
}

    @Test
    void updateBalanceAtBankTest() {// TODO: implement this test
}

    @Test
    void updateApplicantScoringTest() {
        Long id = null;
        ApplicantScoringInput input = null; // TODO
        // TODO fill input data
        // input.setApplicationNumber(new ApplicationNumber());
        // input.setCity(new City());
        // input.setBalanceAtBank(new BalanceAtBank());
        // assertTrue(applicantScoringRepository.containsKey(id));
        var applicantScoring = applicantScoringService.updateApplicantScoring(id, input);
        assertTrue(applicantScoring.isPresent());
        assertTrue(applicantScoringRepository.containsEntity(applicantScoring.get()));
}

}
