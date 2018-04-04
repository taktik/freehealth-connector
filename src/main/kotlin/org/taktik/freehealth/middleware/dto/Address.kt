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

import java.io.Serializable
import java.util.*

data class Address(
        val addressType: AddressType = AddressType.home,
        val street: String? = null,
        val houseNumber: String? = null,
        val postboxNumber: String? = null,
        val postalCode: String? = null,
        val city: String? = null,
        val country: String? = null,
        val telecoms: MutableSet<Telecom> = HashSet()
) : Serializable {
    fun mergeFrom(other: Address) = Address(
            street = if (this.street == null && other.street != null) other.street else this.street,
            houseNumber = if (this.houseNumber == null && other.houseNumber != null) other.houseNumber else this.houseNumber,
            postboxNumber = if (this.postboxNumber == null && other.postboxNumber != null) other.postboxNumber else this.postboxNumber,
            postalCode = if (this.postalCode == null && other.postalCode != null) other.postalCode else this.postalCode,
            city = if (this.city == null && other.city != null) other.city else this.city,
            country = if (this.country == null && other.country != null) other.country else this.country
    ).apply {
        telecoms.addAll(this.telecoms.map { destTelecom -> other.telecoms.find { telecom -> telecom.telecomType === destTelecom.telecomType }?.let { destTelecom.mergeFrom(it) } ?: destTelecom })
        telecoms.addAll(other.telecoms.filter { fromTelecom -> this.telecoms.none { telecom -> telecom.telecomType === fromTelecom.telecomType }})
    }

    fun forceMergeFrom(other: Address) = Address(
            street = other.street ?: this.street,
            houseNumber = other.houseNumber ?: this.houseNumber,
            postboxNumber = other.postboxNumber ?: this.postboxNumber,
            postalCode = other.postalCode ?: this.postalCode,
            city = other.city ?: this.city,
            country = other.country ?: this.country
    ).apply {
        telecoms.addAll(this.telecoms.map { destTelecom -> other.telecoms.find { telecom -> telecom.telecomType === destTelecom.telecomType }?.let { destTelecom.forceMergeFrom(it) } ?: destTelecom })
        telecoms.addAll(other.telecoms.filter { fromTelecom -> this.telecoms.none { telecom -> telecom.telecomType === fromTelecom.telecomType }})
    }

}
