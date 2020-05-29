package org.taktik.connector.business.mhm

import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionRequest
import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionResponse
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface MhmService {
    fun startSubscription(token: SAMLToken, request: SendSubscriptionRequest): SendSubscriptionResponse
}
