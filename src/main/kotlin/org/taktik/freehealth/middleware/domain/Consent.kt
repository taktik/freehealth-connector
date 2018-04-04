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

package org.taktik.freehealth.middleware.domain

import org.taktik.freehealth.middleware.dto.common.AuthorWithPatientDto
import org.taktik.freehealth.middleware.dto.common.KmehrCd
import java.io.Serializable

/**
 * Created by aduchate on 9/11/13, 15:06
 */
class Consent : Serializable {
    var patient: PatientId? = null
    var cds: List<KmehrCd> = mutableListOf()
    var signdate: Long? = null
    var revokedate: Long? = null
    var author: AuthorWithPatientDto? = null
}
