/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.connector.business.ehbox.service


import org.taktik.connector.business.common.util.HandlerChainUtil
import org.taktik.connector.business.ehbox.api.domain.exception.EhboxBusinessConnectorException
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType
import java.net.MalformedURLException

object ServiceFactory {
    private val config = ConfigFactory.getConfigValidator()
    val PROP_ENDPOINT_CONSULTATION_V3 = "endpoint.ehbox.consultation.v3"
    val PROP_ENDPOINT_PUBLICATION_V3 = "endpoint.ehbox.publication.v3"
    val PROP_VALIDATION_INCOMING_EHBOX_V3 = "validation.incoming.ehbox.v3.message"
    internal val EHBOX_CONS_PROT = "/ehealth-ehbox/XSD/ehealth-ehBox-consultation-schema-protocol-3_0.xsd"
    internal val EHBOX_PUB_PROT = "/ehealth-ehbox/XSD/ehealth-ehBox-publication-schema-protocol-3_0.xsd"

    @Throws(MalformedURLException::class, TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    fun getConsultationService(token: SAMLToken): GenericRequest {
        val genReq = GenericRequest()
        genReq.setEndpoint(config.getProperty("endpoint.ehbox.consultation.v3", "\$uddi{uddi:ehealth-fgov-be:business:ehboxconsultation:v3}"))
        genReq.setCredential(token, TokenType.SAML)
        genReq.addDefaulHandlerChain()
        genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.ehbox.v3.message", "/ehealth-ehbox/XSD/ehealth-ehBox-consultation-schema-protocol-3_0.xsd"))
        return genReq
    }

    @Throws(MalformedURLException::class, TechnicalConnectorException::class, EhboxBusinessConnectorException::class)
    fun getPublicationService(token: SAMLToken): GenericRequest {
        val genReq = GenericRequest()
        genReq.setEndpoint(config.getProperty("endpoint.ehbox.publication.v3", "\$uddi{uddi:ehealth-fgov-be:business:ehboxpublication:v3}"))
        genReq.setCredential(token, TokenType.SAML)
        genReq.addDefaulHandlerChain()
        genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.ehbox.v3.message", "/ehealth-ehbox/XSD/ehealth-ehBox-publication-schema-protocol-3_0.xsd"))
        return genReq
    }
}