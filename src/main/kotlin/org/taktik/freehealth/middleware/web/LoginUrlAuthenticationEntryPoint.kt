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

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginUrlAuthenticationEntryPoint(loginFormUrl: String,
                                       internal var prefixedLoginUrls: Map<String, String>) : org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint(loginFormUrl) {

    override fun buildRedirectUrlToLoginPage(request: HttpServletRequest,
                                             response: HttpServletResponse,
                                             authException: AuthenticationException): String {

        var urlToLoginPage = super.buildRedirectUrlToLoginPage(request, response, authException)

        for ((key, value) in this.prefixedLoginUrls) {
            if (request.requestURI.startsWith(key)) {
                urlToLoginPage += value
                break
            }
        }

        return urlToLoginPage
    }

}
