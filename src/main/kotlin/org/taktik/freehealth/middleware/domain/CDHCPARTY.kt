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

import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import java.io.Serializable

class CDHCPARTY : Serializable {
    enum class CDHCPARTYSCHEMES(val value: String) {
        CD_HCPARTY("CD-HCPARTY"),
        CD_APPLICATION("CD-APPLICATION"),
        CD_ENCRYPTION_ACTOR("CD-ENCRYPTION-ACTOR"),
        CD_ROLE("CD-ROLE"),
        LOCAL("LOCAL")
    }

    var value: String? = null
    var s: CDHCPARTYschemes? = null
    var sv: String? = null
    var dn: String? = null
    var l: String? = null
    var sl: String? = null
}
