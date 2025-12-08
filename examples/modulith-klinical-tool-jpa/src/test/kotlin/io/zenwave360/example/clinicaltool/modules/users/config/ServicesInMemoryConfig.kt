package io.zenwave360.example.clinicaltool.modules.users.config

import io.zenwave360.example.clinicaltool.modules.users.*
import io.zenwave360.example.clinicaltool.modules.users.domain.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/** Services InMemory Config. It can be used standalone or with @SpringBootTest. */
@Configuration
@Profile("in-memory")
open class ServicesInMemoryConfig : RepositoriesInMemoryConfig() {

    protected val userService = UserServiceImpl(userRepository())

    @Bean
    open fun <T : UserService> userService(): T {
        return userService as T
    }

    companion object {
        var _users: List<User>? = null
    }

    fun reloadTestData() {

        val testDataLoader = TestDataLoader(listOf(User::class.java))
        val users = _users ?: testDataLoader.loadCollectionTestDataAsObjects(User::class.java)
        userRepository().deleteAll()
        userRepository().saveAll(users)
    }
}
