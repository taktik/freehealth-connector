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

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.genins.InsurabilityInfoDto
import org.taktik.freehealth.middleware.service.GenInsService
import java.time.Instant
import java.util.*

@RestController
@RequestMapping("/genins")
class GenInsController(val genInsService: GenInsService) {
    @GetMapping("/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getGeneralInsurability(
        @PathVariable ssin: String,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpName: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) endDate: Long?,
        @RequestParam(required = false) hospitalized: Boolean?
    ): InsurabilityInfoDto {
        val startDate: Instant = date?.let { Instant.ofEpochMilli(it) } ?: Instant.now()
        return genInsService.getGeneralInsurabity(keystoreId = keystoreId,
                                                  tokenId = tokenId,
                                                  hcpQuality = hcpQuality ?: "doctor",
                                                  hcpNihii = hcpNihii,
                                                  hcpSsin = hcpSsin,
                                                  hcpName = hcpName,
                                                  passPhrase = passPhrase,
                                                  patientSsin = ssin,
                                                  io = null,
                                                  ioMembership = null,
                                                  startDate = startDate,
                                                  endDate = endDate?.let { Instant.ofEpochMilli(it) } ?: startDate,
                                                  hospitalized = hospitalized ?: false)
    }

    @GetMapping("/{io}/{ioMembership}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getGeneralInsurabilityByMembership(
        @PathVariable io: String,
        @PathVariable ioMembership: String,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpName: String,
        @RequestParam(required = false) hcpQuality: String?,
        @RequestParam(required = false) date: Long?,
        @RequestParam(required = false) endDate: Long?,
        @RequestParam(required = false) hospitalized: Boolean?
    ): InsurabilityInfoDto {
        val startDate: Instant = date?.let { Instant.ofEpochMilli(it) } ?: Instant.now()
        return genInsService.getGeneralInsurabity(keystoreId = keystoreId,
                                                  tokenId = tokenId,
                                                  hcpQuality = hcpQuality ?: "doctor",
                                                  hcpNihii = hcpNihii,
                                                  hcpSsin = hcpSsin,
                                                  hcpName = hcpName,
                                                  passPhrase = passPhrase,
                                                  patientSsin = null,
                                                  io = io,
                                                  ioMembership = ioMembership,
                                                  startDate = startDate,
                                                  endDate = endDate?.let { Instant.ofEpochMilli(it) } ?: startDate,
                                                  hospitalized = hospitalized ?: false)
    }
}
