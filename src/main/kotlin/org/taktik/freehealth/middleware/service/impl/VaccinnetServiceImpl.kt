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

package org.taktik.freehealth.middleware.service.impl

import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.springframework.stereotype.Service
import org.taktik.connector.business.vaccinnet.AddVaccinationsRequestType
import org.taktik.connector.business.vaccinnet.AddVaccinationsResponseType
import org.taktik.connector.business.vaccinnet.GetVaccinationsRequestType
import org.taktik.connector.business.vaccinnet.GetVaccinationsResponseType
import org.taktik.connector.business.vaccinnet.RemoveVaccinationRequestType
import org.taktik.connector.business.vaccinnet.RemoveVaccinationResponseType
import org.taktik.connector.technical.service.keydepot.KeyDepotService
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.STSService
import org.taktik.freehealth.middleware.service.VaccinnetService
import org.taktik.freehealth.utils.FuzzyValues
import java.util.UUID

@Service
class VaccinnetServiceImpl(private val stsService: STSService) : VaccinnetService {
    private val freehealthVaccinnetService: org.taktik.connector.business.vaccinnet.service.VaccinnetService =
        org.taktik.connector.business.vaccinnet.service.impl.VaccinnetServiceImpl()

    override fun addVaccinations(keystoreId: UUID, tokenId: UUID, passPhrase: String, patientId: String, softwareId: String, vaccinetId: String, kmehrmessage: Kmehrmessage): AddVaccinationsResponseType {
        val marshallerHelper = MarshallerHelper<Kmehrmessage, Kmehrmessage>(Kmehrmessage::class.java, Kmehrmessage::class.java)
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw MissingTokenException("Cannot obtain token for Ehealth Box operations")
        val addVaccinationsRequest = AddVaccinationsRequestType().apply {
            this.softwareId = softwareId
            this.vaccinnetId = vaccinnetId
            this.patientId = patientId
            this.base64EncodedKmehrmessage.add(AddVaccinationsRequestType.Base64EncodedKmehrmessage().apply {
                value = marshallerHelper.toXMLByteArray(kmehrmessage)
            })
        }
        return freehealthVaccinnetService.addVaccinations(samlToken, addVaccinationsRequest)
    }

    override fun removeVaccination(keystoreId: UUID, tokenId: UUID, passPhrase: String, patientId: String, softwareId: String, vaccinetId: String, vaccinationId: String): RemoveVaccinationResponseType {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw MissingTokenException("Cannot obtain token for Ehealth Box operations")
        val removeVaccinationsRequest = RemoveVaccinationRequestType().apply {
            this.softwareId = softwareId
            this.vaccinnetId = vaccinnetId
            this.patientId = patientId
            this.vaccinationId = vaccinationId
        }
        return freehealthVaccinnetService.removeVaccination(samlToken, removeVaccinationsRequest)
    }

    override fun getVaccinations(keystoreId: UUID, tokenId: UUID, passPhrase: String, patientId: String, softwareId: String, vaccinetId: String, since: Long): GetVaccinationsResponseType {
        val samlToken = stsService.getSAMLToken(tokenId, keystoreId, passPhrase)
            ?: throw MissingTokenException("Cannot obtain token for Ehealth Box operations")
        val getVaccinationsRequest = GetVaccinationsRequestType().apply {
            this.softwareId = softwareId
            this.vaccinnetId = vaccinnetId
            this.patientId = patientId
            this.vaccinationDateSince = FuzzyValues.getXMLGregorianCalendarFromFuzzyLong(since)
        }
        return freehealthVaccinnetService.getVaccinations(samlToken, getVaccinationsRequest)
    }
}
