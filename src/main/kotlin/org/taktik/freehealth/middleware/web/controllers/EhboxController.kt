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

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.ehbox.BoxInfo
import org.taktik.freehealth.middleware.dto.ehbox.DocumentMessage
import org.taktik.freehealth.middleware.dto.ehbox.Message
import org.taktik.freehealth.middleware.service.EhboxService
import java.util.*

@RestController
@RequestMapping("/ehbox")
class EhboxController(val ehboxService: EhboxService) {
    @GetMapping("")
    fun getInfos(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam passPhrase: String): BoxInfo =
        ehboxService.getInfos(keystoreId, tokenId, passPhrase)

    @GetMapping("/{boxId}")
    fun loadMessages(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam passPhrase: String, @PathVariable boxId: String, @RequestParam limit: Int?, @RequestParam alternateKeystoreId: UUID? = null, @RequestParam alternateKeystorePassPhrase: String? = null): List<Message> =
        ehboxService.loadMessages(keystoreId, tokenId, passPhrase, boxId, limit, alternateKeystoreId, alternateKeystorePassPhrase)

    @GetMapping("/{boxId}/{messageId}")
    fun getFullMessage(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam passPhrase: String, @PathVariable boxId: String, @PathVariable messageId: String, @RequestParam alternateKeystoreId: UUID? = null, @RequestParam alternateKeystorePassPhrase: String? = null): Message =
        ehboxService.getFullMessage(keystoreId, tokenId, passPhrase, boxId, messageId, alternateKeystoreId, alternateKeystorePassPhrase)

    @PostMapping("")
    fun sendMessage(
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        message: DocumentMessage,
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
    )

    @PostMapping("/move/from/{source}/to/{destination}")
    fun moveMessages(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam passPhrase: String, @RequestBody messageIds: List<String>, @PathVariable source: String, @PathVariable destination: String): Boolean =
        ehboxService.moveMessages(keystoreId, tokenId, passPhrase, messageIds, source, destination)

    @PostMapping("/move/from/{source}")
    fun deleteMessages(@RequestParam keystoreId: UUID, @RequestParam tokenId: UUID, @RequestParam passPhrase: String, @RequestBody messageIds: List<String>, @PathVariable source: String): Boolean =
        ehboxService.deleteMessages(keystoreId, tokenId, passPhrase, messageIds, source)
}
