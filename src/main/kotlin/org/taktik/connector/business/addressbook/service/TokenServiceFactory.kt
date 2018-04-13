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

package org.taktik.connector.business.addressbook.service

import org.taktik.connector.business.common.util.HandlerChainUtil
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.config.Configuration
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType

class TokenServiceFactory private constructor() {

    init {
        throw UnsupportedOperationException("This factory should never be instantiated, only its static methods should be used")
    }

    companion object {
        private val config = ConfigFactory.getConfigValidator()

        @Throws(TechnicalConnectorException::class)
        fun getService(token: SAMLToken): GenericRequest {
            val genReq = GenericRequest()
            genReq.setEndpoint(config.getProperty("endpoint.addressbook", "\$uddi{uddi:ehealth-fgov-be:business:addressbook:v1}"))
            genReq.setCredential(token, TokenType.SAML)
            genReq.addDefaulHandlerChain()
            genReq.addHandlerChain(HandlerChainUtil.buildChainWithValidator("validation.incoming.message.addressbook", "/XSD/ehealth-addressbook/XSD/ehealth-addressbook-protocol-1_1.xsd"))
            return genReq
        }
    }
}
