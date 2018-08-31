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

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.util.*

/**
 * Created by aduchate on 21/01/13, 15:37
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class Insurability : Serializable {
    //Key from InsuranceParameter
    var parameters: Map<InsuranceParameter, String> = HashMap()
    var hospitalisation: Boolean? = null
    var ambulatory: Boolean? = null
    var dental: Boolean? = null
    var identificationNumber: String? = null // N° in form (number for the insurance's identification)
    var insuranceId: String? = null // UUID to identify Partena, etc. (link to Insurance object's document ID)
    var startDate: Long? = null
    var endDate: Long? = null
    var titularyId: String? = null //UUID of the contact person who is the titulary of the insurance
}
