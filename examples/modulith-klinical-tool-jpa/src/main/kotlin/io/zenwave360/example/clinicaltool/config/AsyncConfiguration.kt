package io.zenwave360.example.clinicaltool.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
@EnableScheduling
@Profile("!testdev & !testprod")
open class AsyncConfiguration(private val taskExecutionProperties: TaskExecutionProperties) : AsyncConfigurer {
    @Bean(name = ["taskExecutor"])
    override fun getAsyncExecutor(): Executor? {
        log.debug("Creating Async Task Executor")
        val executor = ThreadPoolTaskExecutor()
        executor.setCorePoolSize(taskExecutionProperties.getPool().getCoreSize())
        executor.setMaxPoolSize(taskExecutionProperties.getPool().getMaxSize())
        executor.setQueueCapacity(taskExecutionProperties.getPool().getQueueCapacity())
        executor.setThreadNamePrefix(taskExecutionProperties.getThreadNamePrefix())
        return executor
    }

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler? {
        return SimpleAsyncUncaughtExceptionHandler()
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(AsyncConfiguration::class.java)
    }
}
