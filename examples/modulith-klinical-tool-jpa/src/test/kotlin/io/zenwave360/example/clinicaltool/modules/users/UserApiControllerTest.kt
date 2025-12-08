package io.zenwave360.example.clinicaltool.modules.users

import io.zenwave360.example.clinicaltool.modules.users.*
import io.zenwave360.example.clinicaltool.modules.users.dtos.*
import io.zenwave360.example.clinicaltool.modules.users.config.ServicesInMemoryConfig

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.math.*
import java.time.*
import java.util.*

/**
 * Test controller for UserApiController.
 */
class UserApiControllerTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val context = ServicesInMemoryConfig()

    private val controller = UserApiController( context.userService() )

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun findByUsernameTest() {
        val username: String = ""
        val response = controller.findByUsername(username)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun updateUserTest() {
        val username: String = ""
val reqBody: UserDTO = UserDTO(username = "aaa", email = "aaa", roles = mutableListOf())
        val response = controller.updateUser(username, reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun deleteUserTest() {
        val username: String = ""
        val response = controller.deleteUser(username)
        Assertions.assertEquals(204, response.statusCode.value())
    }

    @Test
    fun createUserTest() {
        val reqBody: UserDTO = UserDTO(username = "aaa", email = "aaa", roles = mutableListOf())
        val response = controller.createUser(reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun listUsersTest() {
        val page: Int = 0
val limit: Int = 0
val sort: List<String> = mutableListOf()
        val response = controller.listUsers(page, limit, sort)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun enableAccountTest() {
        val username: String = ""
        val response = controller.enableAccount(username)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun disableAccountTest() {
        val username: String = ""
        val response = controller.disableAccount(username)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun searchUsersTest() {
        val page: Int = 0
val limit: Int = 0
val sort: List<String> = mutableListOf()
val reqBody: SearchCriteriaDTO = SearchCriteriaDTO()
        val response = controller.searchUsers(page, limit, sort, reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
    }


}
