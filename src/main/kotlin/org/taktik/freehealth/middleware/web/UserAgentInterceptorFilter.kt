package org.taktik.freehealth.middleware.web

import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest


@Component
@Order(2)
class UserAgentInterceptorFilter : Filter {
    override fun init(filterConfig: FilterConfig?) {}

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain
    ) {
        val req = request as HttpServletRequest
        (req.getHeader("X-User-Agent") ?: req.getHeader(HttpHeaders.USER_AGENT))?.let {
            userAgent.set(it)
        }
        (req.getHeader("X-From"))?.let {
            xFrom.set(it)
        }

        chain.doFilter(request, response)
    } // other methods

    override fun destroy() {}

    companion object {
        private val userAgent: ThreadLocal<String> = ThreadLocal()
        private val xFrom: ThreadLocal<String> = ThreadLocal()

        fun getUserAgent(): String? {
            return userAgent.get()
        }

        fun getXfrom(): String? {
            return xFrom.get()
        }

    }
}
