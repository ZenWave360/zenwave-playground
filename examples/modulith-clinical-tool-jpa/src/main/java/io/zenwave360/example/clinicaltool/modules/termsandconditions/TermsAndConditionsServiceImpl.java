package io.zenwave360.example.clinicaltool.modules.termsandconditions;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.mappers.*;
import java.time.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing [AcceptedTermsAndConditions, TermsAndConditions].
 */
@Service
@Transactional(readOnly = true)
@lombok.AllArgsConstructor
public class TermsAndConditionsServiceImpl implements TermsAndConditionsService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final TermsAndConditionsServiceMapper termsAndConditionsServiceMapper =
            TermsAndConditionsServiceMapper.INSTANCE;

    private final AcceptedTermsAndConditionsRepository acceptedTermsAndConditionsRepository;

    private final TermsAndConditionsRepository termsAndConditionsRepository;

    public List<TermsAndConditions> listTermsAndConditions() {
        log.debug("Request listTermsAndConditions");

        var termsAndConditions = termsAndConditionsRepository.findAll();
        return termsAndConditions;
    }

    public Optional<TermsAndConditions> getTermsAndConditions(Long id) {
        log.debug("[CRUD] Request to get TermsAndConditions : {}", id);
        var termsAndConditions = termsAndConditionsRepository.findById(id);
        return termsAndConditions;
    }

    @Transactional
    public TermsAndConditions createTermsAndConditions(TermsAndConditions input) {
        log.debug("[CRUD] Request to save TermsAndConditions: {}", input);
        var termsAndConditions = termsAndConditionsServiceMapper.update(new TermsAndConditions(), input);
        termsAndConditions = termsAndConditionsRepository.save(termsAndConditions);
        return termsAndConditions;
    }

    @Transactional
    public Optional<TermsAndConditions> updateTermsAndConditions(Long id, TermsAndConditions input) {
        log.debug("Request updateTermsAndConditions: {} {}", id, input);

        var termsAndConditions = termsAndConditionsRepository
                .findById(id)
                .map(existingTermsAndConditions -> {
                    return termsAndConditionsServiceMapper.update(existingTermsAndConditions, input);
                })
                .map(termsAndConditionsRepository::save);
        return termsAndConditions;
    }

    public Optional<TermsAndConditions> getCurrentTermsAndConditions(String lang) {
        log.debug("Request getCurrentTermsAndConditions: {}", lang);
        var yesterday = LocalDate.now().minusDays(1);
        var termsAndConditions =
                termsAndConditionsRepository.findOneByLangAndStartDateBeforeOrderByStartDateAsc(lang, yesterday);
        return Optional.ofNullable(termsAndConditions);
    }

    @Transactional
    public void acceptTermsAndConditions(AcceptedTermsAndConditionsInput input) {
        log.debug("Request acceptTermsAndConditions: {}", input);
        var acceptedTermsAndConditions = acceptedTermsAndConditionsRepository
                .findByUserId(input.getUserId())
                .or(() -> Optional.of(new AcceptedTermsAndConditions().setUserId(input.getUserId())))
                .map(existingAcceptedTermsAndConditions -> {
                    return existingAcceptedTermsAndConditions
                            .setTermsAndConditionsId(input.getTermsAndConditionsId())
                            .setAcceptedDate(Instant.now());
                })
                .map(acceptedTermsAndConditionsRepository::save);
        //        return acceptedTermsAndConditions;
    }
}
