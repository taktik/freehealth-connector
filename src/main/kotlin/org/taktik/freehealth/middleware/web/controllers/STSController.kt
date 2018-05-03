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
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.taktik.freehealth.middleware.dto.UUIDType
import org.taktik.freehealth.middleware.service.STSService
import java.util.*

@RestController
@RequestMapping("/sts")
class STSController(private val stsService: STSService) {
    val log = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/keystore")
    fun uploadKeystore(@RequestParam file: MultipartFile) = stsService.uploadKeystore(file).let { UUIDType(it) }

    @GetMapping("/token/{keystoreId}")
    fun requestToken(@RequestParam passPhrase: String, @RequestParam ssin: String, @RequestParam(required = false) isMedicalHouse: Boolean? = null, @PathVariable keystoreId: UUID) =
        stsService.requestToken(keystoreId, ssin, passPhrase, isMedicalHouse ?: false)

    @PostMapping("/token/{tokenId}")
    fun registerToken(@RequestBody token: String, @PathVariable tokenId: UUID) {
        stsService.registerToken(tokenId, token)
    }
}
