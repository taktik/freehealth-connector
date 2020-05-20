package org.taktik.freehealth.middleware.web

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import java.net.InetAddress


@ControllerAdvice
class GeneralControllerAdvice() : ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter?,
        converterType: Class<out HttpMessageConverter<*>?>?): Boolean {
        return true
    }

    override fun beforeBodyWrite(body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse): Any? {
        (request as? ServletServerHttpRequest)?.servletRequest?.getAttribute("startTime")?.let {
            (it as? Long)?.let { startTime ->
                val timeElapsed = System.currentTimeMillis() - startTime
                response.headers.add("X-FHC-Timing", timeElapsed.toString())
                response.headers.add("X-FHC-Node", InetAddress.getLocalHost().hostName)
            }
        }
        return body
    }
}
