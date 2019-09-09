package org.taktik.connector.technical.validator.impl

import org.taktik.connector.technical.exception.SoaErrorException
import org.taktik.connector.technical.validator.EhealthReplyValidator
import be.fgov.ehealth.commons.core.v1.LocalisedString
import be.fgov.ehealth.commons.protocol.v1.ResponseType
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EhealthReplyValidatorImpl : EhealthReplyValidator {
    @Throws(SoaErrorException::class)
    override fun validateReplyStatus(response: be.fgov.ehealth.commons._1_0.protocol.ResponseType): Boolean {
        val code = response.getStatus().getCode()
        if ("100" != code) {
            LOG.error("Error Status received : " + code + " " + (response.getStatus().getMessages().get(0) as be.fgov.ehealth.commons._1_0.core.LocalisedString).value)
            throw SoaErrorException(code, response)
        } else {
            return true
        }
    }

    @Throws(SoaErrorException::class)
    override fun validateReplyStatus(response: ResponseType): Boolean {
        val code = response.status.code
        if ("100" != code && "200" != code) {
            LOG.error("Error Status received : " + code + " " + (response.status.messages[0] as LocalisedString).value)
            throw SoaErrorException(code, response)
        } else {
            return true
        }
    }

    @Throws(SoaErrorException::class)
    override fun validateReplyStatus(response: StatusResponseType): Boolean {
        val code = response.status.statusCode.value
        if ("urn:be:fgov:ehealth:2.0:status:Success" != code) {
            LOG.error("Error Status received : " + code + " " + response.status.statusMessage)
            throw SoaErrorException(code, response)
        } else {
            return true
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(EhealthReplyValidatorImpl::class.java)
        val EHEALTH_SUCCESS_CODE_100 = "100"
        val EHEALTH_SUCCESS_CODE_200 = "200"
        private val EHEALTH_SUCCESS_URN = "urn:be:fgov:ehealth:2.0:status:Success"
    }
}
