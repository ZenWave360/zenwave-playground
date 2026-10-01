package io.zenwave360.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.SecurityFilterChain
import java.util.Optional

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
class SecurityConfiguration {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors(Customizer.withDefaults())
            .headers { headers -> headers.frameOptions { it.disable() } }
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/swagger-ui/**").permitAll()
                    .requestMatchers("/v3/api-docs/swagger-config").permitAll()
                    .requestMatchers("/apis/**").permitAll()
                    .requestMatchers("/.well-known/**").permitAll()
                    .anyRequest().authenticated()
            }
            // .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
        return http.build()
    }

    @Bean
    fun springSecurityAuditorAware(): AuditorAware<String> = AuditorAware { Optional.of(currentUserLogin() ?: "system") }

    /**
     * Get the login of the current user.
     */
    private fun currentUserLogin(): String? = extractPrincipal(SecurityContextHolder.getContext().authentication)

    private fun extractPrincipal(authentication: Authentication?): String? =
        when (val principal = authentication?.principal) {
            is UserDetails -> principal.username
            is String -> principal
            else -> null
        }
}
