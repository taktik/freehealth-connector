package org.taktik.connector.technical.validator

import org.taktik.connector.technical.exception.InvalidTimeStampException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import java.security.KeyStore
import org.bouncycastle.tsp.TimeStampToken

interface TimeStampValidator {

    @Throws(InvalidTimeStampException::class, TechnicalConnectorException::class)
    fun validateTimeStampToken(var1: TimeStampToken)

    @Throws(InvalidTimeStampException::class, TechnicalConnectorException::class)
    fun validateTimeStampToken(var1: ByteArray, var2: TimeStampToken)


    @Deprecated("")
    fun setKeyStore(var1: KeyStore?)


    @Deprecated("")
    fun setAliases(var1: MutableList<String>)
}
