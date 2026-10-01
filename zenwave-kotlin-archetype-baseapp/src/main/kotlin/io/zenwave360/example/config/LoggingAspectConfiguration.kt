package io.zenwave360.example.config

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles

@Configuration
@EnableAspectJAutoProxy
class LoggingAspectConfiguration {

    @Bean
    @Profile(Constants.SPRING_PROFILE_LOCAL, Constants.SPRING_PROFILE_TEST, Constants.SPRING_PROFILE_DEVELOPMENT)
    fun loggingAspect(env: Environment): LoggingAspect = LoggingAspect(env)

    @Aspect
    class LoggingAspect(private val env: Environment) {

        /**
         * Pointcut that matches all repositories, services and Web REST endpoints.
         */
        @Pointcut(
            "within(@org.springframework.stereotype.Repository *)" +
                " || within(@org.springframework.stereotype.Service *)" +
                " || within(@org.springframework.web.bind.annotation.RestController *)",
        )
        fun springBeanPointcut() {
            // Method is empty as this is just a Pointcut, the implementations are in the advices.
        }

        /**
         * Pointcut that matches all Spring beans in the application's main packages.
         */
        @Pointcut(
            "within(io.zenwave360.example.core.implementation..*)" +
                " || within(io.zenwave360.example.infrastructure..*)" +
                " || within(io.zenwave360.example.adapters..*)",
        )
        fun applicationPackagePointcut() {
            // Method is empty as this is just a Pointcut, the implementations are in the advices.
        }

        /**
         * Retrieves the [Logger] associated to the given [JoinPoint].
         */
        private fun logger(joinPoint: JoinPoint): Logger = LoggerFactory.getLogger(joinPoint.signature.declaringTypeName)

        /**
         * Advice that logs methods throwing exceptions.
         */
        @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
        fun logAfterThrowing(joinPoint: JoinPoint, e: Throwable) {
            if (env.acceptsProfiles(Profiles.of(Constants.SPRING_PROFILE_DEVELOPMENT))) {
                logger(joinPoint).error(
                    "Exception in {}() with cause = '{}' and exception = '{}'",
                    joinPoint.signature.name,
                    e.cause ?: "NULL",
                    e.message,
                    e,
                )
            } else {
                logger(joinPoint).error(
                    "Exception in {}() with cause = {}",
                    joinPoint.signature.name,
                    e.cause?.toString() ?: "NULL",
                )
            }
        }

        /**
         * Advice that logs when a method is entered and exited.
         */
        @Around("applicationPackagePointcut() && springBeanPointcut()")
        fun logAround(joinPoint: ProceedingJoinPoint): Any? {
            val log = logger(joinPoint)
            if (log.isDebugEnabled) {
                log.debug("Enter: {}() with argument[s] = {}", joinPoint.signature.name, joinPoint.args.contentToString())
            }
            try {
                val result = joinPoint.proceed()
                if (log.isDebugEnabled) {
                    log.debug("Exit: {}() with result = {}", joinPoint.signature.name, result)
                }
                return result
            } catch (e: IllegalArgumentException) {
                log.error("Illegal argument: {} in {}()", joinPoint.args.contentToString(), joinPoint.signature.name)
                throw e
            }
        }
    }
}
