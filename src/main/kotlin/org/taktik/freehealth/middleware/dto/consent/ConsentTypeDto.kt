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

package org.taktik.freehealth.middleware.dto.consent

import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONSENT
import org.taktik.freehealth.middleware.dto.common.AuthorWithPatientDto
import org.taktik.freehealth.middleware.dto.common.KmehrPatientDto

class ConsentTypeDto {
    var patient: KmehrPatientDto? = null
    var cds: List<CDCONSENT>? = null
    var signdate: Long? = null
    var revokedate: Long? = null
    var author: AuthorWithPatientDto? = null
}
