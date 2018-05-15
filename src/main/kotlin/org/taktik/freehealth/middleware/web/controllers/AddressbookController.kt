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
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.service.AddressbookService
import java.util.*

@RestController
@RequestMapping("/ab")
class AddressbookController(val addressbookService: AddressbookService) {
    @GetMapping("/search/hcp/{lastName}")
    fun searchHcp(
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @PathVariable lastName: String,
        @RequestParam(
                    required = false
                ) firstName: String? = null,
        @RequestParam(required = false) type: String?
    ): List<HealthcareParty> = addressbookService.searchHcp(
        keystoreId, tokenId, passPhrase, lastName, firstName, type ?: "PHYSICIAN"
    )

    @GetMapping("/search/org/{name}")
    fun searchOrg(
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @PathVariable name: String,
        @RequestParam(
                    required = false
                ) type: String?
    ): List<HealthcareParty> = addressbookService.searchOrg(
        keystoreId, tokenId, passPhrase, name, type ?: "HOSPITAL"
    )

    @GetMapping("/hcp/nihii/{nihii}")
    fun getHcpByNihii(
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @PathVariable nihii: String,
        @RequestParam(
                    required = false
                ) language: String?
    ): HealthcareParty = addressbookService.getHcp(
        keystoreId, tokenId, passPhrase, nihii, null, language ?: "fr"
    )

    @GetMapping("/hcp/ssin/{ssin}")
    fun getHcpBySsin(
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @PathVariable ssin: String,
        @RequestParam(
                    required = false
                ) language: String?
    ): HealthcareParty = addressbookService.getHcp(
        keystoreId, tokenId, passPhrase, null, ssin, language ?: "fr"
    )

    @GetMapping("/org/nihii/{nihii}")
    fun getOrgByNihii(
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @PathVariable nihii: String,
        @RequestParam(
                    required = false
                ) language: String?
    ): HealthcareParty = addressbookService.getOrg(
        keystoreId, tokenId, passPhrase, null, null, nihii, language ?: "fr"
    )

    @GetMapping("/org/cbe/{cbe}")
    fun getOrgByCbe(
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @PathVariable cbe: String?,
        @RequestParam(
                    required = false
                ) language: String?
    ): HealthcareParty = addressbookService.getOrg(
        keystoreId, tokenId, passPhrase, null, cbe, null, language ?: "fr"
    )

    @GetMapping("/org/ehp/{ehp}")
    fun getOrgByEhp(
        @RequestParam keystoreId: UUID,
        @RequestParam tokenId: UUID,
        @RequestParam passPhrase: String,
        @PathVariable ehp: String?,
        @RequestParam(
                    required = false
                ) language: String?
    ): HealthcareParty = addressbookService.getOrg(
        keystoreId, tokenId, passPhrase, ehp, null, null, language ?: "fr"
    )
}
