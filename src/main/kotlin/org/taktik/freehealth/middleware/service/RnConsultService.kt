package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse
import org.taktik.connector.business.consultrn.exception.identifyperson.ConsultrnIdentifyPersonException
import org.taktik.connector.business.consultrnv2.exception.inscriptionservice.CbssPersonServiceException
import org.taktik.connector.business.consultrnv2.exception.personservice.SearchPersonBySsinException
import org.taktik.connector.business.consultrnv2.exception.personservice.SearchPersonPhoneticallyException
import org.taktik.connector.technical.exception.SoaErrorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnRegisterPersonResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnSearchPersonBySsinResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnSearchPersonPhoneticallyResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.PersonMid
import java.util.*
import javax.xml.ws.soap.SOAPFaultException

interface RnConsultService {
    @Throws(TechnicalConnectorException::class, SOAPFaultException::class, SoaErrorException::class, SearchPersonBySsinException::class)
    fun searchPersonBySsin(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ssin: String
    ) : ConsultRnSearchPersonBySsinResponseDto

    @Throws(TechnicalConnectorException::class, SOAPFaultException::class, SoaErrorException::class, SearchPersonPhoneticallyException::class)
    fun searchPersonPhonetically(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        dateOfBirth: Int,
        lastName: String,
        firstName: String? = null,
        middleName: String? = null,
        matchingType: String? = "ALL_GIVENNAME",
        gender: String? = "UNKNOWN",
        countryCode: Int = 0,
        cityCode: String ? = null,
        tolerance: Int? = 0,
        limit: Int? = 20
    ) : ConsultRnSearchPersonPhoneticallyResponseDto

    @Throws(TechnicalConnectorException::class, SOAPFaultException::class, SoaErrorException::class, CbssPersonServiceException::class)
    fun registerPerson(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        mid: PersonMid
    ) : ConsultRnRegisterPersonResponseDto

    @Throws(TechnicalConnectorException::class, SOAPFaultException::class, SoaErrorException::class)
    fun consultCurrentSsin(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ssin: String
    ) : ConsultCurrentSsinResponse
}
