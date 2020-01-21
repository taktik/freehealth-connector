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
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.ehbox.AltKeystoresList
import org.taktik.freehealth.middleware.dto.ehbox.BoxInfo
import org.taktik.freehealth.middleware.dto.ehbox.DocumentMessage
import org.taktik.freehealth.middleware.dto.ehbox.ErrorMessage
import org.taktik.freehealth.middleware.dto.ehbox.Message
import org.taktik.freehealth.middleware.exception.MissingTokenException
import org.taktik.freehealth.middleware.service.EhboxService
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/ehbox")
class EhboxController(val ehboxService: EhboxService) {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MissingTokenException::class)
    @ResponseBody
    fun handleBadRequest(req: HttpServletRequest, ex: Exception): String = ex.message ?: "unknown reason"

    @GetMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getInfos(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String): BoxInfo =
        ehboxService.getInfos(keystoreId, tokenId, passPhrase)

    @GetMapping("/{boxId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun loadMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable boxId: String, @RequestParam limit: Int?): List<Message> =
        ehboxService.loadMessages(keystoreId, tokenId, passPhrase, boxId, limit).messages

    @GetMapping("/{boxId}/{messageId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getFullMessage(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable boxId: String, @PathVariable messageId: String): Message =
        ehboxService.getFullMessage(keystoreId, tokenId, passPhrase, boxId, messageId).let {it.message ?: ErrorMessage(it.error?.descr) }

    @PostMapping("/{boxId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun loadMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable boxId: String, @RequestParam limit: Int?, @RequestBody alternateKeystores: AltKeystoresList): List<Message> =
        ehboxService.loadMessages(keystoreId, tokenId, passPhrase, boxId, limit, alternateKeystores.keystores).messages

    @PostMapping("/{boxId}/{messageId}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getFullMessage(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @PathVariable boxId: String, @PathVariable messageId: String, @RequestBody alternateKeystores: AltKeystoresList): Message =
        ehboxService.getFullMessage(keystoreId, tokenId, passPhrase, boxId, messageId, alternateKeystores.keystores).let {it.message ?: ErrorMessage(it.error?.descr) }

    @PostMapping("", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sendMessage(
        @RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID,
        @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID,
        @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String,
        @RequestBody message: DocumentMessage,
        @RequestParam publicationReceipt: Boolean?,
        @RequestParam receptionReceipt: Boolean?,
        @RequestParam readReceipt: Boolean?
    ): Boolean = ehboxService.sendMessage(
        keystoreId,
        tokenId,
        passPhrase,
        message,
        publicationReceipt ?: false,
        receptionReceipt ?: false,
        readReceipt ?: false
    ).success

    @PostMapping("/move/from/{source}/to/{destination}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun moveMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestBody messageIds: List<String>, @PathVariable source: String, @PathVariable destination: String): Boolean =
        ehboxService.moveMessages(keystoreId, tokenId, passPhrase, messageIds, source, destination).success

    @PostMapping("/move/from/{source}", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun deleteMessages(@RequestHeader(name = "X-FHC-keystoreId") keystoreId: UUID, @RequestHeader(name = "X-FHC-tokenId") tokenId: UUID, @RequestHeader(name = "X-FHC-passPhrase") passPhrase: String, @RequestBody messageIds: List<String>, @PathVariable source: String): Boolean =
        ehboxService.deleteMessages(keystoreId, tokenId, passPhrase, messageIds, source).success
}
