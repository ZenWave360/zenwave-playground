package io.zenwave360.example.clinicaltool.modules.users

import io.zenwave360.example.clinicaltool.modules.users.config.*
import io.zenwave360.example.clinicaltool.modules.users.domain.*
import io.zenwave360.example.clinicaltool.modules.users.*
import io.zenwave360.example.clinicaltool.modules.users.dtos.*
import io.zenwave360.example.clinicaltool.modules.users.mappers.*
import io.zenwave360.example.clinicaltool.modules.users.*
import io.zenwave360.example.clinicaltool.modules.users.inmemory.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.PageRequest

import java.util.Map
import java.util.Optional
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.mockito.Mockito.*

/**
 * Acceptance Test for UserService.
 */
class UserServiceTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    val context = ServicesInMemoryConfig()
    val userService: UserService = context.userService()

    val userRepository: UserRepositoryInMemory = context.userRepository()


    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun findByUsernameTest() {// TODO: implement this test
}

    @Test
    fun createUserTest() {
        val input: User = User() // TODO
        // TODO fill input data
        // input.name = ""
        // input.username = ""
        // input.email = ""
        // input.password = ""
        // input.roles = List.of("")
        // input.enabled = false
        // input.credentialsNonExpired = false
        // input.accountNonExpired = false
        // input.accountNonLocked = false
        // input.additionalProperties = java.util.HashMap()
        val user = userService.createUser(input)
        assertNotNull(user.id)
        assertTrue(userRepository.containsEntity(user))
}

    @Test
    fun updateUserTest() {
        val username = "";
        val input: User = User() // TODO
        // TODO fill input data
        // input.name = ""
        // input.username = ""
        // input.email = ""
        // input.password = ""
        // input.roles = List.of("")
        // input.enabled = false
        // input.credentialsNonExpired = false
        // input.accountNonExpired = false
        // input.accountNonLocked = false
        // input.additionalProperties = java.util.HashMap()
        // assertTrue(userRepository.containsKey(id))
        val user = userService.updateUser(username, input)
        assertTrue(user.isPresent)
        assertTrue(userRepository.containsEntity(user.get()))
}

    @Test
    fun lockAccountTest() {// TODO: implement this test
}

    @Test
    fun unLockAccountTest() {// TODO: implement this test
}

    @Test
    fun deleteUserTest() {
        val username = "";
        // assertTrue(userRepository.containsKey(id))
        userService.deleteUser(username)
        // assertFalse(userRepository.containsKey(id))
}

    @Test
    fun searchUsersTest() {// TODO: implement this test
}

    @Test
    fun listUsersTest() {
        // val results = userService.listUsers(PageRequest.of(0, 10))
        // assertNotNull(results)
}

}
