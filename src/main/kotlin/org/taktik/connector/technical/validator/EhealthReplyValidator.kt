package org.taktik.connector.technical.validator

import org.taktik.connector.technical.exception.SoaErrorException
import be.fgov.ehealth.commons.protocol.v1.ResponseType
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType

interface EhealthReplyValidator {
    @Throws(SoaErrorException::class)
    fun validateReplyStatus(var1: ResponseType): Boolean

    @Throws(SoaErrorException::class)
    fun validateReplyStatus(var1: StatusResponseType): Boolean
}
