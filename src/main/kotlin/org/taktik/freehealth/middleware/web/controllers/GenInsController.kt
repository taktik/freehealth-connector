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

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.genins.InsurabilityInfoDto
import org.taktik.freehealth.middleware.service.GenInsService
import java.util.*

@RestController
@RequestMapping("/genins")
class GenInsController(val genInsService: GenInsService) {
    @GetMapping("/{ssin}")
    fun getGeneralInsurability(@PathVariable ssin: String, @RequestParam tokenId: UUID, @RequestParam keystoreId: UUID, @RequestParam passPhrase: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestParam(required = false) hcpQuality: String = "doctor", @RequestParam(required = false) date: Long?, @RequestParam(required = false) hospitalized: Boolean?): InsurabilityInfoDto =
            genInsService.getGeneralInsurabity(
                    keystoreId = keystoreId,
                    tokenId = tokenId,
                    hcpQuality = hcpQuality,
                    hcpNihii = hcpNihii,
                    hcpSsin = hcpSsin,
                    hcpName = hcpName,
                    passPhrase = passPhrase,
                    patientSsin = ssin,
                    io = null,
                    ioMembership = null,
                    date = date?.let { Date(date) } ?: Date(),
                    hospitalized = hospitalized ?: false
            )

    @GetMapping("/{io}/{ioMembership}")
    fun getGeneralInsurabilityByMembership(@PathVariable io: String, @PathVariable ioMembership: String, @RequestParam tokenId: UUID, @RequestParam keystoreId: UUID, @RequestParam passPhrase: String, @RequestParam hcpNihii: String, @RequestParam hcpSsin: String, @RequestParam hcpName: String, @RequestParam(required = false) hcpQuality: String = "doctor", @RequestParam(required = false) date: Long?, @RequestParam(required = false) hospitalized: Boolean?): InsurabilityInfoDto {
        return genInsService.getGeneralInsurabity(
                keystoreId = keystoreId,
                tokenId = tokenId,
                hcpQuality = hcpQuality,
                hcpNihii = hcpNihii,
                hcpSsin = hcpSsin,
                hcpName = hcpName,
                passPhrase = passPhrase,
                patientSsin = null,
                io = io,
                ioMembership = ioMembership,
                date = date?.let { Date(date) } ?: Date(),
                hospitalized = hospitalized ?: false
        )
    }
}