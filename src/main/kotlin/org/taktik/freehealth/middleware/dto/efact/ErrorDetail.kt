/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.freehealth.middleware.dto.efact

import org.taktik.freehealth.middleware.dto.efact.segments.RecordOrSegmentDescription
import java.io.Serializable

class ErrorDetail : Serializable {
    var creationDate: Int = 0
    var errorCodeComment: String? = null
    var index: Int = 0
    var record: Record<RecordOrSegmentDescription>? = null
    var invoicingYearMonth: Int = 0
    var mutualityCode: Int = 0
    var oaResult: String? = null
    var rejectionCode1: String? = null
    var rejectionCode2: String? = null
    var rejectionCode3: String? = null
    var rejectionLetter1: String? = null
    var rejectionLetter2: String? = null
    var rejectionLetter3: String? = null
    var rejectionDescr1: String? = null
    var rejectionDescr2: String? = null
    var rejectionDescr3: String? = null
    var rejectionZoneDescr1: String? = null
    var rejectionZoneDescr2: String? = null
    var rejectionZoneDescr3: String? = null
    var reserve: String? = null
    var sendingId: Int = 0

    constructor()

    constructor(record: Record<RecordOrSegmentDescription>) {
        this.record = record
    }
}
