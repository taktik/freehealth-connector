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
import org.taktik.freehealth.middleware.dto.memberdata.FacetDto
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.MemberDataService
import org.taktik.icure.cin.saml.extensions.Facet
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.UUID
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/mda")
class MemberDataController(val memberDataService: MemberDataService, val mapper: MapperFacade) {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MissingTokenException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: Exception): String = ex.message ?: "unknown reason"

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
        val startDate: Date = date?.let { Date(date) } ?: Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneId.of("Europe/Brussels").rules.getOffset(Instant.now())))
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
                                               endDate = endDate?.let { Date(it) } ?: startDate.let { Date(it.time + 86400000) },
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
        val startDate: Date = date?.let { Date(date) } ?: Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneId.of("Europe/Brussels").rules.getOffset(Instant.now())))
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
                                               endDate = endDate?.let { Date(it) } ?: startDate.let { Date(it.time + 86400000) },
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
        val startDate: Date = date?.let { Date(date) } ?: Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneId.of("Europe/Brussels").rules.getOffset(Instant.now())))
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
                                               endDate = endDate?.let { Date(it) } ?: startDate.let { Date(it.time + 86400000) },
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
        val startDate: Date = date?.let { Date(date) } ?: Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneId.of("Europe/Brussels").rules.getOffset(Instant.now())))
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
                                               endDate = endDate?.let { Date(it) } ?: startDate.let { Date(it.time + 86400000) },
                                               hospitalized = hospitalized ?: false)
    }
}
