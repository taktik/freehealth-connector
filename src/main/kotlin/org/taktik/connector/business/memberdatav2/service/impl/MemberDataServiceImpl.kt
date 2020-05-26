package org.taktik.connector.business.memberdatav2.service.impl

import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationRequest
import be.fgov.ehealth.mycarenet.memberdata.protocol.v1.MemberDataConsultationResponse
import org.slf4j.LoggerFactory
import org.taktik.connector.business.memberdatav2.service.MemberDataService
import org.taktik.connector.business.memberdatav2.service.ServiceFactory
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap.ModuleBootstrapHook
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.utils.impl.JaxbContextFactory
import javax.xml.soap.SOAPException

class MemberDataServiceImpl : MemberDataService, ModuleBootstrapHook {
    @Throws(TechnicalConnectorException::class)
    override fun consultMemberData(token: SAMLToken, request: MemberDataConsultationRequest): MemberDataConsultationResponse {
        return try {
            val service =
                ServiceFactory.getMemberDataSyncPort(token)
            service.setPayload(request as Any?)
            val xmlResponse =
                org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val memberDataConsultationResponse = xmlResponse.asObject(MemberDataConsultationResponse::class.java) as MemberDataConsultationResponse

            memberDataConsultationResponse.soapRequest = xmlResponse.request
            memberDataConsultationResponse.soapResponse = xmlResponse.soapMessage

            memberDataConsultationResponse
        } catch (soapException: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, soapException, *arrayOf<Any?>(soapException.message))
        }
    }

    override fun bootstrap() {
        JaxbContextFactory.initJaxbContext(MemberDataConsultationRequest::class.java)
        JaxbContextFactory.initJaxbContext(MemberDataConsultationResponse::class.java)
    }
}
