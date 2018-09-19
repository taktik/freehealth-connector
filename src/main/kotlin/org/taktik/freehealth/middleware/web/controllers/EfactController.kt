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
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.efact.InvoicesBatch
import org.taktik.freehealth.middleware.dto.etarif.TarificationConsultationResult
import org.taktik.freehealth.middleware.service.EfactService
import org.taktik.freehealth.middleware.service.TarificationService
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/efact")
class EfactController(val efactService: EfactService, val mapper: MapperFacade) {

    @PostMapping("/batch")
    fun sendBatch(
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestBody batch: InvoicesBatch
                 ) =
        efactService.sendBatch(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            batch = batch
                              )

    @PostMapping("/flat")
    fun sendBatch(
        @RequestBody batch: InvoicesBatch
                 ) =
        efactService.makeFlatFile(
            batch = batch,
            isTest = false
                              )

    @PostMapping("/flat/test")
    fun sendBatchTest(
        @RequestBody batch: InvoicesBatch
                 ) =
        efactService.makeFlatFile(
            batch = batch,
            isTest = true
                                 )

    @GetMapping("/{nihii}/{language}")
    fun loadMessages(
        @PathVariable nihii: String,
        @PathVariable language: String,
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @RequestParam ssin: String,
        @RequestParam firstName: String,
        @RequestParam lastName: String
                 ) =
        efactService.loadMessages(
            keystoreId = keystoreId,
            tokenId = tokenId,
            passPhrase = passPhrase,
            hcpSsin = ssin,
            hcpNihii = nihii,
            hcpFirstName = firstName,
            hcpLastName = lastName,
            language = language
                                 )

}
