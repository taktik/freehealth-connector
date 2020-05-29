package org.taktik.connector.business.mhm.impl

import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationRequest
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionResponse
import org.taktik.connector.business.mhm.MhmService
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import javax.xml.soap.SOAPException

class MhmServiceImpl : MhmService {
    override fun startSubscription(token: SAMLToken, request: SendSubscriptionRequest): SendSubscriptionResponse {
        try {
            val service = org.taktik.connector.business.mycarenet.mhm.service.ServiceFactory.getSubscriptionPort(token)
            service.setPayload(request as Any)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val response = xmlResponse.asObject(SendSubscriptionResponse::class.java)

            response.soapRequest = xmlResponse.request
            response.soapResponse = xmlResponse.soapMessage

            return response
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }
    }
}
