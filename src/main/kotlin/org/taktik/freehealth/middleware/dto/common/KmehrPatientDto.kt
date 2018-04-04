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

package org.taktik.freehealth.middleware.dto.common

import be.fgov.ehealth.standards.kmehr.id.v1.IDPATIENT
import java.io.Serializable

class KmehrPatientDto : Serializable {
    var inss: String? = null
    var regNrWithMut: String? = null
    var mutuality: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var middleName: String? = null
    var name: String? = null
    var ids: List<IDPATIENT>? = null

    var eidCardNumber: String? = null
    var sisCardNumber: String? = null
    var isiCardNumber: String? = null
}
