package org.taktik.connector.technical.handler

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import java.util.concurrent.TimeUnit
import javax.xml.ws.handler.soap.SOAPMessageContext

class SAMLHolderOfKeyHandler(private val token: SAMLToken) : AbstractWsSecurityHandler() {

    @Throws(TechnicalConnectorException::class)
    override fun addWSSecurity(context: SOAPMessageContext) {
        this.buildSignature().on(context).withTimeStamp(this.getTimeStampTTL(), TimeUnit.SECONDS).withSAMLToken(token)
            .sign(AbstractWsSecurityHandler.SignedParts.BODY, AbstractWsSecurityHandler.SignedParts.TIMESTAMP)
    }

    override fun getLogger(): Log {
        return LOG
    }

    companion object {
        private val LOG = LogFactory.getLog(this::class.java)
    }
}
