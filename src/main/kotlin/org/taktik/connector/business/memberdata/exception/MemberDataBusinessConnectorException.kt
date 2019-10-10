package org.taktik.connector.business.memberdata.exception

import org.taktik.connector.technical.exception.ConnectorException
import java.text.MessageFormat

class MemberDataBusinessConnectorException(errorCodeValue: MemberDataBusinessConnectorExceptionValues, vararg params: Any) : ConnectorException(MessageFormat.format(errorCodeValue.message, *params), errorCodeValue.errorCode) {
    companion object {
        private val serialVersionUID = 1L
    }
}
