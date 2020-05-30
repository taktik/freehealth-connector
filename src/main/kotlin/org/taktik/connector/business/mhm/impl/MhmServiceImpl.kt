package org.taktik.connector.business.mhm.impl

import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationRequest
import be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.CancelSubscriptionRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.CancelSubscriptionResponse
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.NotifySubscriptionClosureRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.NotifySubscriptionClosureResponse
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionResponse
import org.taktik.connector.business.mhm.MhmService
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.freehealth.middleware.dto.mhm.CancelSubscriptionResult
import org.taktik.freehealth.middleware.dto.mhm.EndSubscriptionResult
import javax.xml.soap.SOAPException

class MhmServiceImpl : MhmService {
    override fun sendSubscription(token: SAMLToken, request: SendSubscriptionRequest, soapAction: String): SendSubscriptionResponse {
        try {
            val service = org.taktik.connector.business.mycarenet.mhm.service.ServiceFactory.getSubscriptionPort(token)
            service.setPayload(request as Any)
            service.setSoapAction(soapAction)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val response = xmlResponse.asObject(SendSubscriptionResponse::class.java)

            response.soapRequest = xmlResponse.request
            response.soapResponse = xmlResponse.soapMessage

            return response
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }
    }

    override fun cancelSubscription(token: SAMLToken, request: CancelSubscriptionRequest, soapAction: String): CancelSubscriptionResponse {
        try {
            val service = org.taktik.connector.business.mycarenet.mhm.service.ServiceFactory.getSubscriptionPort(token)
            service.setPayload(request as Any)
            service.setSoapAction(soapAction)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val response = xmlResponse.asObject(CancelSubscriptionResponse::class.java)

            response.soapRequest = xmlResponse.request
            response.soapResponse = xmlResponse.soapMessage

            return response
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }
    }

    override fun notifySubscriptionClosure(token: SAMLToken, request: NotifySubscriptionClosureRequest, soapAction: String): NotifySubscriptionClosureResponse {
        try {
            val service = org.taktik.connector.business.mycarenet.mhm.service.ServiceFactory.getSubscriptionPort(token)
            service.setPayload(request as Any)
            service.setSoapAction(soapAction)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val response = xmlResponse.asObject(NotifySubscriptionClosureResponse::class.java)

            response.soapRequest = xmlResponse.request
            response.soapResponse = xmlResponse.soapMessage

            return response
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }
    }
}
