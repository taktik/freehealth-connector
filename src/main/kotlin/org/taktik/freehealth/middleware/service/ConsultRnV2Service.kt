package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnRegisterPersonResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnSearchPersonBySsinResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.ConsultRnSearchPersonPhoneticallyResponseDto
import org.taktik.freehealth.middleware.dto.consultrnv2.PersonMid
import java.util.*

interface ConsultRnV2Service {
    fun searchPersonBySsin(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ssin: String
    ) : ConsultRnSearchPersonBySsinResponseDto

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

    fun registerPerson(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        mid: PersonMid
    ) : ConsultRnRegisterPersonResponseDto

    fun consultCurrentSsin(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ssin: String
    ) : ConsultCurrentSsinResponse
}
