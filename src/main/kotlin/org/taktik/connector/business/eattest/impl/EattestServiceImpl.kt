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
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationRequest
import be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationResponse
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
    private val log = LoggerFactory.getLogger(EattestServiceImpl::class.java)

    override fun sendAttestion(token: SAMLToken, request: be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationRequest): be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse {
        try {
            val service = org.taktik.connector.business.mycarenet.attestv2.service.ServiceFactory.getAttestPort(token)
            service.setPayload(request as Any)
            val start = System.currentTimeMillis()
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val stop = System.currentTimeMillis()
            val response = xmlResponse.asObject(be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse::class.java) as be.fgov.ehealth.mycarenet.attest.protocol.v2.SendAttestationResponse

            response.upstreamTiming = (stop - start).toInt();
            response.soapRequest = xmlResponse.request
            response.soapResponse = xmlResponse.soapMessage

            return response
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }
    }

    override fun cancelAttestion(token: SAMLToken, request: CancelAttestationRequest): CancelAttestationResponse {
        try {
            val service = org.taktik.connector.business.mycarenet.attestv2.service.ServiceFactory.getAttestPort(token)
            service.setPayload(request as Any)
            val start = System.currentTimeMillis()
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val stop = System.currentTimeMillis()
            val response = xmlResponse.asObject(be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationResponse::class.java) as be.fgov.ehealth.mycarenet.attest.protocol.v2.CancelAttestationResponse

            response.upstreamTiming = (stop - start).toInt();
            response.soapRequest = xmlResponse.request
            response.soapResponse = xmlResponse.soapMessage

            return response
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }    }

    @Throws(TechnicalConnectorException::class)
    override fun sendAttestion(token: SAMLToken, request: SendAttestationRequest): SendAttestationResponse {
        try {
            val service = ServiceFactory.getAttestPort(token)
            service.setPayload(request as Any)
            val start = System.currentTimeMillis()
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
            val stop = System.currentTimeMillis()
            val response = xmlResponse.asObject(SendAttestationResponse::class.java) as SendAttestationResponse

            response.upstreamTiming = (stop - start).toInt();
            response.soapRequest = xmlResponse.request
            response.soapResponse = xmlResponse.soapMessage

            return response
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }
    }

    override fun bootstrap() {
        JaxbContextFactory.initJaxbContext(SendAttestationRequest::class.java)
        JaxbContextFactory.initJaxbContext(SendAttestationResponse::class.java)
    }

}
