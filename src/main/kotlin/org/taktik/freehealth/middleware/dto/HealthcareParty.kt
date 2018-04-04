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

package org.taktik.freehealth.middleware.dto

import org.taktik.freehealth.middleware.dto.common.Gender

class HealthcareParty(
        val name: String? = null,
        val lastName: String? = null,
        val firstName: String? = null,
        val gender: Gender = Gender.unknown,
        val civility: String? = null,
        val speciality: String? = null,
        val companyName: String? = null,
        val bankAccount: String? = null,
        val bic: String? = null,
        val proxyBankAccount: String? = null,
        val proxyBic: String? = null,
        val invoiceHeader: String? = null,
        val type: String? = null,
        val cbe: String? = null,
        val ehp: String? = null,
        val nihii: String ? = null,
        val ssin: String? = null,
        val addresses: MutableSet<Address> = HashSet(),
        val languages: MutableList<String> = mutableListOf()
)