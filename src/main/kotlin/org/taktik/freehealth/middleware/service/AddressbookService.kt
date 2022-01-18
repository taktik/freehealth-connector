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

package org.taktik.freehealth.middleware.service

import org.taktik.freehealth.middleware.dto.HealthcareParty
import java.util.*

interface AddressbookService {
    fun searchHcp(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        queryLastName: String? = null,
        queryFirstName: String? = null,
        queryEmailAddress: String? = null,
        queryZipCode: String? = null,
        queryCity: String? = null,
        type: String = "PHYSICIAN",
        limit: Int = 100
    ): List<HealthcareParty>

    fun searchOrg(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        name: String,
        type: String = "HOSPITAL"
    ): List<HealthcareParty>

    fun getHcp(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        nihii: String?,
        ssin: String?,
        quality: String?,
        language: String = "fr"
    ): HealthcareParty?

    fun getOrg(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        ehp: String?,
        cbe: String?,
        nihii: String?,
        language: String = "fr"
    ): HealthcareParty?
}
