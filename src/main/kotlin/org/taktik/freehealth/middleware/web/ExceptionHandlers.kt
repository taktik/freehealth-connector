package org.taktik.freehealth.middleware.web

import org.springframework.http.MediaType
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver
import org.taktik.connector.technical.exception.TechnicalConnectorException

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException

/**
 * Improve this using ResponseStatusException when upgrading to Spring 5+
 */
class ExceptionHandlers : AbstractHandlerExceptionResolver() {
    override fun doResolveException(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, handler: Any?, exception: Exception): ModelAndView? {
        try {
            if (exception is TechnicalConnectorException) {
                return handleTechnicalConnectorException(exception, httpServletResponse)
            }
        } catch (handlerException: Exception) {
            logger.error("Handling of [" + exception.javaClass.name + "]	resulted in Exception", handlerException)
        }

        return null
    }

    @Throws(IOException::class)
    private fun handleTechnicalConnectorException(exception: TechnicalConnectorException, httpServletResponse: HttpServletResponse): ModelAndView {
        httpServletResponse.contentType = MediaType.TEXT_PLAIN_VALUE
        httpServletResponse.sendError(exception.category.httpStatus.value(), exception.message)

        return ModelAndView()
    }
}
