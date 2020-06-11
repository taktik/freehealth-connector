package org.taktik.connector.business.mhm.impl

import be.fgov.ehealth.mycarenet.mhm.protocol.v1.CancelSubscriptionRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.CancelSubscriptionResponse
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.NotifySubscriptionClosureRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.NotifySubscriptionClosureResponse
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionResponse
import org.taktik.connector.business.mhm.MhmService
import org.taktik.connector.business.mhm.validator.impl.MhmXmlValidatorImpl
import org.taktik.connector.business.mycarenet.mhm.service.ServiceFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken

import javax.xml.soap.SOAPException

class MhmServiceImpl : MhmService {
    @Throws(TechnicalConnectorException::class)
    override fun sendSubscription(token: SAMLToken, request: SendSubscriptionRequest, soapAction: String): SendSubscriptionResponse {
        try {
            val service = ServiceFactory.getSubscriptionPort(token)
            service.setPayload(request)
            service.setSoapAction(soapAction)
            val start = System.currentTimeMillis()
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val stop = System.currentTimeMillis()
            val mhmSensSubscriptionResponse = xmlResponse.asObject(SendSubscriptionResponse::class.java) as SendSubscriptionResponse

            mhmSensSubscriptionResponse.upstreamTiming = (stop - start).toInt()
            mhmSensSubscriptionResponse.soapRequest = xmlResponse.request
            mhmSensSubscriptionResponse.soapResponse = xmlResponse.soapMessage

            return mhmSensSubscriptionResponse
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }
    }

    @Throws(TechnicalConnectorException::class)
    override fun cancelSubscription(token: SAMLToken, request: CancelSubscriptionRequest, soapAction: String): CancelSubscriptionResponse {
        try {
            val service = ServiceFactory.getSubscriptionPort(token)
            service.setPayload(request)
            service.setSoapAction(soapAction)
            val start = System.currentTimeMillis()
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val stop = System.currentTimeMillis()
            val mhmCancelSubscriptionResponse = xmlResponse.asObject(CancelSubscriptionResponse::class.java) as CancelSubscriptionResponse

            mhmCancelSubscriptionResponse.upstreamTiming = (stop - start).toInt()
            mhmCancelSubscriptionResponse.soapRequest = xmlResponse.request
            mhmCancelSubscriptionResponse.soapResponse = xmlResponse.soapMessage

            return mhmCancelSubscriptionResponse
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }
    }

    @Throws(TechnicalConnectorException::class)
    override fun notifySubscriptionClosure(token: SAMLToken, request: NotifySubscriptionClosureRequest, soapAction: String): NotifySubscriptionClosureResponse {
        try {
            val service = ServiceFactory.getSubscriptionPort(token)
            service.setPayload(request)
            service.setSoapAction(soapAction)
            val start = System.currentTimeMillis()
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val stop = System.currentTimeMillis()
            val mhmNotifySubscriptionClosureResponse = xmlResponse.asObject(NotifySubscriptionClosureResponse::class.java) as NotifySubscriptionClosureResponse

            mhmNotifySubscriptionClosureResponse.upstreamTiming = (stop - start).toInt()
            mhmNotifySubscriptionClosureResponse.soapRequest = xmlResponse.request
            mhmNotifySubscriptionClosureResponse.soapResponse = xmlResponse.soapMessage

            return mhmNotifySubscriptionClosureResponse
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }
    }
}
