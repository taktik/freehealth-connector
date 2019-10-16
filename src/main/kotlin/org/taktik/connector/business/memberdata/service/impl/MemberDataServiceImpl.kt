package org.taktik.connector.business.memberdata.service.impl

import org.taktik.connector.business.memberdata.service.MemberDataService
import org.taktik.connector.business.memberdata.service.ServiceFactory
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.utils.impl.JaxbContextFactory
import org.taktik.connector.technical.validator.SessionValidator
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.GenericResponse
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse
import javax.xml.soap.SOAPException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MemberDataServiceImpl : MemberDataService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
    @Throws(TechnicalConnectorException::class)
    override fun consultMemberData(token: SAMLToken, request: MemberDataConsultationRequest): MemberDataConsultationResponse {
        try {
            val service = ServiceFactory.getMemberDataSyncPort(token)
            service.setPayload(request)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val memberDataConsultationResponse = xmlResponse.asObject(MemberDataConsultationResponse::class.java)

            memberDataConsultationResponse.soapRequest = xmlResponse.request
            memberDataConsultationResponse.soapResponse = xmlResponse.soapMessage

            return memberDataConsultationResponse
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }
    }

    override fun bootstrap() {
        JaxbContextFactory.initJaxbContext(MemberDataConsultationRequest::class.java)
        JaxbContextFactory.initJaxbContext(MemberDataConsultationResponse::class.java)
    }
}
