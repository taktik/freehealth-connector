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

import be.fgov.ehealth.hubservices.core.v3.PutTransactionSetResponse
import be.fgov.ehealth.hubservices.core.v3.TransactionIdType
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import org.taktik.connector.business.therlink.domain.TherapeuticLinkMessage
import org.taktik.freehealth.middleware.domain.consent.Consent
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.domain.hub.HcPartyConsent
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.domain.hub.TransactionSummary
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkMessageDto
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

interface HubService {
    fun getHcPartyConsent(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        hubPackageId: String?
    ): HcPartyConsent?

    fun putPatient(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        firstName: String,
        lastName: String,
        gender: Gender,
        dateOfBirth: LocalDateTime,
        hubPackageId: String?
    ): Patient?

    fun getPatient(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        hubPackageId: String?
    ): Patient?

    fun registerPatientConsent(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        patientEidCardNumber: String?,
        hubPackageId: String?
    )

    fun getPatientConsent(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        hubPackageId: String?
    ): Consent?

    fun registerTherapeuticLink(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        patientEidCardNumber: String?,
        hubPackageId: String?
    )

    fun getTherapeuticLinks(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        patientSsin: String,
        therLinkType: String?,
        from: Instant?,
        to: Instant?,
        hubPackageId: String?
    ): List<TherapeuticLinkMessage>

    fun getTransactionsList(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String,
        hubPackageId: String?,
        breakTheGlassReason: String? = null,
        from: Long?,
        to: Long?,
        authorNihii: String?,
        authorSsin: String?,
        isGlobal: Boolean
    ): List<TransactionSummary>

    fun putTransaction(
        endpoint: String,
        hubId: Long,
        hubApplication: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String,
        transaction: ByteArray,
        hubPackageId: String?
    ): TransactionIdType

    fun getTransaction(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String,
        hubPackageId: String?,
        breakTheGlassReason: String? = null,
        sv: String,
        sl: String,
        value: String
    ): Kmehrmessage?

    fun revokeTransaction(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String,
        hubPackageId: String?,
        breakTheGlassReason: String? = null,
        sv: String,
        sl: String,
        value: String
    ): String

    fun putTransactionSet(
        endpoint: String,
        hubId: Long,
        hubApplication: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String,
        transaction: ByteArray,
        hubPackageId: String?
    ): PutTransactionSetResponse

    fun getTransactionSet(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String,
        hubPackageId: String?,
        breakTheGlassReason: String? = null,
        sv: String,
        sl: String,
        value: String
    ): Kmehrmessage?
}
