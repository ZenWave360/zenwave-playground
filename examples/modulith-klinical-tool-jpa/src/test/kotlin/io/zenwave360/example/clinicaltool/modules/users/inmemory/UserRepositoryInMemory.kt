package io.zenwave360.example.clinicaltool.modules.users.inmemory

import io.zenwave360.example.clinicaltool.modules.users.domain.*
import io.zenwave360.example.clinicaltool.modules.users.UserRepository
import java.math.*
import java.time.*
import java.util.*

class UserRepositoryInMemory : InMemoryJpaRepository<User, Long>(), UserRepository {
    override fun findByUsername(username: String): java.util.Optional<User> {
        return getEntities().values.stream().filter { e ->
             isSameValue(username, readField(e, "username")) 
        }.findFirst()
    }
}
