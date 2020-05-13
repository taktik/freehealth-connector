package org.taktik.freehealth.middleware.web

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExecuterTimeInterceptor : HandlerInterceptorAdapter() {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse?, handler: Any?): Boolean {
        request.setAttribute("startTime", System.currentTimeMillis())
        return true
    }
}
