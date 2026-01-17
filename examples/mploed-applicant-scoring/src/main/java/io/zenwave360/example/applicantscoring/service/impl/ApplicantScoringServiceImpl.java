package io.zenwave360.example.applicantscoring.service.impl;

import io.zenwave360.example.applicantscoring.domain.*;
import io.zenwave360.example.applicantscoring.events.*;
import io.zenwave360.example.applicantscoring.repository.jpa.*;
// import io.zenwave360.example.applicantscoring.events.dtos.*;
import io.zenwave360.example.applicantscoring.service.*;
import io.zenwave360.example.applicantscoring.service.dtos.*;
import io.zenwave360.example.applicantscoring.service.impl.mappers.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing [ApplicantScoring].
 */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class ApplicantScoringServiceImpl implements ApplicantScoringService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ApplicantScoringServiceMapper applicantScoringServiceMapper = ApplicantScoringServiceMapper.INSTANCE;

    private final ApplicantScoringRepository applicantScoringRepository;

    private final EventsMapper eventsMapper = EventsMapper.INSTANCE;
    private final ApplicantScoringEventsProducer eventsProducer;

    public Optional<ApplicantScoring> getApplicationScoring(Long id) {
        log.debug("Request getApplicationScoring: {}", id);

        return applicantScoringRepository.findById(id);
    }

    @Transactional
    public ApplicantScoring createApplicantScoring(ApplicationNumberInput input) {
        log.debug("[CRUD] Request to save ApplicantScoring: {}", input);
        var applicantScoring = applicantScoringServiceMapper.update(new ApplicantScoring(), input);
        applicantScoring = applicantScoringRepository.save(applicantScoring);
        // TODO: may need to reload the entity to fetch relationships 'mapped by id'
        // emit events
        var applicantScoringEvent = eventsMapper.asApplicantScoringEvent(applicantScoring);
        eventsProducer.onApplicantScoringEvent(applicantScoringEvent);
        return applicantScoring;
    }

    public Optional<ApplicantScoring> updateCity(Long id, CityInput input) {
        log.debug("Request updateCity: {} {}", id, input);

        var applicantScoring = applicantScoringRepository
                .findById(id)
                .map(existingApplicantScoring -> {
                    return applicantScoringServiceMapper.update(existingApplicantScoring, input);
                })
                .map(applicantScoringRepository::save);
        if (applicantScoring.isPresent()) {
            // emit events
            var applicantScoringEvent = eventsMapper.asApplicantScoringEvent(applicantScoring.get());
            eventsProducer.onApplicantScoringEvent(applicantScoringEvent);
        }
        return applicantScoring;
    }

    public Optional<ApplicantScoring> updateBalanceAtBank(Long id, BalanceAtBankInput input) {
        log.debug("Request updateBalanceAtBank: {} {}", id, input);

        var applicantScoring = applicantScoringRepository
                .findById(id)
                .map(existingApplicantScoring -> {
                    return applicantScoringServiceMapper.update(existingApplicantScoring, input);
                })
                .map(applicantScoringRepository::save);
        if (applicantScoring.isPresent()) {
            // emit events
            var applicantScoringEvent = eventsMapper.asApplicantScoringEvent(applicantScoring.get());
            eventsProducer.onApplicantScoringEvent(applicantScoringEvent);
        }
        return applicantScoring;
    }

    @Transactional
    public Optional<ApplicantScoring> updateApplicantScoring(Long id, ApplicantScoringInput input) {
        log.debug("Request updateApplicantScoring: {} {}", id, input);

        var applicantScoring = applicantScoringRepository
                .findById(id)
                .map(existingApplicantScoring -> {
                    return applicantScoringServiceMapper.update(existingApplicantScoring, input);
                })
                .map(applicantScoringRepository::save);
        if (applicantScoring.isPresent()) {
            // emit events
            var applicantScoringEvent = eventsMapper.asApplicantScoringEvent(applicantScoring.get());
            eventsProducer.onApplicantScoringEvent(applicantScoringEvent);
        }
        return applicantScoring;
    }
}
