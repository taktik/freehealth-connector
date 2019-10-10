package org.taktik.connector.technical.validator

import org.taktik.connector.technical.exception.SoaErrorException
import be.fgov.ehealth.commons.protocol.v1.ResponseType
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType

interface EhealthReplyValidator {
    @Throws(SoaErrorException::class)
    fun validateReplyStatus(response: ResponseType): Boolean

    @Throws(SoaErrorException::class)
    fun validateReplyStatus(response: be.fgov.ehealth.commons._1_0.protocol.ResponseType): Boolean

    @Throws(SoaErrorException::class)
    fun validateReplyStatus(response: StatusResponseType): Boolean
}
