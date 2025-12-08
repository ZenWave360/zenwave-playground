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
import java.util.List;

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
    void findByUsernameTest() {
    }

    @Test
    void createUserTest() {
        User input = new User();
        input.setName("John Doe");
        input.setUsername("johndoe");
        input.setEmail("john.doe@example.com");
        input.setPassword("password123");
        input.setRoles(List.of("USER", "ADMIN"));
        input.setEnabled(true);
        input.setCredentialsNonExpired(true);
        input.setAccountNonExpired(true);
        input.setAccountNonLocked(true);

        java.util.Map<String, Object> additionalProperties = new java.util.HashMap<>();
        additionalProperties.put("theme", "dark");
        additionalProperties.put("notifications", true);
        input.setAdditionalProperties(additionalProperties);

        var user = userService.createUser(input);
        assertNotNull(user.getId());
        assertTrue(userRepository.containsEntity(user));
    }

    @Test
    void updateUserTest() {
        var username = "admin";
        User input = new User();
        input.setName("Admin User");
        input.setUsername("admin");
        input.setEmail("user@email.com");
        input.setPassword("$2a$10$I86fM/OvHg8ounuxGq2oBufCecu3NFO4vT1SWIvd4hnM72lrzdXBG");
        input.setRoles(List.of("USER", "ADMIN"));
        input.setEnabled(true);
        input.setCredentialsNonExpired(true);
        input.setAccountNonExpired(true);
        input.setAccountNonLocked(true);

        java.util.Map<String, Object> additionalProperties = new java.util.HashMap<>();
        additionalProperties.put("theme", "dark");
        additionalProperties.put("notifications", true);
        input.setAdditionalProperties(additionalProperties);

        var user = userService.updateUser(username, input);
        assertTrue(user.isPresent());
        assertTrue(userRepository.containsEntity(user.get()));
    }

    @Test
    void enableAccountTest() {
    }

    @Test
    void diableAccountTest() {
    }

    @Test
    void deleteUserTest() {
        var username = "johndoe";
        userService.deleteUser(username);
    }

    @Test
    void searchUsersTest() {
    }

    @Test
    void listUsersTest() {
    }
}
