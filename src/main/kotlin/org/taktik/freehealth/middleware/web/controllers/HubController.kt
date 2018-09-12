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

package org.taktik.freehealth.middleware.web.controllers

import be.fgov.ehealth.hubservices.core.v3.PutTransactionSetResponse
import be.fgov.ehealth.hubservices.core.v3.TransactionIdType
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.taktik.connector.business.therlink.domain.TherapeuticLink
import org.taktik.freehealth.middleware.domain.consent.Consent
import org.taktik.freehealth.middleware.domain.hub.TransactionSummary
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.service.HubService
import org.taktik.freehealth.utils.FuzzyValues
import java.time.Instant
import java.util.*

@RestController
@RequestMapping("/hub")
class HubController(val hubService: HubService) {

    @PostMapping("/patient/{lastName}/{patientSsin}")
    fun putPatient(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String? = null,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String,
        @RequestParam firstName: String,
        @PathVariable lastName: String,
        @RequestParam gender: Gender,
        @RequestParam dateOfBirth: Long
    ) = hubService.putPatient(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin,
        firstName = firstName,
        lastName = lastName,
        gender = gender,
        dateOfBirth = FuzzyValues.getLocalDateTime(dateOfBirth)!!
    )

    @GetMapping("/patient/{patientSsin}")
    fun getPatient(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String? = null,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String
    ) = hubService.getPatient(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin
    )

    @GetMapping("/hcpconsent/{hcpNihii}")
    fun getHcpConsent(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @PathVariable hcpNihii: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpZip: String,
        @RequestParam(required = false) hubPackageId: String? = null
    ) = hubService.getHcPartyConsent(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip
    )

    @PostMapping("/consent/{patientSsin}")
    fun registerPatientConsent(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String? = null,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String,
        @RequestParam(
                    required = false
                ) patientEidCardNumber: String?
    ) = hubService.registerPatientConsent(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin,
        patientEidCardNumber = patientEidCardNumber
    )

    @GetMapping("/consent/{patientSsin}")
    fun getPatientConsent(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String? = null,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String
    ): Consent? = hubService.getPatientConsent(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin
                                                                                              )

    @PostMapping("/therlink/{hcpNihii}/{patientSsin}")
    fun registerTherapeuticLink(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @PathVariable hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String? = null,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String,
        @RequestParam(
                    required = false
                ) patientEidCardNumber: String?
    ) = hubService.registerTherapeuticLink(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin,
        patientEidCardNumber = patientEidCardNumber
    )

    @GetMapping("/therlink/{hcpNihii}/{patientSsin}")
    fun getTherapeuticLinks(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @PathVariable hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String? = null,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String,
        @RequestParam(
                    required = false
                ) therLinkType: String?,
        @RequestParam(required = false) from: Instant?,
        @RequestParam(required = false) to: Instant?
    ): List<TherapeuticLink> = hubService.getTherapeuticLinks(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        patientSsin = patientSsin,
        therLinkType = therLinkType,
        from = from,
        to = to
    )

    @GetMapping("/list/{patientSsin}")
    fun getTransactionsList(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String? = null,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String,
        @RequestParam(
                    required = false
                ) from: Long? = null,
        @RequestParam(required = false) to: Long? = null,
        @RequestParam(required = false) authorNihii: String? = null,
        @RequestParam(
                    required = false
                ) authorSsin: String? = null,
        @RequestParam(required = false) isGlobal: Boolean = false,
        @RequestParam(required = false) breakTheGlassReason: String? = null
        ): List<TransactionSummary> {
        return hubService.getTransactionsList(
            endpoint = endpoint,
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hubPackageId = hubPackageId,
            hcpLastName = hcpLastName,
            hcpFirstName = hcpFirstName,
            hcpNihii = hcpNihii,
            hcpZip = hcpZip,
            hcpSsin = hcpSsin,
            ssin = patientSsin,
            from = from,
            to = to,
            authorNihii = authorNihii,
            authorSsin = authorSsin,
            isGlobal = isGlobal,
            breakTheGlassReason = breakTheGlassReason
        )
    }

