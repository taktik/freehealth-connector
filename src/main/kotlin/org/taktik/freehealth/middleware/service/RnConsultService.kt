package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse
import be.fgov.ehealth.idsupport.protocol.v2.VerifyIdResponse
import org.taktik.connector.business.consultrnv2.exception.inscriptionservice.CbssPersonServiceException
import org.taktik.connector.business.consultrnv2.exception.personservice.SearchPersonBySsinException
import org.taktik.connector.business.consultrnv2.exception.personservice.SearchPersonPhoneticallyException
import org.taktik.connector.technical.exception.SoaErrorException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultRegisterPersonResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultSearchPersonBySsinResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultSearchPersonPhoneticallyResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.RnConsultPersonMid
import java.util.*
import javax.xml.ws.soap.SOAPFaultException

interface RnConsultService {
    @Throws(TechnicalConnectorException::class, SOAPFaultException::class, SoaErrorException::class, SearchPersonBySsinException::class)
    fun searchPersonBySsin(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ssin: String
    ) : RnConsultSearchPersonBySsinResponseDto

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
    ) : RnConsultSearchPersonPhoneticallyResponseDto

    @Throws(TechnicalConnectorException::class, SOAPFaultException::class, SoaErrorException::class, CbssPersonServiceException::class)
    fun registerPerson(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        mid: RnConsultPersonMid
    ) : RnConsultRegisterPersonResponseDto

    @Throws(TechnicalConnectorException::class, SOAPFaultException::class, SoaErrorException::class)
    fun consultCurrentSsin(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ssin: String
    ) : ConsultCurrentSsinResponse

    @Throws(TechnicalConnectorException::class, SOAPFaultException::class, SoaErrorException::class)
    fun verifyId(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ssin: String?,
        cardNumber: String?,
        barCoded: String?
    ) : VerifyIdResponse
}
