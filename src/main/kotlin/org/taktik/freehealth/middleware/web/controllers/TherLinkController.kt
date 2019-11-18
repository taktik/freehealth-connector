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

import ma.glasnost.orika.MapperFacade
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.taktik.connector.business.therlink.domain.HasTherapeuticLinkMessage
import org.taktik.connector.business.therlink.domain.ProofTypeValues
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkDto
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkMessageDto
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.TherLinkService
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/therlink")
class TherLinkController(val therLinkService: TherLinkService, val mapper: MapperFacade) {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MissingTokenException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: Exception): String = ex.message ?: "unknown reason"

    @GetMapping("/check/{patientSsin}/{hcpNihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun hasTherapeuticLink(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @PathVariable patientSsin: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam(required = false) eidCardNumber: String?,
        @RequestParam(required = false) isiCardNumber: String?,
        @RequestParam(required = false) startDate: Date?,
        @RequestParam(required = false) endDate: Date?,
        @RequestParam(required = false) type: String?
    ) = therLinkService.hasTherapeuticLink(
    keystoreId = keystoreId,
    tokenId = tokenId,
    passPhrase = passPhrase,
    hcpNihii = hcpNihii,
    hcpSsin = hcpSsin,
    hcpFirstName = hcpFirstName,
    hcpLastName = hcpLastName,
    patientSsin = patientSsin,
    patientFirstName = patientFirstName,
    patientLastName = patientLastName,
    eidCardNumber = eidCardNumber,
    isiCardNumber = isiCardNumber,
    startDate = startDate,
    endDate = endDate,
    therLinkType = type
    )

    @GetMapping("/{patientSsin}/{hcpNihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getAllTherapeuticLinks(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @PathVariable patientSsin: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam(required = false) eidCardNumber: String?,
        @RequestParam(required = false) isiCardNumber: String?,
        @RequestParam(required = false) startDate: Date?,
        @RequestParam(required = false) endDate: Date?,
        @RequestParam(required = false) type: String?,
        @RequestParam(required = false) sign: Boolean?
    ) = therLinkService.getAllTherapeuticLinks(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpFirstName = hcpFirstName,
        hcpLastName = hcpLastName,
        patientSsin = patientSsin,
        patientFirstName = patientFirstName,
        patientLastName = patientLastName,
        eidCardNumber = eidCardNumber,
        isiCardNumber = isiCardNumber,
        startDate = startDate,
        endDate = endDate,
        type = type,
        sign = sign
    )?.let { mapper.map(it, TherapeuticLinkMessageDto::class.java) }

    @PostMapping("/query", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getAllTherapeuticLinksWithQueryLink(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody queryLink: TherapeuticLinkDto,
        @RequestParam(required = false) sign: Boolean?
    ) = therLinkService.getAllTherapeuticLinksWithQueryLink(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        queryLink = mapper.map(queryLink, org.taktik.connector.business.therlink.domain.TherapeuticLink::class.java),
        sign = sign
    )?.let { mapper.map(it, TherapeuticLinkMessageDto::class.java) }

    @PostMapping("/check", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun doesLinkExist(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestBody therLink: TherapeuticLinkDto) =
        therLinkService.doesLinkExist(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            therLink = mapper.map(therLink, org.taktik.connector.business.therlink.domain.TherapeuticLink::class.java)
        )?.let { mapper.map(it, TherapeuticLinkDto::class.java) }

    @PostMapping("/register", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun registerTherapeuticLink(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam patientSsin: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam(required = false) eidCardNumber: String?,
        @RequestParam(required = false) isiCardNumber: String?,
        @RequestParam(required = false) start: Date?,
        @RequestParam(required = false) end: Date?,
        @RequestParam(required = false) therLinkType: String?,
        @RequestParam(required = false) comment: String?,
        @RequestParam(required = false) sign: Boolean?,
        @RequestParam(required = false) proofType: String?
                               ) = therLinkService.registerTherapeuticLink(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpFirstName = hcpFirstName,
        hcpLastName = hcpLastName,
        patientSsin = patientSsin,
        patientFirstName = patientFirstName,
        patientLastName = patientLastName,
        eidCardNumber = eidCardNumber,
        isiCardNumber = isiCardNumber,
        start = start,
        end = end,
        therLinkType = therLinkType,
        comment = comment,
        sign = sign,
        proofType = proofType?.let { ProofTypeValues.valueOf(it)}
    ).let { mapper.map(it, TherapeuticLinkMessageDto::class.java) }

    @PostMapping("/revoke/{patientSsin}/{hcpNihii}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun revokeLink(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @PathVariable patientSsin: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam(required = false) eidCardNumber: String?,
        @RequestParam(required = false) isiCardNumber: String?,
        @RequestParam(required = false) start: Date?,
        @RequestParam(required = false) end: Date?,
        @RequestParam(required = false) therLinkType: String?,
        @RequestParam(required = false) comment: String?,
        @RequestParam(required = false) sign: Boolean?,
        @RequestParam(required = false) proofType: String?
    ) = therLinkService.revokeLink(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        hcpFirstName = hcpFirstName,
        hcpLastName = hcpLastName,
        patientSsin = patientSsin,
        patientFirstName = patientFirstName,
        patientLastName = patientLastName,
        eidCardNumber = eidCardNumber,
        isiCardNumber = isiCardNumber,
        start = start,
        end = end,
        therLinkType = therLinkType,
        comment = comment,
        sign = sign,
        proofType = proofType?.let { ProofTypeValues.valueOf(it)}
    ).let { mapper.map(it, TherapeuticLinkMessageDto::class.java) }

    @PostMapping("/revoke", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun revokeLink(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody therLink: TherapeuticLinkDto,
        @RequestParam(required = false) sign: Boolean?,
        @RequestParam(required = false) proofType: String?
    ) = therLinkService.revokeLink(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        therLink = mapper.map(therLink, org.taktik.connector.business.therlink.domain.TherapeuticLink::class.java),
        sign = sign,
        proofType = proofType?.let { ProofTypeValues.valueOf(it)}
    ).let { mapper.map(it, TherapeuticLinkMessageDto::class.java) }
}
