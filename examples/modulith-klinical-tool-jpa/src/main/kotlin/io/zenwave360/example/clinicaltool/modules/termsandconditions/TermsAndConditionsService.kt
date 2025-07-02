package io.zenwave360.example.clinicaltool.modules.termsandconditions

import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*
import java.math.*
import java.time.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

/**
 * Inbound Service Port for managing [AcceptedTermsAndConditions, TermsAndConditions].
 */
@org.springframework.modulith.NamedInterface("TermsAndConditionsService")
interface TermsAndConditionsService {


         /**
      * 
      *
      */

     fun listTermsAndConditions(): List<TermsAndConditions>


         /**
      * 
      *
      */

     fun getTermsAndConditions(id: Long): Optional<TermsAndConditions>


         /**
      * 
      *
      */

     fun createTermsAndConditions(input: TermsAndConditions): TermsAndConditions


         /**
      * 
      *
      */

     fun updateTermsAndConditions(id: Long, input: TermsAndConditions): Optional<TermsAndConditions>


         /**
      * 
      *
      */

     fun getCurrentTermsAndConditions(lang: String): Optional<TermsAndConditions>


         /**
      * 
      *
      */

     fun acceptTermsAndConditions(input: AcceptedTermsAndConditionsInput): Unit



}
