package org.taktik.freehealth.middleware.service

import be.fgov.ehealth.consultrn.ssinhistory.protocol.v1.ConsultCurrentSsinResponse
import be.fgov.ehealth.rn.cbsspersonservice.protocol.v1.RegisterPersonResponse
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonBySsinResponse
import be.fgov.ehealth.rn.personservice.protocol.v1.SearchPersonPhoneticallyResponse
import org.taktik.freehealth.middleware.dto.consultrn.PersonMid
import java.util.*

interface ConsultRnV2Service {
    fun searchPersonBySsin(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ssin: String
    ) : SearchPersonBySsinResponse

    fun searchPersonPhonetically(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        dateOfBirth: Int,
        lastName: String,
        firstName: String? = null,
        middleName: String? = null,
        gender: String = "UNKNOWN",
        countryCode: Int = 0,
        cityCode: String ? = null,
        tolerance: Int = 0,
        limit: Int = 20
    ) : SearchPersonPhoneticallyResponse

    fun registerPerson(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        mid: PersonMid
    ) : RegisterPersonResponse

    fun consultCurrentSsin(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ssin: String
    ) : ConsultCurrentSsinResponse
}
