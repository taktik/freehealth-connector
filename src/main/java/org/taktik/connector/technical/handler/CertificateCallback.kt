package org.taktik.connector.technical.handler

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.Credential
import org.taktik.connector.technical.service.sts.security.impl.KeyPairCredential
import java.security.PrivateKey
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.xml.ws.handler.soap.SOAPMessageContext

class CertificateCallback : AbstractWsSecurityHandler {
    private var cred: Credential? = null

    @Throws(TechnicalConnectorException::class)
    constructor(certificate: X509Certificate, privateKey: PrivateKey) {
        this.cred = KeyPairCredential(privateKey, certificate)
    }

    @Throws(TechnicalConnectorException::class)
    constructor(cred: Credential) {
        this.cred = cred
    }

    @Throws(TechnicalConnectorException::class)
    override fun addWSSecurity(context: SOAPMessageContext) {

        this.buildSignature().on(context.message).withTimeStamp(this.getTimeStampTTL(), TimeUnit.SECONDS).withBinarySecurityToken(cred)
            .sign(AbstractWsSecurityHandler.SignedParts.BODY, AbstractWsSecurityHandler.SignedParts.TIMESTAMP, AbstractWsSecurityHandler.SignedParts.BST)
    }

    override fun getLogger(): Log {
        return LOG
    }

    companion object {
        private val LOG = LogFactory.getLog(this::class.java)
    }
}
