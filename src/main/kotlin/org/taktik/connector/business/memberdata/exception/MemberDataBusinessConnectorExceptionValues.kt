package org.taktik.connector.business.memberdata.exception

enum class MemberDataBusinessConnectorExceptionValues private constructor(val errorCode: String, val message: String) {
    PARAMETER_NULL("parameters.null", "This parameter is null : {0}")
}
