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

import org.taktik.connector.business.genins.exception.GenInsBusinessConnectorException
import org.taktik.connector.technical.exception.SessionManagementException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityAsXmlOrFlatRequestType
import be.fgov.ehealth.genericinsurability.protocol.v1.GetInsurabilityResponse

interface GenInsService {
    @Throws(GenInsBusinessConnectorException::class, TechnicalConnectorException::class, SessionManagementException::class)
    fun getInsurability(token: SAMLToken, request: GetInsurabilityAsXmlOrFlatRequestType): GetInsurabilityResponse
}
