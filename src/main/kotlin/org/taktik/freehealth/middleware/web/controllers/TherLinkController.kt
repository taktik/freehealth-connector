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
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkDto
import org.taktik.freehealth.middleware.dto.therlink.TherapeuticLinkMessageDto
import org.taktik.freehealth.middleware.service.TherLinkService
import java.util.*

@RestController
@RequestMapping("/therlink")
class TherLinkController(val therLinkService: TherLinkService, val mapper: MapperFacade) {

    @GetMapping("/{patientSsin}/{hcpNihii}")
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
        @RequestParam(
                    required = false
                ) eidCardNumber: String? = null,
        @RequestParam(required = false) isiCardNumber: String? = null,
        @RequestParam(
                    required = false
                ) startDate: Date? = null,
        @RequestParam(required = false) endDate: Date? = null,
        @RequestParam(required = false) type: String? = null,
        @RequestParam(
                    required = false
                ) sign: Boolean? = null
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
    )?.map { mapper.map(it, TherapeuticLinkMessageDto::class.java) }

    @PostMapping("/query")
    fun getAllTherapeuticLinksWithQueryLink(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody queryLink: TherapeuticLinkDto,
        @RequestParam(
                    required = false
                ) sign: Boolean? = null
    ) = therLinkService.getAllTherapeuticLinksWithQueryLink(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        queryLink = mapper.map(queryLink, org.taktik.connector.business.therlink.domain.TherapeuticLink::class.java),
        sign = sign
    )?.map { mapper.map(it, TherapeuticLinkMessageDto::class.java) }

    @PostMapping("/check")
    fun doesLinkExist(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestBody therLink: TherapeuticLinkDto) =
        therLinkService.doesLinkExist(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            therLink = mapper.map(therLink, org.taktik.connector.business.therlink.domain.TherapeuticLink::class.java)
        )?.let { mapper.map(it, TherapeuticLinkDto::class.java) }

    @PostMapping("/register")
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
        @RequestParam(
                    required = false
                ) eidCardNumber: String? = null,
        @RequestParam(required = false) isiCardNumber: String? = null,
        @RequestParam(
                    required = false
                ) start: Date? = null,
        @RequestParam(required = false) end: Date? = null,
        @RequestParam(required = false) therLinkType: String? = null,
        @RequestParam(
                    required = false
                ) comment: String? = null,
        @RequestParam(required = false) sign: Boolean? = null
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
        sign = sign
    ).let { mapper.map(it, TherapeuticLinkMessageDto::class.java) }

    @PostMapping("/revoke/{patientSsin}/{hcpNihii}")
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
        @RequestParam(
                    required = false
                ) eidCardNumber: String? = null,
        @RequestParam(required = false) isiCardNumber: String? = null,
        @RequestParam(
                    required = false
                ) start: Date? = null,
        @RequestParam(required = false) end: Date?,
        @RequestParam(required = false) therLinkType: String? = null,
        @RequestParam(
                    required = false
                ) comment: String? = null,
        @RequestParam(required = false) sign: Boolean? = null
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
        sign = sign
    ).let { mapper.map(it, TherapeuticLinkMessageDto::class.java) }

    @PostMapping("/revoke")
    fun revokeLink(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody therLink: TherapeuticLinkDto,
        @RequestParam(
                    required = false
                ) sign: Boolean? = null
    ) = therLinkService.revokeLink(
        keystoreId = keystoreId,
        tokenId = tokenId,
        passPhrase = passPhrase,
        therLink = mapper.map(therLink, org.taktik.connector.business.therlink.domain.TherapeuticLink::class.java),
        sign = sign
    ).let { mapper.map(it, TherapeuticLinkMessageDto::class.java) }
}
