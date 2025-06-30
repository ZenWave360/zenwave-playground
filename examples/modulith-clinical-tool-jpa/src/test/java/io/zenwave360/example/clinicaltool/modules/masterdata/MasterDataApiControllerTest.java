package io.zenwave360.example.clinicaltool.modules.masterdata;

import io.zenwave360.example.clinicaltool.modules.masterdata.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*;
import io.zenwave360.example.clinicaltool.modules.masterdata.config.ServicesInMemoryConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.*;
import java.time.*;
import java.util.*;

/**
 * Test controller for MasterDataApiController.
 */
public class MasterDataApiControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    MasterDataApiController controller = new MasterDataApiController( context.masterDataService() );

	@BeforeEach
	void setUp() {
		context.reloadTestData();
	}


    @Test
    public void createMasterDataTest() {
        MasterDataDTO reqBody = null;
        var response = controller.createMasterData(reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void listMasterDataTest() {
        Integer page = null;
Integer limit = null;
List<String> sort = null;
        var response = controller.listMasterData(page, limit, sort);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void getMasterDataTest() {
        Long id = null;
        var response = controller.getMasterData(id);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void updateMasterDataTest() {
        Long id = null;
MasterDataDTO reqBody = null;
        var response = controller.updateMasterData(id, reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void deleteMasterDataTest() {
        Long id = null;
        var response = controller.deleteMasterData(id);
        Assertions.assertEquals(204, response.getStatusCode().value());
    }

    @Test
    public void listMasterDataOfTypeTest() {
        String type = null;
String lang = null;
        var response = controller.listMasterDataOfType(type, lang);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }


}
