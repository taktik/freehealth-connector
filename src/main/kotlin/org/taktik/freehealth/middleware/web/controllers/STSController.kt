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

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.taktik.freehealth.middleware.dto.UUIDType
import org.taktik.freehealth.middleware.service.SSOService
import org.taktik.freehealth.middleware.service.STSService
import java.util.UUID

@RestController
@RequestMapping("/sts")
class STSController(private val stsService: STSService, private val ssoService: SSOService) {
    val log = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/keystore", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun uploadKeystore(@RequestParam file: MultipartFile) = UUIDType(stsService.uploadKeystore(file))

    @GetMapping("/keystore/{keystoreId}/info", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getKeystoreInfo(@PathVariable(name = "keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String) = stsService.getKeystoreInfo(keystoreId, passPhrase)

    @Deprecated("Please specify a quality")
    @GetMapping("/token", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun requestToken(@RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam ssin: String, @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestParam(required = false) isMedicalHouse: Boolean?, @RequestParam(required = false) isGuardPost: Boolean?, @RequestParam(required = false) isSortingCenter: Boolean?, @RequestHeader(name = "X-FHC-tokenId", required = false) previousTokenId: UUID?) =
        stsService.requestToken(keystoreId, ssin, passPhrase, when {
            isMedicalHouse?: false -> "medicalhouse"
            isGuardPost?: false -> "guardpost"
            isSortingCenter ?: false -> "sortingcenter"
            else -> "doctor"
        }, previousTokenId)

    @GetMapping("/token/{quality}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun requestToken(@RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam ssin: String, @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @PathVariable(name = "quality") quality: String, @RequestHeader(name = "X-FHC-tokenId", required = false) previousTokenId: UUID?) =
        stsService.requestToken(keystoreId, ssin, passPhrase, quality ?: "doctor", previousTokenId)

    @PostMapping("/token", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun registerToken(@RequestBody token: String, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestParam(required = false) quality: String?) {
        stsService.registerToken(tokenId, token, quality ?: "doctor")
    }

    @GetMapping("/keystore/check", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun checkKeystoreExist(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID) = stsService.checkIfKeystoreExist(keystoreId)

    @GetMapping("/token/check", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun checkTokenValid(@RequestHeader(name = "X-FHC-tokenId") tokenId: UUID) = stsService.checkTokenValid(tokenId)

    @GetMapping("/token/bearer", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getBearerToken(@RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestParam ssin: String, @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID) =
        ssoService.getBearerToken(tokenId, keystoreId, passPhrase)
}
