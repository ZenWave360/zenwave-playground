package io.zenwave360.examples.kotlin.config

import org.springframework.security.authentication.ott.OneTimeToken
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler

@org.springframework.context.annotation.Configuration
open class OneTimeTokenConfiguration {
    @org.springframework.context.annotation.Bean
    open fun oneTimeTokenGenerationSuccessHandler(): OneTimeTokenGenerationSuccessHandler {
        val redirectHandler: RedirectOneTimeTokenGenerationSuccessHandler =
            RedirectOneTimeTokenGenerationSuccessHandler("/login/ott")
        return OneTimeTokenGenerationSuccessHandler { request: jakarta.servlet.http.HttpServletRequest, response: jakarta.servlet.http.HttpServletResponse, oneTimeToken: OneTimeToken ->
            // TODO: Implement this method
            kotlin.io.println(
                ("One-time token generated: " + oneTimeToken.username + " "
                        + oneTimeToken.tokenValue + " " + oneTimeToken.expiresAt)
            )
            redirectHandler.handle(request, response, oneTimeToken)
        }
    }
}
