package io.zenwave360.example.clinicaltool.modules.users

import io.zenwave360.example.clinicaltool.modules.users.domain.*
import io.zenwave360.example.clinicaltool.modules.users.*
import io.zenwave360.example.clinicaltool.modules.users.dtos.*
import io.zenwave360.example.clinicaltool.modules.users.*
import io.zenwave360.example.clinicaltool.modules.users.dtos.*
import io.zenwave360.example.clinicaltool.modules.users.mappers.*

import java.net.URI
import java.net.URISyntaxException
import java.math.*
import java.time.*
import java.util.*
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import org.mapstruct.factory.Mappers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.web.context.request.NativeWebRequest


/**
 * REST controller for UserApi.
 */
@RestController
@RequestMapping("/api")
open class UserApiController(
    private val userService: UserService
) : UserApi {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private lateinit var request: NativeWebRequest

    private val mapper = UserDTOsMapper.INSTANCE

    fun getRequest(): Optional<NativeWebRequest> {
        return Optional.ofNullable(request)
    }



    override fun findByUsername(username: String): ResponseEntity<UserDTO> {
        log.debug("REST request to findByUsername: {}", username)
        val user =  userService.findByUsername(username)
        return if (user.isPresent) {
            val responseDTO = mapper.asUserDTO(user.get())
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun updateUser(username: String, reqBody: UserDTO): ResponseEntity<UserDTO> {
        log.debug("REST request to updateUser: {}, {}", username, reqBody)
        val input = mapper.asUser(reqBody)
        val user =  userService.updateUser(username, input)
        return if (user.isPresent) {
            val responseDTO = mapper.asUserDTO(user.get())
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun deleteUser(username: String): ResponseEntity<Unit> {
        log.debug("REST request to deleteUser: {}", username)
        userService.deleteUser(username)
        return ResponseEntity.status(204).build()
    }

    override fun createUser(reqBody: UserDTO): ResponseEntity<UserDTO> {
        log.debug("REST request to createUser: {}", reqBody)
        val input = mapper.asUser(reqBody)
        val user =  userService.createUser(input)
        val responseDTO = mapper.asUserDTO(user)
        return ResponseEntity.status(201).body(responseDTO)
    }

    override fun lockAccount(username: String): ResponseEntity<UserDTO> {
        log.debug("REST request to lockAccount: {}", username)
        val user =  userService.lockAccount(username)
        return if (user.isPresent) {
            val responseDTO = mapper.asUserDTO(user.get())
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun unLockAccount(username: String): ResponseEntity<UserDTO> {
        log.debug("REST request to unLockAccount: {}", username)
        val user =  userService.unLockAccount(username)
        return if (user.isPresent) {
            val responseDTO = mapper.asUserDTO(user.get())
            ResponseEntity.status(200).body(responseDTO)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    override fun searchUsers(page: Int, limit: Int, sort: List<String>?, reqBody: SearchCriteriaDTO): ResponseEntity<UserPaginatedDTO> {
        log.debug("REST request to searchUsers: {}, {}, {}, {}", page, limit, sort, reqBody)
        val input = mapper.asSearchCriteria(reqBody)
        val userPage =  userService.searchUsers(input, pageOf(page, limit, sort))
        val responseDTO = mapper.asUserPaginatedDTO(userPage)
        return ResponseEntity.status(200).body(responseDTO)
    }

    override fun listUsers(page: Int, limit: Int, sort: List<String>?): ResponseEntity<UserPaginatedDTO> {
        log.debug("REST request to listUsers: {}, {}, {}", page, limit, sort)
        val userPage =  userService.listUsers(pageOf(page, limit, sort))
        val responseDTO = mapper.asUserPaginatedDTO(userPage)
        return ResponseEntity.status(200).body(responseDTO)
    }


    protected fun pageOf(page: Int?, limit: Int?, sort: List<String>?): Pageable {
        val sortOrder = sort?.let {
            Sort.by(it.map { sortParam ->
                val parts = sortParam.split(":")
                val property = parts[0]
                val direction = if (parts.size > 1) Sort.Direction.fromString(parts[1]) else Sort.Direction.ASC
                Sort.Order(direction, property)
            })
        } ?: Sort.unsorted()
        return PageRequest.of(page ?: 0, limit ?: 10, sortOrder)
    }
}
