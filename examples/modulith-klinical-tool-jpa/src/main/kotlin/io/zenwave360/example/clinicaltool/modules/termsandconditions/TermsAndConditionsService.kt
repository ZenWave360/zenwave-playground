package io.zenwave360.example.clinicaltool.modules.termsandconditions

import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*
import java.math.*
import java.time.*

/** Inbound Service Port for managing [AcceptedTermsAndConditions, TermsAndConditions]. */
@org.springframework.modulith.NamedInterface("TermsAndConditionsService")
interface TermsAndConditionsService {

    /**  */
    fun listTermsAndConditions(): List<TermsAndConditions>

    /**  */
    fun getTermsAndConditions(id: Long): TermsAndConditions?

    /**  */
    fun createTermsAndConditions(input: TermsAndConditions): TermsAndConditions

    /**  */
    fun updateTermsAndConditions(id: Long, input: TermsAndConditions): TermsAndConditions?

    /**  */
    fun getCurrentTermsAndConditions(lang: String): TermsAndConditions?

    /**  */
    fun acceptTermsAndConditions(input: AcceptedTermsAndConditionsInput): Unit
}
