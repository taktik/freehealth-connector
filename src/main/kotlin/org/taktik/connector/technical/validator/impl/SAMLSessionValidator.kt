package org.taktik.connector.technical.validator.impl

import org.taktik.connector.technical.exception.SessionManagementException
import org.taktik.connector.technical.exception.SessionManagementExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.service.sts.utils.SAMLHelper
import org.taktik.connector.technical.session.Session
import org.taktik.connector.technical.validator.SessionValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SAMLSessionValidator : SessionValidator {

    @Throws(SessionManagementException::class)
    override fun validateSession(): Boolean {
        if (!Session.getInstance().hasValidSession()) {
            LOG.error("No valid session found")
            throw SessionManagementException(SessionManagementExceptionValues.ERROR_NOSESSION)
        } else {
            return true
        }
    }

    @Throws(SessionManagementException::class)
    override fun validateToken(samlToken: SAMLToken?): Boolean {
        if (samlToken != null && samlToken.assertion != null) {
            return SAMLHelper.getNotOnOrAfterCondition(samlToken.assertion)!!.isAfterNow
        } else {
            LOG.error("No valid samlToken")
            throw SessionManagementException(SessionManagementExceptionValues.ERROR_NOTOKEN)
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(SAMLSessionValidator::class.java)
    }
}
