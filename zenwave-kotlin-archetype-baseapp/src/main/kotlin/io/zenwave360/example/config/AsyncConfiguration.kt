package io.zenwave360.example.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.Future

@Configuration
@EnableAsync
@EnableScheduling
@Profile("!testdev & !testprod")
class AsyncConfiguration(private val taskExecutionProperties: TaskExecutionProperties) : AsyncConfigurer {

    @Bean(name = ["taskExecutor"])
    override fun getAsyncExecutor(): Executor {
        log.debug("Creating Async Task Executor")
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = taskExecutionProperties.pool.coreSize
        executor.maxPoolSize = taskExecutionProperties.pool.maxSize
        executor.queueCapacity = taskExecutionProperties.pool.queueCapacity
        executor.setThreadNamePrefix(taskExecutionProperties.threadNamePrefix)
        return ExceptionHandlingAsyncTaskExecutor(executor)
    }

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler = SimpleAsyncUncaughtExceptionHandler()

    private class ExceptionHandlingAsyncTaskExecutor(private val executor: AsyncTaskExecutor) :
        AsyncTaskExecutor, InitializingBean, DisposableBean {

        private val log: Logger = LoggerFactory.getLogger(ExceptionHandlingAsyncTaskExecutor::class.java)

        override fun execute(task: Runnable) {
            executor.execute(createWrappedRunnable(task))
        }

        override fun submit(task: Runnable): Future<*> = executor.submit(createWrappedRunnable(task))

        override fun <T : Any> submit(task: Callable<T>): Future<T> = executor.submit(createCallable(task))

        private fun <T : Any> createCallable(task: Callable<T>): Callable<T> = Callable {
            try {
                task.call()
            } catch (e: Exception) {
                handle(e)
                throw e
            }
        }

        private fun createWrappedRunnable(task: Runnable): Runnable = Runnable {
            try {
                task.run()
            } catch (e: Exception) {
                handle(e)
            }
        }

        private fun handle(e: Exception) {
            log.error(EXCEPTION_MESSAGE, e)
        }

        override fun destroy() {
            (executor as? DisposableBean)?.destroy()
        }

        override fun afterPropertiesSet() {
            (executor as? InitializingBean)?.afterPropertiesSet()
        }

        companion object {
            const val EXCEPTION_MESSAGE = "Caught async exception"
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(AsyncConfiguration::class.java)
    }
}
