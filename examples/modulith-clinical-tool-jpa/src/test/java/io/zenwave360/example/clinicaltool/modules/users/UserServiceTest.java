package io.zenwave360.example.clinicaltool.modules.users;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import io.zenwave360.example.clinicaltool.modules.users.config.*;
import io.zenwave360.example.clinicaltool.modules.users.domain.*;
import io.zenwave360.example.clinicaltool.modules.users.dtos.*;
import io.zenwave360.example.clinicaltool.modules.users.inmemory.*;
import io.zenwave360.example.clinicaltool.modules.users.mappers.*;
import java.time.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acceptance Test for UserService.
 */
class UserServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    UserServiceImpl userService = context.userService();

    UserRepositoryInMemory userRepository = context.userRepository();

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void findByUsernameTest() { // TODO: implement this test
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
    void enableAccountTest() { // TODO: implement this test
    }

    @Test
    void diableAccountTest() { // TODO: implement this test
    }

    @Test
    void deleteUserTest() {
        var username = "";
        // assertTrue(userRepository.containsKey(id));
        userService.deleteUser(username);
        // assertFalse(userRepository.containsKey(id));
    }

    @Test
    void searchUsersTest() { // TODO: implement this test
    }

    @Test
    void listUsersTest() {
        // var results = userService.listUsers(PageRequest.of(0, 10));
        // assertNotNull(results);
    }
}
