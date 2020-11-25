package org.taktik.connector.business.memberdatav2.service

import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface MemberDataService {
    @Throws(TechnicalConnectorException::class)
    fun consultMemberData(token: SAMLToken, request: MemberDataConsultationRequest): MemberDataConsultationResponse
}
