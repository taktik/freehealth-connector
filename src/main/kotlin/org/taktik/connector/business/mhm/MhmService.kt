package org.taktik.connector.business.mhm

import be.fgov.ehealth.mycarenet.mhm.protocol.v1.CancelSubscriptionRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.CancelSubscriptionResponse
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.NotifySubscriptionClosureRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.NotifySubscriptionClosureResponse
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionResponse
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.freehealth.middleware.dto.mhm.CancelSubscriptionResult
import org.taktik.freehealth.middleware.dto.mhm.EndSubscriptionResult

interface MhmService {
    fun sendSubscription(token: SAMLToken, request: SendSubscriptionRequest, soapAction: String): SendSubscriptionResponse
    fun cancelSubscription(token: SAMLToken, request: CancelSubscriptionRequest, soapAction: String) : CancelSubscriptionResponse
    fun notifySubscriptionClosure(token: SAMLToken, request: NotifySubscriptionClosureRequest, soapAction: String) : NotifySubscriptionClosureResponse
}
