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
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.eattest.Eattest
import org.taktik.freehealth.middleware.dto.eattest.SendAttestResult
import org.taktik.freehealth.middleware.service.EattestV2Service
import org.taktik.freehealth.middleware.service.MhmService
import java.util.UUID

@RestController
@RequestMapping("/mhm")
class MhmController(val mhmService: MhmService) {
    @PostMapping("/send/{patientSsin}/verbose", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendAttestWithResponse(
        @PathVariable patientSsin: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam hcpCbe: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam io: String,
        @RequestParam(required = false) ioMembership: String?,
        @RequestParam startDate: Int,
        @RequestParam isTrial: Boolean,
        @RequestParam signatureType: String
    ) = mhmService.startSubscription(
        keystoreId,
        tokenId,
        passPhrase,
        hcpNihii,
        hcpName,
        hcpCbe,
        patientSsin,
        patientFirstName,
        patientLastName,
        patientGender,
        io,
        ioMembership, startDate, isTrial, signatureType)

    @PostMapping("/send/{io}/{ioMembership}/verbose", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendAttestWithResponse(
        @PathVariable io: String,
        @PathVariable ioMembership: String,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam hcpCbe: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam startDate: Int,
        @RequestParam isTrial: Boolean,
        @RequestParam signatureType: String
                              ) = mhmService.startSubscription(
        keystoreId,
        tokenId,
        passPhrase,
        hcpNihii,
        hcpName,
        hcpCbe,
        patientSsin,
        patientFirstName,
        patientLastName,
        patientGender,
        io,
        ioMembership, startDate, isTrial, signatureType)

}
