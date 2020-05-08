package org.taktik.connector.business.memberdatav2.builders

import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse
import org.taktik.connector.business.memberdatav2.domain.MemberDataBuilderResponse
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.etee.Crypto

interface ResponseObjectBuilder {
    @Throws(TechnicalConnectorException::class)
    fun handleConsultationResponse(consultResponse: MemberDataConsultationResponse, crypto: Crypto): MemberDataBuilderResponse?
}
