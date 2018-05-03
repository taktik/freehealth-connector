package org.taktik.connector.business.wsconsent.exception

import org.taktik.connector.technical.exception.ConnectorException
import java.text.MessageFormat

class WsConsentBusinessConnectorException : ConnectorException {

    constructor(
        errorCodeValue: WsConsentBusinessConnectorExceptionValues,
        vararg params: Any
    ) : super(MessageFormat.format(errorCodeValue.message, *params), errorCodeValue.errorCode) {
    }

    constructor(errorCodeValue: WsConsentBusinessConnectorExceptionValues, e: Throwable, vararg params: Any) : super(
        MessageFormat.format(errorCodeValue.message, *params),
        errorCodeValue.errorCode,
        e
    ) {
    }

    companion object {
        private val serialVersionUID = 1L
    }
}