    @GetMapping("/t/{ssin}/{sv}/{sl}", produces = [MediaType.APPLICATION_XML_VALUE])
    fun getTransaction(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String? = null,
        @RequestParam hcpZip: String,
        @RequestParam(required = false) breakTheGlassReason: String? = null,
        @PathVariable ssin: String,
        @PathVariable sv: String,
        @PathVariable sl: String,
        @RequestParam id: String
    ): String {
        return hubService.getTransaction(
            endpoint = endpoint,
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hubPackageId = hubPackageId,
            hcpLastName = hcpLastName,
            hcpFirstName = hcpFirstName,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpZip = hcpZip,
            ssin = ssin,
            breakTheGlassReason = breakTheGlassReason,
            sv = sv,
            sl = sl,
            value = id
        )
    }

    @DeleteMapping("/t/{ssin}/{sv}/{sl}")
    fun revokeTransaction(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String? = null,
        @RequestParam hcpZip: String,
        @RequestParam(required = false) breakTheGlassReason: String? = null,
        @PathVariable ssin: String,
        @PathVariable sv: String,
        @PathVariable sl: String,
        @RequestParam id: String
    ): String {
        return hubService.revokeTransaction(
            endpoint = endpoint,
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hubPackageId = hubPackageId,
            hcpLastName = hcpLastName,
            hcpFirstName = hcpFirstName,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpZip = hcpZip,
            ssin = ssin,
            breakTheGlassReason = breakTheGlassReason,
            sv = sv,
            sl = sl,
            value = id
        )
    }

    @PostMapping("/t/{hubId}/{patientSsin}", consumes = [MediaType.APPLICATION_XML_VALUE])
    fun putTransaction(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String? = null,
        @RequestParam hcpZip: String,
        @PathVariable hubId: Long,
        @RequestParam(
                    required = false
                ) hubApplication: String?,
        @PathVariable patientSsin: String,
        @RequestBody message: ByteArray
    ): TransactionIdType {
        return hubService.putTransaction(
            endpoint = endpoint,
            hubId = hubId,
            hubApplication = hubApplication ?: "",
            keystoreId = keystoreId,
            hubPackageId = hubPackageId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpLastName = hcpLastName,
            hcpFirstName = hcpFirstName,
            hcpNihii = hcpNihii,
            hcpSsin = hcpSsin,
            hcpZip = hcpZip,
            ssin = patientSsin,
            transaction = message
        )
    }

    @GetMapping("/ts/{ssin}/{sv}/{sl}", produces = [MediaType.APPLICATION_XML_VALUE])
    fun getTransactionSet(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String? = null,
        @RequestParam hcpZip: String,
        @RequestParam(required = false) breakTheGlassReason: String? = null,
        @PathVariable ssin: String,
        @PathVariable sv: String,
        @PathVariable sl: String,
        @RequestParam id: String
    ): String = hubService.getTransactionSet(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hubPackageId = hubPackageId,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        ssin = ssin,
        breakTheGlassReason = breakTheGlassReason,
        sv = sv,
        sl = sl,
        value = id
    )

    @PostMapping("/ts/{hubId}/{patientSsin}")
    fun putTransactionSet(
        @RequestParam endpoint: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String? = null,
        @RequestParam hcpZip: String,
        @PathVariable hubId: Long,
        @RequestParam(
                    required = false
                ) hubApplication: String?,
        @PathVariable patientSsin: String,
        @RequestBody message: String
    ): PutTransactionSetResponse = hubService.putTransactionSet(
        endpoint = endpoint,
        hubId = hubId,
        hubApplication = hubApplication ?: "",
        keystoreId = keystoreId,
        hubPackageId = hubPackageId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        ssin = patientSsin,
        transaction = message
    )
}
