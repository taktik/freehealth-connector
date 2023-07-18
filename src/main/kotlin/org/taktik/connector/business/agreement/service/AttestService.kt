package org.taktik.connector.business.agreement.service

import be.fgov.ehealth.mycarenet.attest.protocol.v3.CancelAttestationResponse
import be.fgov.ehealth.mycarenet.attest.protocol.v3.SendAttestationResponse
import be.fgov.ehealth.mycarenet.commons.protocol.v4.SendRequestType
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface AttestService {
    @Throws(TechnicalConnectorException::class)
    fun sendAttestion(token: SAMLToken?, request: SendRequestType?): SendAttestationResponse?

    @Throws(TechnicalConnectorException::class)
    fun cancelAttestion(token: SAMLToken?, request: SendRequestType?): CancelAttestationResponse?
}
