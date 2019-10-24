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

import be.fgov.ehealth.hubservices.core.v3.GetAccessRightResponse
import be.fgov.ehealth.hubservices.core.v3.GetPatientAuditTrailResponse
import be.fgov.ehealth.hubservices.core.v3.PutAccessRightResponse
import be.fgov.ehealth.hubservices.core.v3.PutTransactionSetResponse
import be.fgov.ehealth.hubservices.core.v3.RevokeAccessRightResponse
import be.fgov.ehealth.standards.kmehr.schema.v1.Kmehrmessage
import ma.glasnost.orika.MapperFacade
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.taktik.connector.technical.utils.MarshallerHelper
import org.taktik.freehealth.middleware.domain.consent.Consent
import org.taktik.freehealth.middleware.dto.hub.TransactionSummaryDto
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.dto.hub.PutTransactionResponseDto
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkMessageDto
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.HubService
import org.taktik.freehealth.utils.FuzzyValues
import java.time.Instant
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/hub")
class HubController(val hubService: HubService, val mapper: MapperFacade) {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MissingTokenException::class)
    @ResponseBody fun handleBadRequest(req: HttpServletRequest, ex: Exception): String = ex.message ?: "unknown reason"

    @PostMapping("/patient/{lastName}/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun putPatient(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
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

    @GetMapping("/patient/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPatient(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
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

    @GetMapping("/hcpconsent/{hcpNihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getHcpConsent(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable hcpNihii: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpZip: String,
        @RequestParam(required = false) hubPackageId: String?
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

    @PostMapping("/consent/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun registerPatientConsent(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String,
        @RequestParam(required = false) patientEidCardNumber: String?,
        @RequestParam(required = false) patientIsiCardNumber: String?
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
        patientEidCardNumber = patientEidCardNumber,
        patientIsiCardNumber = patientIsiCardNumber

                                                                   )

    @DeleteMapping("/consent/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun revokePatientConsent(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String,
        @RequestParam(required = false) patientEidCardNumber: String?,
        @RequestParam(required = false) patientIsiCardNumber: String?
                              ) = hubService.revokePatientConsent(
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
        patientEidCardNumber = patientEidCardNumber,
        patientIsiCardNumber = patientIsiCardNumber
                                                                   )

    @GetMapping("/consent/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPatientConsent(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
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

    @PostMapping("/therlink/{hcpNihii}/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun registerTherapeuticLink(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @PathVariable hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String,
        @RequestParam(required = false) patientEidCardNumber: String?,
        @RequestParam(required = false) patientIsiCardNumber: String?
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
        patientEidCardNumber = patientEidCardNumber,
        patientIsiCardNumber = patientIsiCardNumber
    )

    @DeleteMapping("/therlink/{hcpNihii}/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun revokeTherapeuticLink(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @PathVariable hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String,
        @RequestParam(required = false) patientEidCardNumber: String?,
        @RequestParam(required = false) patientIsiCardNumber: String?
                             ) = hubService.revokeTherapeuticLink(
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
        patientEidCardNumber = patientEidCardNumber,
        patientIsiCardNumber = patientIsiCardNumber
                                                                 )

    @GetMapping("/therlink/{hcpNihii}/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getTherapeuticLinks(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @PathVariable hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String,
        @RequestParam(required = false) therLinkType: String?,
        @RequestParam(required = false) from: Instant?,
        @RequestParam(required = false) to: Instant?
    ): TherapeuticLinkMessageDto = hubService.getTherapeuticLinks(
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
    )?.let {
        mapper.map(it, TherapeuticLinkMessageDto::class.java)
    }


    @GetMapping("/list/{patientSsin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getTransactionsList(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @PathVariable patientSsin: String,
        @RequestParam(required = false) from: Long?,
        @RequestParam(required = false) to: Long?,
        @RequestParam(required = false) authorNihii: String?,
        @RequestParam(required = false) authorSsin: String?,
        @RequestParam(required = false) isGlobal: Boolean?,
        @RequestParam(required = false) breakTheGlassReason: String?
    ): List<TransactionSummaryDto> {
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
            isGlobal = isGlobal ?: false,
            breakTheGlassReason = breakTheGlassReason
        )
    }

    @GetMapping("/t/{ssin}/{sv}/{sl}", produces = [MediaType.APPLICATION_XML_VALUE])
    fun getTransaction(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @RequestParam(required = false) breakTheGlassReason: String?,
        @PathVariable ssin: String,
        @PathVariable sv: String,
        @PathVariable sl: String,
        @RequestParam id: String
    ): Kmehrmessage? {
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

    @GetMapping("/t/{ssin}/{sv}/{sl}/kmehr", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getTransactionMessage(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @RequestParam(required = false) breakTheGlassReason: String?,
        @PathVariable ssin: String,
        @PathVariable sv: String,
        @PathVariable sl: String,
        @RequestParam id: String
    ): Kmehrmessage? {
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
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @RequestParam(required = false) breakTheGlassReason: String?,
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

    @PostMapping("/t/{hubId}/{patientSsin}", consumes = [MediaType.APPLICATION_XML_VALUE], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun putTransaction(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @PathVariable hubId: Long,
        @RequestParam(required = false) hubApplication: String?,
        @PathVariable patientSsin: String,
        @RequestBody message: ByteArray
    ): PutTransactionResponseDto {
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
        ).let { mapper.map(it, PutTransactionResponseDto::class.java) }
    }

    @GetMapping("/ts/{ssin}/{sv}/{sl}", produces = [MediaType.APPLICATION_XML_VALUE])
    fun getTransactionSet(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @RequestParam(required = false) breakTheGlassReason: String?,
        @PathVariable ssin: String,
        @PathVariable sv: String,
        @PathVariable sl: String,
        @RequestParam id: String
    ): Kmehrmessage? = hubService.getTransactionSet(
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

    @GetMapping("/ts/{ssin}/{sv}/{sl}/kmehr", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getTransactionSetMessage(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @RequestParam(required = false) breakTheGlassReason: String?,
        @PathVariable ssin: String,
        @PathVariable sv: String,
        @PathVariable sl: String,
        @RequestParam id: String
    ): Kmehrmessage? = hubService.getTransactionSet(
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

    @PostMapping("/ts/{hubId}/{patientSsin}", consumes = [MediaType.APPLICATION_XML_VALUE], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun putTransactionSet(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @PathVariable hubId: Long,
        @RequestParam(required = false) hubApplication: String?,
        @PathVariable patientSsin: String,
        @RequestBody message: ByteArray
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

    @GetMapping("/trail", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPatientAuditTrail(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) hubPackageId: String?,
        @RequestParam hcpZip: String,
        @RequestParam(required = false) from: Long?,
        @RequestParam(required = false) to: Long?,
        @RequestParam(required = false) authorNihii: String?,
        @RequestParam(required = false) authorSsin: String?,
        @RequestParam(required = false) isGlobal: Boolean?,
        @RequestParam(required = false) breakTheGlassReason: String?,
        @RequestParam(required = false) ssin: String?,
        @RequestParam(required = false) sv: String?,
        @RequestParam(required = false) sl: String?,
        @RequestParam(required = false) id: String?
    ): GetPatientAuditTrailResponse = hubService.getPatientAuditTrail(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        ssin = ssin,
        breakTheGlassReason = breakTheGlassReason,
        from = from,
        to = to,
        authorNihii = authorNihii,
        authorSsin = authorSsin,
        isGlobal = isGlobal ?: false,
        sv = sv,
        sl = sl,
        value = id,
        hubPackageId = hubPackageId
                                                                     )

    @PostMapping("/access", consumes = [MediaType.APPLICATION_XML_VALUE], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun putAccessRight(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpZip: String,
        @RequestParam sv: String, //trn to manage
        @RequestParam sl: String, //trn to manage
        @RequestParam value: String, //trn to manage
        @RequestParam (required = false) accessNihii: String?, //hcp to allow/disallow
        @RequestParam (required = false) accessSsin: String?, //hcp to allow/disallow
        @RequestParam accessRight: String, //allow, disallow
        @RequestParam (required = false) hubPackageId: String?
    ): PutAccessRightResponse = hubService.putAccessRight(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        sv = sv,
        sl = sl,
        value = value,
        accessNihii = accessNihii,
        accessRight = accessRight,
        accessSsin = accessSsin,
        hubPackageId = hubPackageId
                                                         )

    @GetMapping("/access", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getAccessRight(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId")keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase")passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpZip: String,
        @RequestParam sv: String, //trn to manage
        @RequestParam sl: String, //trn to manage
        @RequestParam value: String, //trn to manage
        @RequestParam (required = false) hubPackageId: String?
    ): GetAccessRightResponse = hubService.getAccessRight(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        sv = sv,
        sl = sl,
        value = value,
        hubPackageId = hubPackageId
                                                         )

    @DeleteMapping("/access")
    fun revokeAccessRight(
        @RequestParam endpoint: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpZip: String,
        @RequestParam sv: String, //trn to manage
        @RequestParam sl: String, //trn to manage
        @RequestParam value: String, //trn to manage
        @RequestParam (required = false) accessNihii: String?, //hcp to allow/disallow
        @RequestParam (required = false) accessSsin: String?, //hcp to allow/disallow
        @RequestParam (required = false) hubPackageId: String?
    ): RevokeAccessRightResponse = hubService.revokeAccessRight(
        endpoint = endpoint,
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpLastName = hcpLastName,
        hcpFirstName = hcpFirstName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpZip = hcpZip,
        sv = sv,
        sl = sl,
        value = value,
        accessNihii = accessNihii,
        accessSsin = accessSsin,
        hubPackageId = hubPackageId
                                                               )


    @PostMapping("/convertKmehrXMLtoJSON", consumes = [MediaType.APPLICATION_XML_VALUE], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun convertKmehrXMLtoJSON(@RequestBody message: ByteArray): Kmehrmessage {
        return MarshallerHelper(Kmehrmessage::class.java, Kmehrmessage::class.java).toObject(message)
    }

}
