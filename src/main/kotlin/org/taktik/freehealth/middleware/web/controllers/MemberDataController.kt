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
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse
import org.taktik.freehealth.middleware.dto.memberdata.FacetDto
import org.taktik.freehealth.middleware.service.MemberDataService
import org.taktik.icure.cin.saml.extensions.Facet
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.*

@RestController
@RequestMapping("/mda")
class MemberDataController(val memberDataService: MemberDataService, val mapper: MapperFacade) {
    @Value("\${mycarenet.timezone}")
    internal val mcnTimezone: String = "Europe/Brussels"

    @PostMapping("/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun queryMemberData(
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
        @RequestParam(required = false) hospitalized: Boolean?,
        @RequestBody facets:List<FacetDto>
                     ) : MemberDataResponse {
        val startDate: Instant = date?.let { Instant.ofEpochMilli(it) } ?: LocalDate.now().atStartOfDay(ZoneId.of(mcnTimezone)).toInstant()
        return memberDataService.getMemberData(keystoreId = keystoreId,
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
                                               endDate = endDate?.let { Instant.ofEpochMilli(it) } ?: ZonedDateTime.ofInstant(startDate, ZoneId.of(mcnTimezone)).truncatedTo(ChronoUnit.DAYS).plusDays(1).toInstant(),
                                               facets = facets.map { mapper.map(it, Facet::class.java) })
    }

    @GetMapping("/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMemberData(
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
    ) : MemberDataResponse {
        val startDate: Instant = date?.let { Instant.ofEpochMilli(it) } ?: LocalDate.now().atStartOfDay(ZoneId.of(mcnTimezone)).toInstant()
        return memberDataService.getMemberData(keystoreId = keystoreId,
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
                                               endDate = endDate?.let { Instant.ofEpochMilli(it) } ?: ZonedDateTime.ofInstant(startDate, ZoneId.of(mcnTimezone)).truncatedTo(ChronoUnit.DAYS).plusDays(1).toInstant(),
                                               hospitalized = hospitalized ?: false)
    }

    @PostMapping("/{io}/{ioMembership}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun queryMemberDataByMembership(
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
        @RequestParam(required = false) hospitalized: Boolean?,
        @RequestBody facets:List<FacetDto>
                       ) : MemberDataResponse {
        val startDate: Instant = date?.let { Instant.ofEpochMilli(it) } ?: LocalDate.now().atStartOfDay(ZoneId.of(mcnTimezone)).toInstant()
        return memberDataService.getMemberData(keystoreId = keystoreId,
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
                                               endDate = endDate?.let { Instant.ofEpochMilli(it) } ?: ZonedDateTime.ofInstant(startDate, ZoneId.of(mcnTimezone)).truncatedTo(ChronoUnit.DAYS).plusDays(1).toInstant(),
                                               facets = facets.map { mapper.map(it, Facet::class.java) })
    }

    @GetMapping("/{io}/{ioMembership}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getMemberDataByMembership(
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
    ): MemberDataResponse {
        val startDate: Instant = date?.let { Instant.ofEpochMilli(it) } ?: LocalDate.now().atStartOfDay(ZoneId.of(mcnTimezone)).toInstant()
        return memberDataService.getMemberData(keystoreId = keystoreId,
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
                                               endDate = endDate?.let { Instant.ofEpochMilli(it) } ?: ZonedDateTime.ofInstant(startDate, ZoneId.of(mcnTimezone)).truncatedTo(ChronoUnit.DAYS).plusDays(1).toInstant(),
                                               hospitalized = hospitalized ?: false)
    }
}
