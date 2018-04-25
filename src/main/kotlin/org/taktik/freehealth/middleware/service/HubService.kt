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

import be.fgov.ehealth.hubservices.core.v3.PutTransactionResponse
import org.taktik.freehealth.middleware.domain.Consent
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.domain.HcPartyConsent
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkDto
import org.taktik.freehealth.middleware.domain.TransactionSummary
import java.time.LocalDateTime
import java.util.*

interface HubService {
    fun checkHcPartyConsent(token: String, inss: String, nihii: String): HcPartyConsent
    fun getPatientConsent(token: String, nissPatient: String): Consent
    fun registerPatientConsent(token: String, dateOfBirth: LocalDateTime, niss: String, firstName: String, lastName: String, gender: Gender)
    fun registerTherapeuticLink(token: String, ssin: String, start: Date, comment: String)
    fun getTherapeuticLinks(token: String, nissPatient: String, inamiDoctor: String, nissDoctor: String): List<TherapeuticLinkDto>
    fun putPatient(endpoint: String, keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpSsin: String, niss: String, firstName: String, lastName: String, gender: Gender, dateOfBirth: LocalDateTime)
    fun putTransaction(endpoint: String, hubId: Long, hubApplication: String, keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpSsin: String, hcpZip: String, ssin: String, transaction: String): PutTransactionResponse
    fun getTransaction(endpoint: String, keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpSsin: String, hcpZip: String, ssin: String, sv: String, sl: String, value: String): String
    fun getTransactionsList(endpoint: String, keystoreId: UUID, tokenId: UUID, passPhrase: String, hcpNihii: String, hcpSsin: String, hcpZip: String, ssin: String, from: Long?, to: Long?, authorNihii: String?, authorSsin: String?, isGlobal: Boolean): List<TransactionSummary>
}