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

import be.fgov.ehealth.hubservices.core.v3.*
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import org.taktik.connector.business.therlink.domain.TherapeuticLinkMessage
import org.taktik.freehealth.middleware.domain.consent.Consent
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.dto.hub.HcPartyConsentDto
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.domain.hub.PutTransactionResponse
import org.taktik.freehealth.middleware.dto.hub.TransactionSummaryDto
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
    ): HcPartyConsentDto?

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
        patientIsiCardNumber: String?,
        hubPackageId: String?
    ): PutPatientConsentResponse

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
        patientIsiCardNumber: String?,
        hubPackageId: String?
    ): PutTherapeuticLinkResponse

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
    ): TherapeuticLinkMessage

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
    ): List<TransactionSummaryDto>

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
    ): PutTransactionResponse

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

    fun getPatientAuditTrail(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        ssin: String?,
        breakTheGlassReason: String?,
        from: Long?,
        to: Long?,
        authorNihii: String?,
        authorSsin: String?,
        isGlobal: Boolean,
        sv: String?,
        sl: String?,
        value: String?,
        hubPackageId: String?
    ): GetPatientAuditTrailResponse

    fun putAccessRight(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        sv: String, //trn to manage
        sl: String, //trn to manage
        value: String, //trn to manage
        accessNihii: String?, //hcp to allow/disallow
        accessSsin: String?, //hcp to allow/disallow
        accessRight: String, //allow, disallow
        hubPackageId: String?
    ): PutAccessRightResponse

    fun getAccessRight(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        sv: String, //trn to manage
        sl: String, //trn to manage
        value: String, //trn to manage
        hubPackageId: String?
    ): GetAccessRightResponse

    fun revokeAccessRight(
        endpoint: String,
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpLastName: String,
        hcpFirstName: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpZip: String,
        sv: String, //trn to manage
        sl: String, //trn to manage
        value: String, //trn to manage
        accessNihii: String?, //hcp to allow/disallow
        accessSsin: String?, //hcp to allow/disallow
        hubPackageId: String?
    ): RevokeAccessRightResponse

    fun revokeTherapeuticLink(endpoint: String,
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
        patientIsiCardNumber: String?,
        hubPackageId: String?): RevokeTherapeuticLinkResponse

    fun revokePatientConsent(endpoint: String,
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
        patientIsiCardNumber: String?,
        hubPackageId: String?): RevokePatientConsentResponse
}
