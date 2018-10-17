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
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.MycarenetError
import org.taktik.freehealth.middleware.dto.etarif.TarificationConsultationResult
import org.taktik.freehealth.middleware.service.TarificationService
import java.time.LocalDateTime
import java.util.*

@RestController
@RequestMapping("/tarif")
class TarificationController(val tarificationService: TarificationService, val mapper: MapperFacade) {
    @PostMapping("/{ssin}")
    fun consultTarification(
        @PathVariable ssin: String,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestParam hcpFirstName: String,
        @RequestParam hcpLastName: String,
        @RequestParam hcpNihii: String,
        @RequestParam hcpSsin: String,
        @RequestParam(required = false) date: Long? = null,
        @RequestParam(required = false) gmdNihii: String? = null,
        @RequestParam(required = false) justification: String? = null,
        @RequestParam(required = false) traineeSupervisorSsin: String? = null,
        @RequestParam(required = false) traineeSupervisorNihii: String? = null,
        @RequestParam(required = false) traineeSupervisorFirstName: String? = null,
        @RequestParam(required = false) traineeSupervisorLastName: String? = null,
        @RequestBody codes: List<String>
    ) = try { tarificationService.consultTarif(
        keystoreId = keystoreId,
        tokenId = tokenId,
        hcpFirstName = hcpFirstName,
        hcpLastName = hcpLastName,
        hcpNihii = hcpNihii,
        hcpSsin = hcpSsin,
        passPhrase = passPhrase,
        patientSsin = ssin,
        consultationDate = date?.let { LocalDateTime.of((date / 10000).toInt(), ((date / 100).toInt() % 100), (date % 100).toInt(), 0, 0)} ?: LocalDateTime.now(),
        justification = justification,
        gmdNihii = gmdNihii,
        traineeSupervisorSsin = traineeSupervisorSsin,
        traineeSupervisorNihii = traineeSupervisorNihii,
        traineeSupervisorFirstName = traineeSupervisorFirstName,
        traineeSupervisorLastName = traineeSupervisorLastName,
        codes = codes).let { mapper.map(it, TarificationConsultationResult::class.java) } } catch (e : Exception) {
        TarificationConsultationResult().apply { errors.add(MycarenetError(
            code = "999999",
            msgFr = e.message,
            msgNl = e.message,
            locFr = e.stackTrace?.toList()?.map { it.toString() }?.joinToString(";"),
            locNl = e.stackTrace?.toList()?.map { it.toString() }?.joinToString(";")))
        }
    }
}
