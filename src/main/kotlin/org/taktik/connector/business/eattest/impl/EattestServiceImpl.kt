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

package org.taktik.connector.business.eattest.impl

import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationRequest
import be.fgov.ehealth.mycarenet.attest.protocol.v1.SendAttestationResponse
import org.slf4j.LoggerFactory
import org.taktik.connector.business.eattest.EattestService
import org.taktik.connector.business.mycarenet.attest.service.ServiceFactory
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.utils.impl.JaxbContextFactory

import javax.xml.soap.SOAPException

class EattestServiceImpl : EattestService, ConfigurationModuleBootstrap.ModuleBootstrapHook {

    @Throws(TechnicalConnectorException::class)
    override fun sendAttestion(token: SAMLToken, request: SendAttestationRequest): SendAttestationResponse {
        try {
            val service = ServiceFactory.getAttestPort(token)
            service.setPayload(request as Any)
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            return xmlResponse.asObject(SendAttestationResponse::class.java) as SendAttestationResponse
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }

    }

    override fun bootstrap() {
        JaxbContextFactory.initJaxbContext(SendAttestationRequest::class.java)
        JaxbContextFactory.initJaxbContext(SendAttestationResponse::class.java)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(EattestServiceImpl::class.java)
    }
}
