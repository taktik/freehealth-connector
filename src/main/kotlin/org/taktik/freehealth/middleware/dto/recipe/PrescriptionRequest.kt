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

package org.taktik.freehealth.middleware.dto.recipe

import org.taktik.freehealth.middleware.domain.Medication
import org.taktik.freehealth.middleware.domain.Patient
import org.taktik.freehealth.middleware.dto.HealthcareParty
import java.time.LocalDateTime
import java.util.*

class PrescriptionRequest(
    var patient: Patient? = null,
    var hcp: HealthcareParty? = null,
    var feedback: Boolean? = null,
    var medications: List<Medication>? = null,
    var prescriptionType: String? = null,
    var notification: String? = null,
    var executorId: String? = null,
    var deliveryDate: Long? = null //yyyyMMdd
)
