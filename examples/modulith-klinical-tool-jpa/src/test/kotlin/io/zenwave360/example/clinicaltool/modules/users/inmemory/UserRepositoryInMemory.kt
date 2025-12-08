package io.zenwave360.example.clinicaltool.modules.users.inmemory

import io.zenwave360.example.clinicaltool.modules.users.UserRepository
import io.zenwave360.example.clinicaltool.modules.users.domain.*
import java.math.*
import java.time.*
import java.util.*

class UserRepositoryInMemory : InMemoryJpaRepository<User, Long>(), UserRepository {
    override fun findByUsername(username: String): User? {
        return getEntities().values.firstOrNull { e -> isSameValue(username, readField(e, "username")) }
    }
}
