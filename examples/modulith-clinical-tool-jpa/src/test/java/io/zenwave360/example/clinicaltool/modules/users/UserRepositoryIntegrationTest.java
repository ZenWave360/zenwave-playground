package io.zenwave360.example.clinicaltool.modules.users;

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest;
import io.zenwave360.example.clinicaltool.modules.users.domain.*;
import io.zenwave360.example.clinicaltool.modules.users.UserRepository;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.time.*;
import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;

class UserRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    void findAllTest() {
        var results = userRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }


    @Test
    void findByIdTest() {
        var id = 1L;
        var user = userRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getVersion());
        Assertions.assertNotNull(user.getCreatedBy());
        Assertions.assertNotNull(user.getCreatedDate());
    }

    @Test
    void saveTest() {
        User user = new User();
        user.setName("");
        user.setUsername("");
        user.setEmail("");
        user.setPassword("");
        user.setRoles(List.of(""));
        user.setEnabled(false);
        user.setCredentialsNonExpired(false);
        user.setAccountNonExpired(false);
        user.setAccountNonLocked(false);
        user.setAdditionalProperties(new java.util.HashMap());



        // Persist aggregate root
        var created = userRepository.save(user);

        // reloading to get relationships persisted by id
        entityManager.flush();
        entityManager.refresh(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getVersion());
        Assertions.assertNotNull(created.getCreatedBy());
        Assertions.assertNotNull(created.getCreatedDate());


    }

    @Test
    void updateTest() {
        var id = 1L;
        var user = userRepository.findById(id).orElseThrow();
        user.setName("");
        user.setUsername("");
        user.setEmail("");
        user.setPassword("");
        user.setRoles(List.of(""));
        user.setEnabled(false);
        user.setCredentialsNonExpired(false);
        user.setAccountNonExpired(false);
        user.setAccountNonLocked(false);
        user.setAdditionalProperties(new java.util.HashMap());

        user = userRepository.save(user);
        Assertions.assertEquals("", user.getName());
        Assertions.assertEquals("", user.getUsername());
        Assertions.assertEquals("", user.getEmail());
        Assertions.assertEquals("", user.getPassword());
        Assertions.assertEquals(List.of(""), user.getRoles());
        Assertions.assertEquals(false, user.getEnabled());
        Assertions.assertEquals(false, user.getCredentialsNonExpired());
        Assertions.assertEquals(false, user.getAccountNonExpired());
        Assertions.assertEquals(false, user.getAccountNonLocked());
        Assertions.assertEquals(new java.util.HashMap(), user.getAdditionalProperties());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        userRepository.deleteById(id);
        var notFound = userRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
