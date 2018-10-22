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

package org.taktik.connector.business.genins.service.impl

import be.fgov.ehealth.genericinsurability.core.v1.CommonInputType
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsFlatRequest
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsFlatResponse
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsXmlOrFlatRequestType
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityRequest
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityResponse
import org.slf4j.LoggerFactory
import org.taktik.connector.business.genins.exception.GenInsBusinessConnectorException
import org.taktik.connector.business.genins.service.GenInsService
import org.taktik.connector.business.genins.service.ServiceFactory
import org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap
import org.taktik.connector.technical.exception.SessionManagementException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.utils.impl.JaxbContextFactory
import java.net.MalformedURLException
import javax.xml.soap.SOAPException

class GenInsServiceImpl : GenInsService, ConfigurationModuleBootstrap.ModuleBootstrapHook {
    companion object {
        private val LOG = LoggerFactory.getLogger(GenInsServiceImpl::class.java)
    }

    @Throws(
        GenInsBusinessConnectorException::class,
        TechnicalConnectorException::class,
        SessionManagementException::class
    )
    override fun getInsurability(
        token: SAMLToken,
        request: GetInsurabilityAsXmlOrFlatRequestType
    ): GetInsurabilityResponse {
        val genericReq = GetInsurabilityRequest().apply {
            commonInput = request.commonInput
            recordCommonInput = request.recordCommonInput
            this.request = request.request
        }

        try {
            val service = ServiceFactory.getGeninsPort(token).apply { setPayload(genericReq) }
            val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)

            val response = xmlResponse.asObject(GetInsurabilityResponse::class.java) as GetInsurabilityResponse

            response.soapRequest = xmlResponse.request
            response.soapResponse = xmlResponse.soapMessage

            return response
        } catch (e: MalformedURLException) {
            LOG.error("GeninsServiceImpl : " + e.message)
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.MALFORMED_URL, e, "genins " + e.message)
        } catch (e: SOAPException) {
            LOG.error("GeninsServiceImpl : " + e.message)
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, e, e.message)
        }
    }

    override fun bootstrap() {
        JaxbContextFactory.initJaxbContext(GetInsurabilityAsFlatRequest::class.java)
        JaxbContextFactory.initJaxbContext(GetInsurabilityAsFlatResponse::class.java)
        JaxbContextFactory.initJaxbContext(GetInsurabilityAsXmlOrFlatRequestType::class.java)
        JaxbContextFactory.initJaxbContext(GetInsurabilityRequest::class.java)
        JaxbContextFactory.initJaxbContext(GetInsurabilityResponse::class.java)
        JaxbContextFactory.initJaxbContext(CommonInputType::class.java)
    }
}
