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

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException

class AuthenticationFailureHandler : SimpleUrlAuthenticationFailureHandler() {

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationFailure(httpRequest: HttpServletRequest,
                                         httpResponse: HttpServletResponse,
                                         exception: AuthenticationException) {
        val failureURL = httpRequest.session.getAttribute(FAILURE_URL) as String

        if (failureURL == null) {
            super.onAuthenticationFailure(httpRequest, httpResponse, exception)
        } else {
            saveException(httpRequest, exception)
            logger.debug("Redirecting to $failureURL")
            httpResponse.sendRedirect(failureURL)
        }
    }

    companion object {
        val FAILURE_URL = "failureURL"
    }
}