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

package org.taktik.connector.business.addressbook.service.impl

import org.taktik.connector.business.addressbook.service.AddressbookTokenService
import org.taktik.connector.business.addressbook.service.TokenServiceFactory
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.validator.EhealthReplyValidator
import org.taktik.connector.technical.validator.SessionValidator
import org.taktik.connector.technical.ws.ServiceFactory
import org.taktik.connector.technical.ws.domain.GenericRequest
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoRequest
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoResponse
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoRequest
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoResponse
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsRequest
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsResponse
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsRequest
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsResponse
import be.fgov.ehealth.commons.protocol.v2.RequestType
import be.fgov.ehealth.commons.protocol.v2.StatusResponseType
import java.util.HashMap
import javax.xml.soap.SOAPException

class AddressbookTokenServiceImpl @Throws(TechnicalConnectorException::class)
constructor(private val sessionValidator: SessionValidator, private val ehealthReplyValidator: EhealthReplyValidator) : AddressbookTokenService {

    @Throws(TechnicalConnectorException::class)
    override fun getOrganizationContactInfo(token: SAMLToken, request: GetOrganizationContactInfoRequest): GetOrganizationContactInfoResponse {
        return this.invoke(token, request, GetOrganizationContactInfoResponse::class.java)
    }

    @Throws(TechnicalConnectorException::class)
    override fun getProfessionalContactInfo(token: SAMLToken, request: GetProfessionalContactInfoRequest): GetProfessionalContactInfoResponse {
        return this.invoke(token, request, GetProfessionalContactInfoResponse::class.java)
    }

    @Throws(TechnicalConnectorException::class)
    override fun searchOrganizations(token: SAMLToken, request: SearchOrganizationsRequest): SearchOrganizationsResponse {
        return this.invoke(token, request, SearchOrganizationsResponse::class.java)
    }

    @Throws(TechnicalConnectorException::class)
    override fun searchProfessionals(token: SAMLToken, request: SearchProfessionalsRequest): SearchProfessionalsResponse {
        return this.invoke(token, request, SearchProfessionalsResponse::class.java)
    }

    @Throws(TechnicalConnectorException::class)
    private operator fun <T : StatusResponseType> invoke(token: SAMLToken, request: RequestType, clazz: Class<T>): T {
        try {
            this.sessionValidator.validateToken(token)
            val service = TokenServiceFactory.getService(token).apply {
                setPayload(request)
                setSoapAction(soapActions[clazz])
            }
            val response = ServiceFactory.getGenericWsSender().send(service).asObject(clazz)
            this.ehealthReplyValidator.validateReplyStatus(response)
            return response
        } catch (ex: SOAPException) {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, ex, ex.message)
        }

    }

    companion object {
        private val soapActions = HashMap<Class<out StatusResponseType>, String>()

        init {
            soapActions.put(GetProfessionalContactInfoResponse::class.java, "urn:be:fgov:ehealth:addressbook:protocol:v1:getProfessionalContactInfo")
            soapActions.put(GetOrganizationContactInfoResponse::class.java, "urn:be:fgov:ehealth:addressbook:protocol:v1:getOrganizationContactInfo")
            soapActions.put(SearchProfessionalsResponse::class.java, "urn:be:fgov:ehealth:addressbook:protocol:v1:searchProfessionals")
            soapActions.put(SearchOrganizationsResponse::class.java, "urn:be:fgov:ehealth:addressbook:protocol:v1:searchOrganizations")
        }
    }
}
