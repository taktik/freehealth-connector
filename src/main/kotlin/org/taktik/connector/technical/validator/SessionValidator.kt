package org.taktik.connector.technical.validator

import org.taktik.connector.technical.exception.SessionManagementException
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface SessionValidator {
    @Throws(SessionManagementException::class)
    fun validateSession(): Boolean

    @Throws(SessionManagementException::class)
    fun validateToken(var1: SAMLToken?): Boolean
}
