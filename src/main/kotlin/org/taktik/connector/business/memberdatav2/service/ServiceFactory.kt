package org.taktik.connector.business.memberdatav2.service

import org.apache.commons.lang.Validate
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.Configuration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType
import java.util.ArrayList

object ServiceFactory {
    private const val PROP_ENDPOINT_MEMBERDATASYNC = "endpoint.memberdata"
    private val expectedProps: List<String> = ArrayList()
    private var config: Configuration? = null

    @Throws(TechnicalConnectorException::class)
    fun getMemberDataSyncPort(token: SAMLToken): GenericRequest {
        return GenericRequest().setEndpoint(config!!.getProperty("endpoint.memberdata", "\$uddi{uddi:ehealth-fgov-be:business:mycarenetmemberdata:v1}"))
            .setCredential(token, TokenType.SAML)
            .addDefaulHandlerChain()
    }

    init {
        config =
            ConfigFactory.getConfigValidator(expectedProps)
    }
}
