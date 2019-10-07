package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINReply
import be.fgov.ehealth.consultrn._1_0.protocol.SearchBySSINRequest
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticReply
import be.fgov.ehealth.consultrn._1_0.protocol.SearchPhoneticRequest
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonRequest
import be.fgov.ehealth.consultrn.protocol.v2.RegisterPersonResponse
import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse
import org.taktik.connector.business.consultrn.exception.identifyperson.ConsultrnIdentifyPersonException
import org.taktik.connector.business.consultrn.exception.manageperson.ConsultrnRegisterExistingPersonException
import org.taktik.connector.business.consultrn.exception.manageperson.ConsultrnRegisterPersonException
import org.taktik.connector.business.consultrn.exception.phoneticsearch.ConsultrnPhoneticSearchException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.freehealth.middleware.dto.consultrn.PersonMid
import java.util.UUID

interface ConsultRnService {
    @Throws(TechnicalConnectorException::class, ConsultrnIdentifyPersonException::class)
    fun identify(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ssin: String
        ): SearchBySSINReply

    @Throws(TechnicalConnectorException::class, ConsultrnPhoneticSearchException::class)
    fun search(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        dateOfBirth: Int,
        lastName: String,
        firstName: String? = null,
        middleName: String? = null,
        gender: String = "UNKNOWN",
        tolerance: Int = 0,
        limit: Int = 20
        ): SearchPhoneticReply

    @Throws(TechnicalConnectorException::class, ConsultrnRegisterPersonException::class, ConsultrnRegisterExistingPersonException::class)
    fun registerPerson(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        mid: PersonMid
                      ): RegisterPersonResponse

    fun history(keystoreId: UUID, tokenId: UUID, passPhrase: String, ssin: String): ConsultCurrentSsinResponse
}
