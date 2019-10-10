package org.taktik.freehealth.middleware.web

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.freehealth.middleware.exception.MissingKeystoreException
import org.taktik.freehealth.middleware.exception.MissingTokenException

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.util.concurrent.ExecutionException

/**
 * Improve this using ResponseStatusException when upgrading to Spring 5+
 */
class ExceptionHandlers : AbstractHandlerExceptionResolver() {
    override fun doResolveException(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, handler: Any?, exception: Exception): ModelAndView? {
        try {
            val rootException : Exception = if (exception is ExecutionException && exception.cause is java.lang.Exception) exception.cause as Exception else exception

            if (rootException is TechnicalConnectorException) {
                return handleTechnicalConnectorException(rootException, httpServletResponse)
            } else if (rootException is MissingKeystoreException) {
                return handleUnauthorizedException(rootException.message, httpServletResponse)
            } else if (rootException is MissingTokenException) {
                return handleUnauthorizedException(rootException.message, httpServletResponse)
            }
        } catch (handlerException: Exception) {
            logger.error("Handling of [" + exception.javaClass.name + "]	resulted in Exception", handlerException)
        }

        return null
    }

    @Throws(IOException::class)
    private fun handleTechnicalConnectorException(exception: TechnicalConnectorException, httpServletResponse: HttpServletResponse): ModelAndView? {
        httpServletResponse.contentType = MediaType.TEXT_PLAIN_VALUE
        httpServletResponse.sendError(exception.category.httpStatus.value(), exception.message)

        return null
    }

    @Throws(IOException::class)
    private fun handleUnauthorizedException(message: String?, httpServletResponse: HttpServletResponse): ModelAndView? {
        httpServletResponse.contentType = MediaType.TEXT_PLAIN_VALUE
        httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), message ?: "Unknown error")

        return null
    }
}
