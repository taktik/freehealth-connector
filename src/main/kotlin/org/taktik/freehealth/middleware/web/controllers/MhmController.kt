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

import be.fgov.ehealth.mycarenet.mhm.protocol.v1.SendSubscriptionResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.eattest.Eattest
import org.taktik.freehealth.middleware.dto.eattest.SendAttestResult
import org.taktik.freehealth.middleware.dto.mhm.CancelSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.dto.mhm.EndSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.dto.mhm.StartSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.EattestV2Service
import org.taktik.freehealth.middleware.service.MhmService
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.UUID
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/mhm")
class MhmController(val mhmService: MhmService) {
    @Value("\${mycarenet.timezone}")
    internal val mcnTimezone: String = "Europe/Brussels"

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MissingTokenException::class)
    @ResponseBody
    fun handleUnauthorizedRequest(req: HttpServletRequest, ex: Exception): String = ex.message ?: "unknown reason"

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: Exception): String = ex.message ?: "unknown reason"

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(javax.xml.ws.soap.SOAPFaultException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: javax.xml.ws.soap.SOAPFaultException): String = ex.message ?: "unknown reason"


    @PostMapping("/sendSubscription", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendSubscription(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpName: String,
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam(required = false) io: String,
        @RequestParam(required = false) ioMembership: String?,
        @RequestParam startDate: Int,
        @RequestParam isTrial: Boolean,
        @RequestParam signatureType: String,
        @RequestParam isRecovery: Boolean?,
        @RequestParam isTestForNotify: Boolean?
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
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam(required = false) io: String,
        @RequestParam(required = false) ioMembership: String?,
        @RequestParam reference: String
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
        @RequestParam(required = false) patientSsin: String?,
        @RequestParam patientFirstName: String,
        @RequestParam patientLastName: String,
        @RequestParam patientGender: String,
        @RequestParam(required = false) io: String,
        @RequestParam(required = false) ioMembership: String?,
        @RequestParam reference: String,
        @RequestParam endDate: Int,
        @RequestParam reason: String,
        @RequestParam decisionType : String
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
