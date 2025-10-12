package io.example.asyncapi.provider.clinicaltool.modules.users

import io.zenwave360.example.clinicaltool.modules.users.domain.*
import java.math.*
import java.time.*
import java.util.*

import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.Repository

/**
 * Spring Data JPA repository for the User entity.
 */
@Suppress("unused")
@Repository
interface UserRepository : JpaRepository<User, Long> {fun findByUsername(username: String): java.util.Optional<User>
}
