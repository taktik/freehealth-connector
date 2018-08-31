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

package org.taktik.freehealth.middleware.domain.common

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.taktik.freehealth.middleware.dto.Code
import java.io.Serializable

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
class Measure : Serializable {
    var value: Double? = null

    var min: Double? = null
    var max: Double? = null
    var ref: Double? = null

    var severity: Int? = null

    var unit: String? = null

    var unitCodes: Set<Code> = HashSet()
}
