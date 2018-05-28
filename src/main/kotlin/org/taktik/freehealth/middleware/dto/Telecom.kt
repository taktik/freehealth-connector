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

class Telecom(var telecomType: TelecomType = TelecomType.email, var telecomNumber: String? = null) : Serializable {
    fun mergeFrom(other: Telecom): Telecom = Telecom(
        telecomNumber = if (this.telecomNumber == null && other.telecomNumber != null) other.telecomNumber else this.telecomNumber
    )

    fun forceMergeFrom(other: Telecom) = Telecom(
        telecomNumber = other.telecomNumber ?: this.telecomNumber
    )
}
