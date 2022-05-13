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
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.service.SchematronService
import java.io.InputStream
import java.util.*

@RestController
@RequestMapping("/schematron")
class SchematronController(val schematronService: SchematronService) {
    @PostMapping("/validate/{schema}", consumes = [MediaType.APPLICATION_XML_VALUE], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getDinRoutingInfo(
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @PathVariable schema: String,
        @RequestBody(required = true) body: ByteArray
    ) = schematronService.validate(tokenId, schema, body.inputStream())
}
