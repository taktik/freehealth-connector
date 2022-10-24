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
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.service.APBService

@RestController
@RequestMapping("/apb")
class APBController(private val apbService: APBService) {
    val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/token/bearer", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getAPBBearerToken() = apbService.getAPBBearerToken()

    @GetMapping("/token/bearer/ftm", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getFTMBearerToken() = apbService.getFTMBearerToken()
}
