package org.taktik.connector.business.tarification.impl

import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationRequest
import be.fgov.ehealth.mycarenet.commons.protocol.v2.TarificationConsultationResponse
import org.taktik.connector.business.tarification.ServiceFactory
import org.taktik.connector.business.tarification.TarificationService
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.utils.impl.JaxbContextFactory
import javax.xml.soap.SOAPException

class TarificationServiceImpl : TarificationService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
    @Throws(TechnicalConnectorException::class)
    override fun consultTarification(token: SAMLToken, request: TarificationConsultationRequest): TarificationConsultationResponse {
        try {
            val service = ServiceFactory.getTarificationSessionForMycarenet(token)
            service.setPayload(request as Any)
            val start = System.currentTimeMillis()
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val stop = System.currentTimeMillis()
            val tarificationConsultationResponse = xmlResponse.asObject(TarificationConsultationResponse::class.java) as TarificationConsultationResponse

            tarificationConsultationResponse.upstreamTiming = (stop - start).toInt()
            tarificationConsultationResponse.soapRequest = xmlResponse.request
            tarificationConsultationResponse.soapResponse = xmlResponse.soapMessage

            return tarificationConsultationResponse
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }

    }

    override fun bootstrap() {
        JaxbContextFactory.initJaxbContext(TarificationConsultationRequest::class.java)
        JaxbContextFactory.initJaxbContext(TarificationConsultationResponse::class.java)
        JaxbContextFactory.initJaxbContext(TarificationConsultationResponse::class.java)
    }

}
