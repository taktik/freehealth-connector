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

package org.taktik.connector.business.genins.service

import org.taktik.connector.business.common.util.HandlerChainUtil
import org.taktik.connector.business.genins.exception.GenInsBusinessConnectorException
import org.taktik.connector.technical.config.ConfigFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.ws.domain.GenericRequest
import org.taktik.connector.technical.ws.domain.TokenType
import java.net.MalformedURLException

class ServiceFactory private constructor() {
    init {
        throw UnsupportedOperationException()
    }

    companion object {
        private val PROP_ENDPOINT_GENINS_V1 = "endpoint.genins"
        private val config = ConfigFactory.getConfigValidator()
        val GENINS_XSD = arrayOf("/ehealth-mycarenet-genins/XSD/ehealth-genins-protocol-1_1.xsd")

        @Throws(
            MalformedURLException::class,
            TechnicalConnectorException::class,
            GenInsBusinessConnectorException::class
        )
        fun getGeninsPort(token: SAMLToken): GenericRequest {
            val genReq = GenericRequest()
            genReq.setEndpoint(
                config.getProperty(
                    PROP_ENDPOINT_GENINS_V1,
                    "\$uddi{uddi:ehealth-fgov-be:business:genericinsurability:v1}"
                )
            )
            genReq.setCredential(token, TokenType.SAML)
            genReq.setDefaultHandlerChain()
            genReq.setHandlerChain(
                HandlerChainUtil.buildChainWithValidator(
                    "validation.incoming.genins.message",
                    *GENINS_XSD
                )
            )
            return genReq
        }
    }
}
