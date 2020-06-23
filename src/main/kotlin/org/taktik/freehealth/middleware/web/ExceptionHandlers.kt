package org.taktik.freehealth.middleware.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.freehealth.middleware.dto.ExceptionDto
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import org.taktik.freehealth.middleware.exception.MissingTokenException
import javax.servlet.http.HttpServletRequest
import javax.xml.ws.soap.SOAPFaultException

/**
 * Defines custom exception handlers.
 */
@ControllerAdvice
class ExceptionHandlers {
    @ExceptionHandler(TechnicalConnectorException::class)
    fun handleTechnicalConnectorException(request: HttpServletRequest, exception: TechnicalConnectorException) =
            ExceptionDto(exception.category.httpStatus, exception, request.servletPath).toResponseEntity()

    @ExceptionHandler(MissingKeystoreException::class, MissingTokenException::class)
    fun handleUnauthorizedException(request: HttpServletRequest, exception: Exception) =
            ExceptionDto(HttpStatus.UNAUTHORIZED, exception, request.servletPath).toResponseEntity()

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(request: HttpServletRequest, exception: IllegalArgumentException) =
            ExceptionDto(HttpStatus.BAD_REQUEST, exception, request.servletPath).toResponseEntity()

    @ExceptionHandler(SOAPFaultException::class)
    fun handleSoapFaultException(request: HttpServletRequest, exception: SOAPFaultException) =
            ExceptionDto(HttpStatus.BAD_GATEWAY, exception, request.servletPath).toResponseEntity()

    @ExceptionHandler(Exception::class)
    fun handleException(request: HttpServletRequest, exception: Exception): ResponseEntity<ExceptionDto> {
        val cause = exception.cause

        if (cause != null) {
            when (cause) {
                is TechnicalConnectorException -> handleTechnicalConnectorException(request, cause)
                is MissingKeystoreException -> handleUnauthorizedException(request, cause)
                is MissingTokenException -> handleUnauthorizedException(request, cause)
                is IllegalArgumentException -> handleIllegalArgumentException(request, cause)
                is SOAPFaultException -> handleSoapFaultException(request, cause)
            }
        }

        return ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, exception, request.servletPath).toResponseEntity()
    }
}
