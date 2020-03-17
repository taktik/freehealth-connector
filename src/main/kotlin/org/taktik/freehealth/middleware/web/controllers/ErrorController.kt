package org.taktik.freehealth.middleware.web.controllers

import org.apache.commons.lang3.StringUtils
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import springfox.documentation.annotations.ApiIgnore
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Replaces BasicErrorController to return error message as plain text instead of html or json.
 */
@ConditionalOnProperty("server.error.type", havingValue = "plain", matchIfMissing = true)
@ApiIgnore
@Controller
@RequestMapping("\${server.error.path:\${error.path:/error}}")
class ErrorController(
    val errorAttributes: ErrorAttributes,
    val serverProperties: ServerProperties,
    errorViewResolvers: List<ErrorViewResolver>? = null
) : AbstractErrorController(errorAttributes, errorViewResolvers) {
    override fun getErrorPath(): String {
        return serverProperties.error.path
    }

    @RequestMapping(produces = ["${MediaType.TEXT_PLAIN_VALUE};charset=UTF-8"])
    @ResponseBody
    fun errorString(request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<String> {
        val status = getStatus(request)
        val body = getErrorAttributes(request, false)
        val message = body["message"]?.toString() ?: StringUtils.EMPTY

        return ResponseEntity(message, status)
    }
}
