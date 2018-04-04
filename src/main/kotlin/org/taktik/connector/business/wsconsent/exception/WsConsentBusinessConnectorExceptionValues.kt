package org.taktik.connector.business.wsconsent.exception

enum class WsConsentBusinessConnectorExceptionValues private constructor(val errorCode: String, val message: String) {
	REQUIRED_FIELD_NULL("required.field.null", "A required field is missing : {1}")
}
