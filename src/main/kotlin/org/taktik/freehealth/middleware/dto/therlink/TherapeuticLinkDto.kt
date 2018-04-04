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

package org.taktik.freehealth.middleware.dto.therlink

import org.taktik.freehealth.middleware.dto.common.HcPartyDto
import org.taktik.freehealth.middleware.dto.common.KmehrPatientDto
import java.io.Serializable

class TherapeuticLinkDto : Serializable {
    var patient: KmehrPatientDto? = null
    var hcParty: HcPartyDto? = null
    var type: String? = null
    var startDate: Long? = null
    var endDate: Long? = null
    var comment: String? = null
    var status: String? = null
}
