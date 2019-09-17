package org.taktik.connector.business.memberdata.builders

import be.ehealth.businessconnector.mycarenet.memberdata.domain.MemberDataBuilderResponse
import org.taktik.connector.technical.exception.TechnicalConnectorException
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse

interface ResponseObjectBuilder {
    @Throws(TechnicalConnectorException::class)
    fun handleConsultationResponse(var1: MemberDataConsultationResponse): MemberDataBuilderResponse
}
