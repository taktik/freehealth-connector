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

import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointDistributor
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointUpdater
import ch.qos.logback.classic.Level
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.service.AddressbookService
import ch.qos.logback.classic.LoggerContext
import org.apache.commons.logging.LogFactory
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.taktik.freehealth.middleware.dao.User
import org.taktik.freehealth.middleware.dto.EndpointDistributorStatusDto


@RestController
@RequestMapping("/admin")
class AdminController(val addressbookService: AddressbookService) {
    private val log = LogFactory.getLog(this.javaClass)

    @PostMapping("/loglevel/{loglevel}", produces = [MediaType.TEXT_PLAIN_VALUE])
    @Throws(Exception::class)
    fun loglevel(@PathVariable("loglevel") logLevel: String, @RequestParam(value = "package") packageName: String): String {
        val principal = SecurityContextHolder.getContext().authentication?.principal as? User

        if (principal?.authorities?.any { it.authority == "ROLE_ADMIN" } != true) {
            throw IllegalAccessException("You are not an administrator. This illegal access attempt has been logged")
        }
        log.info("Log level: $logLevel")
        log.info("Package name: $packageName")
        return setLogLevel(logLevel, packageName)
    }

    @GetMapping("/bcp", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun bcpStatus(): EndpointDistributorStatusDto {
        val principal = SecurityContextHolder.getContext().authentication?.principal as? User

        if (principal?.authorities?.any { it.authority == "ROLE_ADMIN" } != true) {
            throw IllegalAccessException("You are not an administrator. This illegal access attempt has been logged")
        }
        val distributor = EndpointDistributor.getInstance()
        return EndpointDistributorStatusDto(mustPoll = distributor.mustPoll(), isBcpMode = distributor.isBCPMode)
    }

    @PostMapping("/bcp", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateBcpStatus() {
        val principal = SecurityContextHolder.getContext().authentication?.principal as? User

        if (principal?.authorities?.any { it.authority == "ROLE_ADMIN" } != true) {
            throw IllegalAccessException("You are not an administrator. This illegal access attempt has been logged")
        }

        EndpointUpdater.forceUpdate()
    }

    fun setLogLevel(logLevel: String, packageName: String): String {
        val retVal: String
        val loggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
        if (logLevel.equals("DEBUG", ignoreCase = true)) {
            loggerContext.getLogger(packageName).level = Level.DEBUG
            retVal = "ok"
        } else if (logLevel.equals("INFO", ignoreCase = true)) {
            loggerContext.getLogger(packageName).level = Level.INFO
            retVal = "ok"
        } else if (logLevel.equals("TRACE", ignoreCase = true)) {
            loggerContext.getLogger(packageName).level = Level.TRACE
            retVal = "ok"
        } else {
            log.error("Not a known loglevel: $logLevel")
            retVal = "Error, not a known loglevel: $logLevel"
        }
        return retVal
    }

}
