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

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.eattest.Eattest
import org.taktik.freehealth.middleware.dto.eattest.SendAttestResult
import org.taktik.freehealth.middleware.service.EattestService
import java.util.*

@RestController
@RequestMapping("/eattest")
class EattestController(val eattestService: EattestService) {
    @PostMapping("/send/{patientSsin}/verbose")
    fun sendAttestWithResponse(
        @PathVariable patientSsin: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpCbe: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam(
                    required = false
                ) date: Long?,
        @RequestBody attest: Eattest
    ) = eattestService.sendAttest(
        keystoreId,
        tokenId,
        hcpNihii,
        hcpSsin,
        hcpFirstName,
        hcpLastName,
        hcpCbe,
        passPhrase,
        patientSsin,
        patientFirstName,
        patientLastName,
        patientGender,
        null,
        attest
    )

    @PostMapping("/send/{patientSsin}")
    fun sendAttest(
        @PathVariable patientSsin: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpCbe: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam(
                    required = false
                ) date: Long?,
        @RequestBody attest: Eattest
    ): SendAttestResult? = eattestService.sendAttest(
        keystoreId,
        tokenId,
        hcpNihii,
        hcpSsin,
        hcpFirstName,
        hcpLastName,
        hcpCbe,
        passPhrase,
        patientSsin,
        patientFirstName,
        patientLastName,
        patientGender,
        null,
        attest
    )?.let { SendAttestResult(it.acknowledge, it.invoicingNumber, it.attest) }
}
