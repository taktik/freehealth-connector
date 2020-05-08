package org.taktik.connector.business.mediprima.service

import org.apache.commons.lang.Validate
import org.taktik.connector.business.common.util.HandlerChainUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.Configuration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType

object ServiceFactory {
    private const val PROP_ENDPOINT_CONSULTATION_MEDIPRIMA = "endpoint.mediprima.consultation"
    private const val PROP_VALIDATION_INCOMING_MEDIPRIMA =
        "validation.incoming.message.mediprima.consultation"
    private const val PROP_VALIDATION_INCOMING_CONS_TARIFICATION_MEDIPRIMA =
        "validation.incoming.message.mediprima.tarification"
    private const val PROP_ENDPOINT_TARIFICATION_MEDIPRIMA = "endpoint.mediprima.tarification"
    private const val TARIFICATION_PROTOCOL =
        "/ehealth-mycarenet-tarification/XSD/mycarenet-tarification-protocol-1_0.xsd"
    const val MEDIPRIMA_XSD = "/ehealth-mediprima/XSD/ehealth-mediprima-protocol-1_0.xsd"
    private val config: Configuration = ConfigFactory.getConfigValidator()

    @Throws(TechnicalConnectorException::class)
    fun getMediprimaTarificationService(token: SAMLToken?): GenericRequest {
        Validate.notNull(token, "Required parameter SAMLToken is null.")
        return GenericRequest().setEndpoint(config.getProperty("endpoint.mediprima.tarification", "\$uddi{uddi:ehealth-fgov-be:business:mycarenettarificationmediprima:v1}"))
            .setCredential(token, TokenType.SAML)
            .setSoapAction("urn:be:fgov:ehealth:mycarenet:tarification:protocol:v1:TarificationConsult")
            .addDefaulHandlerChain()
            .addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.mediprima.tarification", "/ehealth-mycarenet-tarification/XSD/mycarenet-tarification-protocol-1_0.xsd"))
    }

    @Throws(TechnicalConnectorException::class)
    fun getMediprimaConsultationService(token: SAMLToken?,
        soapAction: String?): GenericRequest {
        Validate.notNull(token, "Required parameter SAMLToken is null.")
        Validate.notNull(token, "Required parameter soapAction is null.")
        return GenericRequest().setEndpoint(config.getProperty("endpoint.mediprima.consultation", "\$uddi{uddi:ehealth-fgov-be:business:mediprimaconsult:v1}"))
            .setCredential(token, TokenType.SAML)
            .setSoapAction(soapAction)
            .addDefaulHandlerChain()
            .addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.mediprima.consultation", "/ehealth-mediprima/XSD/ehealth-mediprima-protocol-1_0.xsd"))
    }
}
