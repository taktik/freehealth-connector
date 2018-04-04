package org.taktik.connector.business.therlink.exception

import org.taktik.connector.technical.exception.ConnectorException
import java.text.MessageFormat

class TherLinkBusinessConnectorException : ConnectorException {

	constructor(errorCodeValue: TherLinkBusinessConnectorExceptionValues, vararg params: Any) : super(MessageFormat.format(errorCodeValue.message, *params), errorCodeValue.errorCode) {}

	constructor(errorCodeValue: TherLinkBusinessConnectorExceptionValues, e: Throwable, vararg params: Any) : super(MessageFormat.format(errorCodeValue.message, *params), errorCodeValue.errorCode, e) {}

	companion object {
		private val serialVersionUID = 1L
	}
}
