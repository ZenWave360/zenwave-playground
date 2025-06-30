package io.zenwave360.example.clinicaltool.modules.users;

import io.zenwave360.example.clinicaltool.modules.users.*;
import io.zenwave360.example.clinicaltool.modules.users.dtos.*;
import io.zenwave360.example.clinicaltool.modules.users.config.ServicesInMemoryConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.*;
import java.time.*;
import java.util.*;

/**
 * Test controller for UserApiController.
 */
public class UserApiControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    UserApiController controller = new UserApiController( context.userService() );

	@BeforeEach
	void setUp() {
		context.reloadTestData();
	}


    @Test
    public void findByUsernameTest() {
        String username = null;
        var response = controller.findByUsername(username);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void updateUserTest() {
        String username = null;
UserDTO reqBody = null;
        var response = controller.updateUser(username, reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void deleteUserTest() {
        String username = null;
        var response = controller.deleteUser(username);
        Assertions.assertEquals(204, response.getStatusCode().value());
    }

    @Test
    public void createUserTest() {
        UserDTO reqBody = null;
        var response = controller.createUser(reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void lockAccountTest() {
        String username = null;
        var response = controller.lockAccount(username);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void unLockAccountTest() {
        String username = null;
        var response = controller.unLockAccount(username);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void searchUsersTest() {
        Integer page = null;
Integer limit = null;
List<String> sort = null;
SearchCriteriaDTO reqBody = null;
        var response = controller.searchUsers(page, limit, sort, reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void listUsersTest() {
        Integer page = null;
Integer limit = null;
List<String> sort = null;
        var response = controller.listUsers(page, limit, sort);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }


}
