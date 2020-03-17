package org.taktik.freehealth.middleware.web

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import org.taktik.freehealth.middleware.exception.MissingTokenException
import javax.servlet.http.HttpServletResponse
import javax.xml.ws.soap.SOAPFaultException

/**
 * Improve this using ResponseStatusException when upgrading to Spring 5+
 */
@ControllerAdvice
class ExceptionHandlers {
    @ExceptionHandler(TechnicalConnectorException::class)
    fun handleTechnicalConnectorException(response: HttpServletResponse, exception: TechnicalConnectorException) {
        response.sendError(exception.category.httpStatus.value(), exception.message ?: "unknown reason")
    }

    @ExceptionHandler(MissingKeystoreException::class, MissingTokenException::class)
    fun handleUnauthorizedException(response: HttpServletResponse, exception: Exception) {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.message ?: "unknown reason")
    }

    @ExceptionHandler(SOAPFaultException::class)
    fun handleSoapFaultException(response: HttpServletResponse, exception: SOAPFaultException) {
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.message ?: "unknown reason")
    }
}
