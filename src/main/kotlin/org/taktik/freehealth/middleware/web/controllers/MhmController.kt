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
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.mhm.CancelSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.dto.mhm.EndSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.dto.mhm.StartSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.service.MhmService
import java.util.UUID

@RestController
@RequestMapping("/mhm")
class MhmController(val mhmService: MhmService) {
    @PostMapping("/sendSubscription", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendSubscription(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam startDate: Int,
        @RequestParam isTrial: Boolean,
        @RequestParam signatureType: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) io: String?,
        @RequestParam(required = false) ioMembership: String?,
        @RequestParam(required = false) isRecovery: Boolean?,
        @RequestParam(required = false) isTestForNotify: Boolean?
    ) : StartSubscriptionResultWithResponse? {
        return mhmService.sendSubscription(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpNihii = hcpNihii,
            hcpName = hcpName,
            patientSsin = patientSsin,
            patientFirstName = patientFirstName,
            patientLastName = patientLastName,
            patientGender = patientGender,
            io = io,
            ioMembership = ioMembership,
            startDate = startDate,
            isTrial = isTrial,
            signatureType = signatureType,
            isRecovery = isRecovery ?: false,
            isTestForNotify = isTestForNotify ?: false)
    }

    @PostMapping("/cancelSubscription", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun cancelSubscription(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam reference: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) io: String?,
        @RequestParam(required = false) ioMembership: String?
    ) : CancelSubscriptionResultWithResponse? {
      return mhmService.cancelSubscription(
          keystoreId = keystoreId,
          tokenId = tokenId,
          passPhrase = passPhrase,
          hcpNihii = hcpNihii,
          hcpName = hcpName,
          patientSsin = patientSsin,
          patientFirstName = patientFirstName,
          patientLastName = patientLastName,
          patientGender = patientGender,
          io = io,
          ioMembership = ioMembership,
          reference = reference
      )
    }

    @PostMapping("/notifySubscriptionClosure", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun notifySubscriptionClosure(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam reference: String,
        @RequestParam endDate: Int,
        @RequestParam reason: String,
        @RequestParam decisionType : String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam(required = false) io: String?,
        @RequestParam(required = false) ioMembership: String?
    ): EndSubscriptionResultWithResponse? {
      return mhmService.notifySubscriptionClosure(
          keystoreId = keystoreId,
          tokenId = tokenId,
          passPhrase = passPhrase,
          hcpNihii = hcpNihii,
          hcpName = hcpName,
          patientSsin = patientSsin,
          patientFirstName = patientFirstName,
          patientLastName = patientLastName,
          patientGender = patientGender,
          io = io,
          ioMembership = ioMembership,
          reference = reference,
          endDate = endDate,
          reason = reason,
          decisionType = decisionType
      )
    }
}
