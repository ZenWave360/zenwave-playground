package io.zenwave360.example.clinicaltool.modules.masterdata.config

import io.zenwave360.example.clinicaltool.modules.masterdata.*
import io.zenwave360.example.clinicaltool.modules.masterdata.domain.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/** Services InMemory Config. It can be used standalone or with @SpringBootTest. */
@Configuration
@Profile("in-memory")
open class ServicesInMemoryConfig : RepositoriesInMemoryConfig() {

    protected val masterDataService = MasterDataServiceImpl(masterDataRepository())

    @Bean
    open fun <T : MasterDataService> masterDataService(): T {
        return masterDataService as T
    }

    companion object {
        var _masterData: List<MasterData>? = null
    }

    fun reloadTestData() {

        val testDataLoader = TestDataLoader(listOf(MasterData::class.java, MasterDataTranslation::class.java))
        val masterData = _masterData ?: testDataLoader.loadCollectionTestDataAsObjects(MasterData::class.java)
        masterDataRepository().deleteAll()
        masterDataRepository().saveAll(masterData)
    }
}
