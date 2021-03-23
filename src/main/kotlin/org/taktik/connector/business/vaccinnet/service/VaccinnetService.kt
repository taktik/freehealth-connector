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

package org.taktik.connector.business.vaccinnet.service

import org.taktik.connector.business.vaccinnet.AddVaccinationsRequestType
import org.taktik.connector.business.vaccinnet.AddVaccinationsResponseType
import org.taktik.connector.business.vaccinnet.GetVaccinationsRequestType
import org.taktik.connector.business.vaccinnet.GetVaccinationsResponseType
import org.taktik.connector.business.vaccinnet.RemoveVaccinationRequestType
import org.taktik.connector.business.vaccinnet.RemoveVaccinationResponseType
import org.taktik.connector.technical.exception.ConnectorException
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface VaccinnetService {
    @Throws(ConnectorException::class)
    fun addVaccinations(token: SAMLToken, request: AddVaccinationsRequestType): AddVaccinationsResponseType

    @Throws(ConnectorException::class)
    fun removeVaccination(token: SAMLToken, request: RemoveVaccinationRequestType): RemoveVaccinationResponseType

    @Throws(ConnectorException::class)
    fun getVaccinations(token: SAMLToken, request: GetVaccinationsRequestType): GetVaccinationsResponseType
}
