package org.taktik.freehealth.middleware.web

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import org.taktik.freehealth.middleware.exception.MissingTokenException
import javax.servlet.http.HttpServletResponse
import javax.xml.ws.soap.SOAPFaultException

/**
 * Defines custom exception handlers.
 *
 * Uses [HttpServletResponse.sendError] to redirect to the servlet error page which will be handled by [BasicErrorController].
 *
 * **NOTE**: Adding [ResponseStatus] to the methods breaks the expected behavior.
 */
@ControllerAdvice
class ExceptionHandlers {
    companion object {
        private const val DEFAULT_EXCEPTION_MESSAGE = "unknown reason"
    }

    @ExceptionHandler(TechnicalConnectorException::class)
    fun handleTechnicalConnectorException(response: HttpServletResponse, exception: TechnicalConnectorException) {
        response.sendError(exception.category.httpStatus.value(), exception.message ?: DEFAULT_EXCEPTION_MESSAGE)
    }

    @ExceptionHandler(MissingKeystoreException::class, MissingTokenException::class)
    fun handleUnauthorizedException(response: HttpServletResponse, exception: Exception) {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.message ?: DEFAULT_EXCEPTION_MESSAGE)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(response: HttpServletResponse, exception: IllegalArgumentException) {
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.message ?: DEFAULT_EXCEPTION_MESSAGE)
    }

    @ExceptionHandler(SOAPFaultException::class)
    fun handleSoapFaultException(response: HttpServletResponse, exception: SOAPFaultException) {
        response.sendError(HttpStatus.BAD_GATEWAY.value(), exception.message ?: DEFAULT_EXCEPTION_MESSAGE)
    }
}
