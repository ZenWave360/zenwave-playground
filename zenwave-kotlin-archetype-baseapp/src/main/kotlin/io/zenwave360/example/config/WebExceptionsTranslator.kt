package io.zenwave360.example.config

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class WebExceptionsTranslator : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest,
    ): ResponseEntity<Any>? {
        val body = ex.updateAndGetBody(messageSource, LocaleContextHolder.getLocale())
        fillBindingErrors(body, ex)
        return createResponseEntity(body, headers, status, request)
    }

    private fun fillBindingErrors(body: ProblemDetail, errors: MethodArgumentNotValidException) {
        errors.bindingResult.globalErrors.forEach { error ->
            body.setProperty(error.objectName, error.toString())
        }
        errors.bindingResult.fieldErrors.forEach { error ->
            body.setProperty(error.objectName + "." + error.field, error.defaultMessage)
        }
    }

    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        statusCode: HttpStatusCode,
        request: WebRequest,
    ): ResponseEntity<Any>? {
        if (statusCode.is5xxServerError) {
            logger.error(String.format("Internal Error: %s %s", request.contextPath, statusCode), ex)
        }
        return super.handleExceptionInternal(ex, body, headers, statusCode, request)
    }
}
