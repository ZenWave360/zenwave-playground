package io.zenwave360.examples.kotlin.repository.jpa

import io.zenwave360.examples.kotlin.config.DockerComposeInitializer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@DockerComposeInitializer.EnableDockerCompose
@org.springframework.transaction.annotation.Transactional
abstract class BaseRepositoryIntegrationTest