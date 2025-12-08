package io.zenwave360.example.clinicaltool.modules.termsandconditions.config

import io.zenwave360.example.clinicaltool.config.TestDataLoader
import io.zenwave360.example.clinicaltool.modules.termsandconditions.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/** Services InMemory Config. It can be used standalone or with @SpringBootTest. */
@Configuration
@Profile("in-memory")
open class ServicesInMemoryConfig : RepositoriesInMemoryConfig() {

    protected val termsAndConditionsService =
        TermsAndConditionsServiceImpl(acceptedTermsAndConditionsRepository(), termsAndConditionsRepository())

    @Bean
    open fun <T : TermsAndConditionsService> termsAndConditionsService(): T {
        return termsAndConditionsService as T
    }

    companion object {
        var _acceptedTermsAndConditions: List<AcceptedTermsAndConditions>? = null
        var _termsAndConditions: List<TermsAndConditions>? = null
    }

    fun reloadTestData() {

        val testDataLoader =
            TestDataLoader(listOf(AcceptedTermsAndConditions::class.java, TermsAndConditions::class.java))
        val acceptedTermsAndConditions =
            _acceptedTermsAndConditions
                ?: testDataLoader.loadCollectionTestDataAsObjects(AcceptedTermsAndConditions::class.java)
        acceptedTermsAndConditionsRepository().deleteAll()
        acceptedTermsAndConditionsRepository().saveAll(acceptedTermsAndConditions)
        val termsAndConditions =
            _termsAndConditions ?: testDataLoader.loadCollectionTestDataAsObjects(TermsAndConditions::class.java)
        termsAndConditionsRepository().deleteAll()
        termsAndConditionsRepository().saveAll(termsAndConditions)
    }
}
