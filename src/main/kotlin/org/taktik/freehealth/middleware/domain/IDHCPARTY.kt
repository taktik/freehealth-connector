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

import java.io.Serializable

class IDHCPARTY : Serializable {
    enum class IDHCPARTYSCHEMES(val value: String) {
        ID_HCPARTY("ID-HCPARTY"),
        INSS("INSS"),
        LOCAL("LOCAL"),
        ID_ENCRYPTION_APPLICATION("ID-ENCRYPTION-APPLICATION"),
        ID_ENCRYPTION_ACTOR("ID-ENCRYPTION-ACTOR"),
        ID_INSURANCE("ID-INSURANCE"),
        ID_CBE("ID-CBE"),
        ID_EHP("ID-EHP");

        companion object {
            fun fromValue(value: String): IDHCPARTYSCHEMES? {
                return values().find { it.value == value }
            }
        }
    }

    var value: String? = null
    var s: IDHCPARTYSCHEMES? = null
    var sv: String? = null
    var dn: String? = null
    var l: String? = null
    var sl: String? = null
}
