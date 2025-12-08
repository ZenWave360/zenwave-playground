package io.zenwave360.example.clinicaltool.modules.users

import io.zenwave360.example.clinicaltool.modules.users.*
import io.zenwave360.example.clinicaltool.modules.users.config.*
import io.zenwave360.example.clinicaltool.modules.users.domain.*
import io.zenwave360.example.clinicaltool.modules.users.dtos.*
import io.zenwave360.example.clinicaltool.modules.users.inmemory.*
import io.zenwave360.example.clinicaltool.modules.users.mappers.*
import java.time.*
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/** Acceptance Test for UserService. */
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
    fun findByUsernameTest() { // TODO: implement this test
    }

    @Test
    fun createUserTest() {
        val input: User = User()
        input.name = "John Doe"
        input.username = "johndoe"
        input.email = "john.doe@example.com"
        input.password = "password123"
        input.roles = mutableListOf("USER", "ADMIN")
        input.enabled = true
        input.credentialsNonExpired = true
        input.accountNonExpired = true
        input.accountNonLocked = true

        input.additionalProperties = mutableMapOf(
            "theme" to "dark" as Object,
            "notifications" to true as Object
        )

        val user = userService.createUser(input)
        assertNotNull(user.id)
        assertTrue(userRepository.containsEntity(user))
    }

    @Test
    fun updateUserTest() {
        val username = "admin"
        val input: User = User()
        input.name = "Admin User"
        input.username = "admin"
        input.email = "user@email.com"
        input.password = "\$2a\$10\$I86fM/OvHg8ounuxGq2oBufCecu3NFO4vT1SWIvd4hnM72lrzdXBG"
        input.roles = mutableListOf("USER", "ADMIN")
        input.enabled = true
        input.credentialsNonExpired = true
        input.accountNonExpired = true
        input.accountNonLocked = true

        input.additionalProperties = mutableMapOf(
            "theme" to "dark" as Object,
            "notifications" to true as Object
        )

        val user = userService.updateUser(username, input)
        assertNotNull(user)
        assertTrue(userRepository.containsEntity(user!!))
    }

    @Test
    fun enableAccountTest() { // TODO: implement this test
    }

    @Test
    fun disableAccountTest() { // TODO: implement this test
    }

    @Test
    fun deleteUserTest() {
        val username = "johndoe"
        userService.deleteUser(username)
    }

    @Test
    fun searchUsersTest() { // TODO: implement this test
    }

    @Test
    fun listUsersTest() {
        // val results = userService.listUsers(PageRequest.of(0, 10))
        // assertNotNull(results)
    }
}
