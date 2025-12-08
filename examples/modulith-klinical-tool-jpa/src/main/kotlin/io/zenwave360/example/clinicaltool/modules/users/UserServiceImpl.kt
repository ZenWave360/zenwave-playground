package io.zenwave360.example.clinicaltool.modules.users

import io.zenwave360.example.clinicaltool.modules.users.*
import io.zenwave360.example.clinicaltool.modules.users.domain.*
import io.zenwave360.example.clinicaltool.modules.users.dtos.*
import io.zenwave360.example.clinicaltool.modules.users.mappers.*
import java.math.*
import java.time.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/** Service Implementation for managing [User]. */
@Service
@Transactional(readOnly = true)
open class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val userServiceMapper: UserServiceMapper = UserServiceMapper.INSTANCE

    override fun findByUsername(username: String): User? {
        log.debug("Request findByUsername: {}", username)

        return userRepository.findByUsername(username)
    }

    @Transactional
    override fun createUser(input: User): User {
        log.debug("[CRUD] Request to save User: {}", input)
        var user = userServiceMapper.update(User(), input)
        user = userRepository.save(user)
        // TODO: may need to reload the entity to fetch relationships 'mapped by id'
        return user
    }

    @Transactional
    override fun updateUser(username: String, input: User): User? {
        log.debug("Request updateUser: {} {}", username, input)

        return userRepository
            .findByUsername(username)
            ?.let { existingUser -> userServiceMapper.update(existingUser, input) }
            ?.let { userRepository.save(it) }
    }

    override fun enableAccount(username: String): User? {
        log.debug("Request enableAccount: {}", username)

        return userRepository.findByUsername(username)
    }

    override fun disableAccount(username: String): User? {
        log.debug("Request disableAccount: {}", username)

        return userRepository.findByUsername(username)
    }

    @Transactional
    override fun deleteUser(username: String): Unit {
        log.debug("Request deleteUser: {}", username)
        val user = userRepository.findByUsername(username)?.let { userRepository.delete(it) }
    }

    override fun searchUsers(input: SearchCriteria, pageable: Pageable): Page<User> {
        log.debug("Request searchUsers: {} {}", input, pageable)

        val users = userRepository.findAll(pageable)
        return users
    }

    override fun listUsers(pageable: Pageable): Page<User> {
        log.debug("Request listUsers: {}", pageable)

        val users = userRepository.findAll(pageable)
        return users
    }
}
