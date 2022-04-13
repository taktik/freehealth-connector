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
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.service.DataAttributeService
import java.util.*

@RestController
@RequestMapping("/daas")
class DataAttributeServiceController(val dataAttributeService: DataAttributeService) {
    @GetMapping("/din/{nihii}/{ssin}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getDinRoutingInfo(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @PathVariable nihii: String,
        @PathVariable ssin: String,
        @RequestParam(required = true) dateOfBirth: Int,
        @RequestParam(required = true) from: Long,
        @RequestParam(required = true) to: Long,
        @RequestParam(required = true) cause: String,
        @RequestParam(required = true) prolongation: Boolean,
        @RequestParam(required = true) total: Boolean
    ) = dataAttributeService.getDinRoutingInfo(
        keystoreId, tokenId, passPhrase, nihii, ssin, dateOfBirth, cause, from, to, total, prolongation
    )
}
