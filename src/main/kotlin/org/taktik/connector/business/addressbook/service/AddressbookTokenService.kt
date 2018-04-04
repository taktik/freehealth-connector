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

import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoRequest
import be.fgov.ehealth.addressbook.protocol.v1.GetOrganizationContactInfoResponse
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoRequest
import be.fgov.ehealth.addressbook.protocol.v1.GetProfessionalContactInfoResponse
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsRequest
import be.fgov.ehealth.addressbook.protocol.v1.SearchOrganizationsResponse
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsRequest
import be.fgov.ehealth.addressbook.protocol.v1.SearchProfessionalsResponse

interface AddressbookTokenService {
    @Throws(TechnicalConnectorException::class)
    fun getOrganizationContactInfo(token: SAMLToken, request: GetOrganizationContactInfoRequest): GetOrganizationContactInfoResponse

    @Throws(TechnicalConnectorException::class)
    fun getProfessionalContactInfo(token: SAMLToken, request: GetProfessionalContactInfoRequest): GetProfessionalContactInfoResponse

    @Throws(TechnicalConnectorException::class)
    fun searchOrganizations(token: SAMLToken, request: SearchOrganizationsRequest): SearchOrganizationsResponse

    @Throws(TechnicalConnectorException::class)
    fun searchProfessionals(token: SAMLToken, request: SearchProfessionalsRequest): SearchProfessionalsResponse
}
