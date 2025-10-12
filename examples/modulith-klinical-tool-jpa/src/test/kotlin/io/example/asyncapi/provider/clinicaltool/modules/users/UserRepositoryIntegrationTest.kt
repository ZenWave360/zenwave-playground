package io.example.asyncapi.provider.clinicaltool.modules.users

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.users.domain.*
import io.zenwave360.example.clinicaltool.modules.users.UserRepository

import java.util.HashSet
import java.util.HashMap
import java.util.List
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import jakarta.persistence.EntityManager

class UserRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun findAllTest() {
        val results = userRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val user = userRepository.findById(id).orElseThrow()
        Assertions.assertNotNull(user.id)
        Assertions.assertNotNull(user.version)
        Assertions.assertNotNull(user.createdBy)
        Assertions.assertNotNull(user.createdDate)
    }

    @Test
    fun saveTest() {
        val user = User()
        user.name = ""
        user.username = ""
        user.email = ""
        user.password = ""
        user.roles = List.of("")
        user.enabled = false
        user.credentialsNonExpired = false
        user.accountNonExpired = false
        user.accountNonLocked = false
        user.additionalProperties = java.util.HashMap()



        // Persist aggregate root
        val created = userRepository.save(user)

        // reloading to get relationships persisted by id
        entityManager.flush()
        entityManager.refresh(created)
        Assertions.assertNotNull(created.id)
        Assertions.assertNotNull(created.version)
        Assertions.assertNotNull(created.createdBy)
        Assertions.assertNotNull(created.createdDate)


    }

    @Test
    fun updateTest() {
        val id = 1L
        val user = userRepository.findById(id).orElseThrow()
        user.name = ""
        user.username = ""
        user.email = ""
        user.password = ""
        user.roles = List.of("")
        user.enabled = false
        user.credentialsNonExpired = false
        user.accountNonExpired = false
        user.accountNonLocked = false
        user.additionalProperties = java.util.HashMap()

        val updated = userRepository.save(user)
        Assertions.assertEquals("", updated.name)
        Assertions.assertEquals("", updated.username)
        Assertions.assertEquals("", updated.email)
        Assertions.assertEquals("", updated.password)
        Assertions.assertEquals(List.of(""), updated.roles)
        Assertions.assertEquals(false, updated.enabled)
        Assertions.assertEquals(false, updated.credentialsNonExpired)
        Assertions.assertEquals(false, updated.accountNonExpired)
        Assertions.assertEquals(false, updated.accountNonLocked)
//        Assertions.assertEquals(java.util.HashMap(), updated.additionalProperties)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        userRepository.deleteById(id)
        val notFound = userRepository.findById(id)
        Assertions.assertFalse(notFound.isPresent)
    }
}
