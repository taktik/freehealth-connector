/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.freehealth.middleware.web

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.transaction.annotation.Transactional
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Transactional
class AuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {
    @Throws(ServletException::class, IOException::class)
    override fun onAuthenticationSuccess(httpRequest: HttpServletRequest,
                                         httpResponse: HttpServletResponse,
                                         authentication: Authentication) {
        super.onAuthenticationSuccess(httpRequest, httpResponse, authentication)
    }

    override fun determineTargetUrl(httpRequest: HttpServletRequest, httpResponse: HttpServletResponse?): String {
        /* Get target URL */
        var target = super.determineTargetUrl(httpRequest, httpResponse)

        /* Replace using optional destination */
        val destination = httpRequest.getParameter("destination")
        if (destination != null) {
            target = destination
        }

        /* Add optional queryString */
        val queryString = httpRequest.getParameter("queryString")
        if (queryString != null) {
            target += queryString
        }

        return target
    }
}