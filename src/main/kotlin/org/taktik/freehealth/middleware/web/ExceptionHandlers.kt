package org.taktik.freehealth.middleware.web

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.freehealth.middleware.dto.ExceptionDto
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import org.taktik.freehealth.middleware.exception.MissingTokenException
import java.io.EOFException
import java.util.concurrent.TimeoutException
import javax.servlet.http.HttpServletRequest
import javax.xml.ws.soap.SOAPFaultException

/**
 * Defines custom exception handlers.
 */
@ControllerAdvice
class ExceptionHandlers {
    @ExceptionHandler(TimeoutException::class)
    fun handleTimeoutException(request: HttpServletRequest, exception: TimeoutException) =
        ExceptionDto(HttpStatus.REQUEST_TIMEOUT, exception, request.servletPath).toResponseEntity()

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

    @ExceptionHandler(EOFException::class)
    fun handleEOFException(request: HttpServletRequest, exception: EOFException) = null //Nothing more to do... Connection closed

    @ExceptionHandler(Exception::class)
    fun handleException(request: HttpServletRequest, exception: Exception) =
            ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, exception, request.servletPath).toResponseEntity()
}
