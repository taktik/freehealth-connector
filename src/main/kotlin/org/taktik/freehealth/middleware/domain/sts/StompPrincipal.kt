package org.taktik.freehealth.middleware.domain.sts

import java.security.Principal

data class StompPrincipal(private val sessionId: String) : Principal {
    override fun getName() = sessionId
}
