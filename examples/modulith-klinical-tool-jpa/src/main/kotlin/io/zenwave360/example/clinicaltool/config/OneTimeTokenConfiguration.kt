package io.zenwave360.example.clinicaltool.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ott.OneTimeToken
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler

@Configuration
open class OneTimeTokenConfiguration {
    @Bean
    open fun oneTimeTokenGenerationSuccessHandler(): OneTimeTokenGenerationSuccessHandler {
        val redirectHandler: RedirectOneTimeTokenGenerationSuccessHandler =
            RedirectOneTimeTokenGenerationSuccessHandler("/login/ott")
        return OneTimeTokenGenerationSuccessHandler { request: HttpServletRequest, response: HttpServletResponse, oneTimeToken: OneTimeToken ->
            // TODO: Implement this method
            println(
                ("One-time token generated: " + oneTimeToken.username + " "
                        + oneTimeToken.tokenValue + " " + oneTimeToken.expiresAt)
            )
            redirectHandler.handle(request, response, oneTimeToken)
        }
    }
}
