package org.taktik.connector.business.genericasync.handlers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.taktik.connector.technical.config.domain.Duration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.handler.AbstractWsSecurityHandler
import org.taktik.connector.technical.service.sts.security.SAMLToken
import javax.xml.ws.handler.soap.SOAPMessageContext

class SAMLHolderOfKeyHandler(private val token: SAMLToken,
                             private val duration: Duration) : AbstractWsSecurityHandler() {

    @Throws(TechnicalConnectorException::class)
    override fun addWSSecurity(context: SOAPMessageContext) {
        this.buildSignature().on(context.message).withTimeStamp(this.duration).withSAMLToken(this.token)
            .sign(AbstractWsSecurityHandler.SignedParts.TIMESTAMP)
    }

    override fun getLogger(): Log {
        return LOG
    }

    companion object {
        private val LOG = LogFactory.getLog(this::class.java)
    }
}
