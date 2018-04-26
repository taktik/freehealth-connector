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

package org.taktik.freehealth.middleware.service

import org.taktik.connector.business.therlink.domain.TherapeuticLinkMessage
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import java.util.*

interface TherLinkService {
	fun getAllTherapeuticLinks(keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpSsin: String, hcpFirstName: String, hcpLastName: String, patientSsin: String, patientFirstName: String, patientLastName: String, eidCardNumber: String?, isiCardNumber: String?, startDate: Date?, endDate: Date?, type: String?, sign: Boolean?): List<TherapeuticLinkMessage>?
	fun getAllTherapeuticLinksWithQueryLink(keystoreId: UUID, tokenId: UUID, passPhrase: String, queryLink: TherapeuticLink, sign: Boolean?): List<TherapeuticLinkMessage>?
	fun doesLinkExist(keystoreId: UUID, tokenId: UUID, passPhrase: String, therLink: TherapeuticLink): TherapeuticLink?
	fun registerTherapeuticLink(keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpSsin: String, hcpFirstName: String, hcpLastName: String, patientSsin: String, patientFirstName: String, patientLastName: String, eidCardNumber: String?, isiCardNumber: String?, start: Date?, end: Date?, therLinkType: String?, comment: String?, sign: Boolean?): TherapeuticLinkMessage
	fun revokeLink(keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpSsin: String, hcpFirstName: String, hcpLastName: String, patientSsin: String, patientFirstName: String, patientLastName: String, eidCardNumber: String?, isiCardNumber: String?, start: Date?, end: Date?, therLinkType: String?, comment: String?, sign: Boolean?): TherapeuticLinkMessage?
	fun revokeLink(keystoreId: UUID, tokenId: UUID, passPhrase: String, therLink: TherapeuticLink, sign: Boolean?): TherapeuticLinkMessage
}