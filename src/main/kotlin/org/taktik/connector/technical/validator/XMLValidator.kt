package org.taktik.connector.technical.validator

import org.taktik.connector.technical.exception.TechnicalConnectorException

interface XMLValidator {
    @Throws(TechnicalConnectorException::class)
    fun validate(var1: Any?)
}
