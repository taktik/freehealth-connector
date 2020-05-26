package org.taktik.connector.business.mediprimav2.service

import org.apache.commons.lang.Validate
import org.taktik.connector.business.common.util.HandlerChainUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.Configuration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType

object ServiceFactory {
    private const val PROP_ENDPOINT_CONSULTATION_MEDIPRIMA = "endpoint.mediprimav2.consultation"
    private const val PROP_VALIDATION_INCOMING_MEDIPRIMA =
        "validation.incoming.message.mediprimav2.consultation"
    const val MEDIPRIMA_XSD = "/ehealth-mediprima/XSD/ehealth-mediprima-protocol-2_0.xsd"
    private val config: Configuration = ConfigFactory.getConfigValidator()

    @Throws(TechnicalConnectorException::class)
    fun getMediprimaConsultationService(token: SAMLToken?,
        soapAction: String?): GenericRequest {
        Validate.notNull(token, "Required parameter SAMLToken is null.")
        Validate.notNull(token, "Required parameter soapAction is null.")
        return GenericRequest().setEndpoint(config.getProperty("endpoint.mediprimav2.consultation", "\$uddi{uddi:ehealth-fgov-be:business:mediprimaconsult:v2}"))
            .setCredential(token, TokenType.SAML)
            .setSoapAction(soapAction)
            .addDefaulHandlerChain()
            .addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.mediprimav2.consultation", "/ehealth-mediprima/XSD/ehealth-mediprima-protocol-2_0.xsd"))
    }

}
