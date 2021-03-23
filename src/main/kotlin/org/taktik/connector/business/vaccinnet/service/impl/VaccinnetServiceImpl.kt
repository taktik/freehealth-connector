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

package org.taktik.connector.business.vaccinnet.service.impl

import org.taktik.connector.business.vaccinnet.service.VaccinnetService
import org.taktik.connector.business.vaccinnet.service.ServiceFactory
import org.taktik.connector.business.vaccinnet.AddVaccinationsRequestType
import org.taktik.connector.business.vaccinnet.AddVaccinationsResponseType
import org.taktik.connector.business.vaccinnet.GetVaccinationsRequestType
import org.taktik.connector.business.vaccinnet.GetVaccinationsResponseType
import org.taktik.connector.business.vaccinnet.RemoveVaccinationRequestType
import org.taktik.connector.business.vaccinnet.RemoveVaccinationResponseType
import org.taktik.connector.technical.service.sts.security.SAMLToken

class VaccinnetServiceImpl() : VaccinnetService {
    override fun addVaccinations(token: SAMLToken, request: AddVaccinationsRequestType): AddVaccinationsResponseType {
        val service = ServiceFactory.getVaccinationService(token).apply {
            setPayload(request as Any)
            setSoapAction("urn:be:fgov:ehealth:safe:vaccination:AddVaccinations")
        }
        val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
        return xmlResponse.asObject(AddVaccinationsResponseType::class.java) as AddVaccinationsResponseType
    }

    override fun removeVaccination(
        token: SAMLToken,
        request: RemoveVaccinationRequestType
    ): RemoveVaccinationResponseType {
        val service = ServiceFactory.getVaccinationService(token).apply {
            setPayload(request as Any)
            setSoapAction("urn:be:fgov:ehealth:safe:vaccination:RemoveVaccination")
        }
        val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
        return xmlResponse.asObject(RemoveVaccinationResponseType::class.java) as RemoveVaccinationResponseType
    }

    override fun getVaccinations(token: SAMLToken, request: GetVaccinationsRequestType): GetVaccinationsResponseType {
        val service = ServiceFactory.getVaccinationService(token).apply {
            setPayload(request as Any)
            setSoapAction("urn:be:fgov:ehealth:safe:vaccination:GetVaccinations")
        }
        val xmlResponse = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(service)
        return xmlResponse.asObject(GetVaccinationsResponseType::class.java) as GetVaccinationsResponseType
    }
}
