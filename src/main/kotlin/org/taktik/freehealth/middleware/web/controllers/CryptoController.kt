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

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
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
import org.springframework.web.multipart.MultipartFile
import org.taktik.connector.business.ehbox.api.domain.Addressee
import org.taktik.connector.technical.utils.IdentifierType
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.CryptoService
import java.util.UUID
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/crypto")
class CryptoController(val cryptoService: CryptoService) {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MissingTokenException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: Exception): String = ex.message ?: "unknown reason"

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(javax.xml.ws.soap.SOAPFaultException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: javax.xml.ws.soap.SOAPFaultException): String = ex.message ?: "unknown reason"

    @PostMapping("/encrypt/{identifier}/{id}", consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun encrypt(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable(value = "identifier") identifier: String,
        @PathVariable(value = "id") id: String,
        @RequestParam(value = "applicationId", required = false) applicationId: String?,
        @RequestBody plainData: ByteArray
               ) = cryptoService.encrypt(keystoreId, passPhrase, Addressee(IdentifierType.valueOf(identifier)).apply { this.id = id; this.applicationId = applicationId ?: "" }, plainData)

    @PostMapping("/encryptFile/{identifier}/{id}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun encryptFile(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable(value = "identifier") identifier: String,
        @PathVariable(value = "id") id: String,
        @RequestParam(value = "applicationId", required = false) applicationId: String?,
        @RequestParam plainData: MultipartFile
               ) = cryptoService.encrypt(keystoreId, passPhrase, Addressee(IdentifierType.valueOf(identifier)).apply { this.id = id; this.applicationId = applicationId ?: "" }, plainData.bytes)


    @PostMapping("/decrypt", consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun decrypt(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody encryptedData: ByteArray
               ) = cryptoService.decrypt(keystoreId, passPhrase, encryptedData)

    @PostMapping("/decryptFile", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun decryptFile(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody encryptedData: MultipartFile
               ) = cryptoService.decrypt(keystoreId, passPhrase, encryptedData.bytes)
}
