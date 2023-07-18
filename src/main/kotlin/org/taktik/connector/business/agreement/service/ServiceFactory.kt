package org.taktik.connector.business.agreement.service

import org.apache.commons.lang.Validate
import org.taktik.connector.business.memberdata.service.ServiceFactory
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.Configuration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType

object ServiceFactory {
    private const val PROP_ENDPOINT_ATTEST_V3 = "endpoint.eattestv3"
    private val expectedProps: List<String?> = emptyList()
    private val config: Configuration? = ConfigFactory.getConfigValidator(expectedProps)
    @JvmStatic
    @Throws(TechnicalConnectorException::class)
    fun getAttestPort(token: SAMLToken?, soapAction: String?): GenericRequest {
        Validate.notNull(token, "Required parameter SAMLToken is null.")
        ServiceFactory.config.getProperty(ServiceFactory.PROP_ENDPOINT_MEMBERDATASYNC, "\$uddi{uddi:ehealth-fgov-be:business:mycarenetmemberdata:v1}")
        val endPoint = config!!.getProperty(PROP_ENDPOINT_ATTEST_V3, "\$uddi{uddi:ehealth-fgov-be:business:mycarenetmemberdata:v1}")
        return GenericRequest().setEndpoint(endPoint ?: "https://services-acpt.ehealth.fgov.be/MyCareNet/eAttest/v3")
            .setCredential(token, TokenType.SAML).setSoapAction(soapAction).addDefaulHandlerChain()
    }
}
