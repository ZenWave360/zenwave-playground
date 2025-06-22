package io.zenwave360.example.clinicaltool.modules.masterdata.config;


import io.zenwave360.example.clinicaltool.modules.masterdata.core.domain.*;


import io.zenwave360.example.clinicaltool.modules.masterdata.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.core.implementation.*;


    

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.ArrayList;

/**
 * Services InMemory Config. It can be used standalone or with @SpringBootTest.
 */
@Configuration
@Profile("in-memory")
public class ServicesInMemoryConfig extends RepositoriesInMemoryConfig {



    protected final MasterDataServiceImpl masterDataService = new MasterDataServiceImpl(masterDataRepository()
        );
    @Bean
    public <T extends MasterDataService> T masterDataService() {
        return (T) masterDataService;
    }


	static List<MasterData> _masterData;
	public void reloadTestData() {
        
		var testDataLoader = new TestDataLoader(List.of(MasterData.class, MasterDataTranslation.class));
		var masterData = _masterData != null? _masterData : testDataLoader.loadCollectionTestDataAsObjects(MasterData.class);
		masterDataRepository().deleteAll();
		masterDataRepository().saveAll(masterData);
	}
}
