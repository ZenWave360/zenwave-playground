package io.zenwave360.example.clinicaltool.modules.users;

import io.zenwave360.example.clinicaltool.modules.users.config.*;
import io.zenwave360.example.clinicaltool.modules.users.domain.*;
import io.zenwave360.example.clinicaltool.modules.users.*;
import io.zenwave360.example.clinicaltool.modules.users.dtos.*;
import io.zenwave360.example.clinicaltool.modules.users.mappers.*;
import io.zenwave360.example.clinicaltool.modules.users.*;
import io.zenwave360.example.clinicaltool.modules.users.inmemory.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.Map;
import java.util.Optional;
import java.time.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Acceptance Test for UserService.
 */
class UserServiceTest  {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    UserServiceImpl userService = context.userService();

    UserRepositoryInMemory userRepository = context.userRepository();

	@BeforeEach
	void setUp() {
		context.reloadTestData();
	}



    @Test
    void findByUsernameTest() {// TODO: implement this test
}

    @Test
    void createUserTest() {
        User input = null; // TODO
        // TODO fill input data
        // input.setName("");
        // input.setUsername("");
        // input.setEmail("");
        // input.setPassword("");
        // input.setRoles(List.of(""));
        // input.setEnabled(false);
        // input.setCredentialsNonExpired(false);
        // input.setAccountNonExpired(false);
        // input.setAccountNonLocked(false);
        // input.setAdditionalProperties(new java.util.HashMap());
        var user = userService.createUser(input);
        assertNotNull(user.getId());
        assertTrue(userRepository.containsEntity(user));
}

    @Test
    void updateUserTest() {
        var username = "";
        User input = null; // TODO
        // TODO fill input data
        // input.setName("");
        // input.setUsername("");
        // input.setEmail("");
        // input.setPassword("");
        // input.setRoles(List.of(""));
        // input.setEnabled(false);
        // input.setCredentialsNonExpired(false);
        // input.setAccountNonExpired(false);
        // input.setAccountNonLocked(false);
        // input.setAdditionalProperties(new java.util.HashMap());
        // assertTrue(userRepository.containsKey(id));
        var user = userService.updateUser(username, input);
        assertTrue(user.isPresent());
        assertTrue(userRepository.containsEntity(user.get()));
}

    @Test
    void lockAccountTest() {// TODO: implement this test
}

    @Test
    void unLockAccountTest() {// TODO: implement this test
}

    @Test
    void deleteUserTest() {
        var username = "";
        // assertTrue(userRepository.containsKey(id));
        userService.deleteUser(username);
        // assertFalse(userRepository.containsKey(id));
}

    @Test
    void searchUsersTest() {// TODO: implement this test
}

    @Test
    void listUsersTest() {
        // var results = userService.listUsers(PageRequest.of(0, 10));
        // assertNotNull(results);
}

}
