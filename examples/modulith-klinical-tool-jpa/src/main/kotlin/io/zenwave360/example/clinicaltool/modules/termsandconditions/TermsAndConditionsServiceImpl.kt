package io.zenwave360.example.clinicaltool.modules.termsandconditions

import io.zenwave360.example.clinicaltool.modules.termsandconditions.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.mappers.*
import java.math.*
import java.time.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/** Service Implementation for managing [AcceptedTermsAndConditions, TermsAndConditions]. */
@Service
@Transactional(readOnly = true)
open class TermsAndConditionsServiceImpl(
    private val acceptedTermsAndConditionsRepository: AcceptedTermsAndConditionsRepository,
    private val termsAndConditionsRepository: TermsAndConditionsRepository,
) : TermsAndConditionsService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val termsAndConditionsServiceMapper: TermsAndConditionsServiceMapper =
        TermsAndConditionsServiceMapper.INSTANCE

    override fun listTermsAndConditions(): List<TermsAndConditions> {
        log.debug("Request listTermsAndConditions")

        val termsAndConditions = termsAndConditionsRepository.findAll()
        return termsAndConditions
    }

    override fun getTermsAndConditions(id: Long): TermsAndConditions? {
        log.debug("[CRUD] Request to get TermsAndConditions : {}", id)
        val termsAndConditions = termsAndConditionsRepository.findByIdOrNull(id)
        return termsAndConditions
    }

    @Transactional
    override fun createTermsAndConditions(input: TermsAndConditions): TermsAndConditions {
        log.debug("[CRUD] Request to save TermsAndConditions: {}", input)
        var termsAndConditions = termsAndConditionsServiceMapper.update(TermsAndConditions(), input)
        termsAndConditions = termsAndConditionsRepository.save(termsAndConditions)
        // TODO: may need to reload the entity to fetch relationships 'mapped by id'
        return termsAndConditions
    }

    @Transactional
    override fun updateTermsAndConditions(id: Long, input: TermsAndConditions): TermsAndConditions? {
        log.debug("Request updateTermsAndConditions: {} {}", id, input)

        return termsAndConditionsRepository
            .findByIdOrNull(id)
            ?.let { existingTermsAndConditions ->
                termsAndConditionsServiceMapper.update(existingTermsAndConditions, input)
            }
            ?.let { termsAndConditionsRepository.save(it) }
    }

    override fun getCurrentTermsAndConditions(lang: String): TermsAndConditions? {
        log.debug("Request getCurrentTermsAndConditions: {}", lang)

        val termsAndConditions = termsAndConditionsServiceMapper.update(TermsAndConditions(), lang)
        // TODO: implement this method
        return termsAndConditions
    }

    override fun acceptTermsAndConditions(input: AcceptedTermsAndConditionsInput): Unit {
        log.debug("Request acceptTermsAndConditions: {}", input)

        val termsAndConditions = TermsAndConditions()
        // TODO: implement this method

    }
}
