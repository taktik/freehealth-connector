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

import org.taktik.freehealth.middleware.dto.efact.segments.ZoneDescription
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties("zoneDescription")
class Zone(var zoneDescription:ZoneDescription? = null, var value: Any? = null) {

    val description: String? = this.zoneDescription?.label;

    override fun toString(): String {
        return "${padBlanks(zoneDescription?.zones?.first() ?: "", 4)}[${padBlanks(zoneDescription!!.position.toString(), 3)}]:\t$value"
    }

    companion object {
        private val ZEROS =
            "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
        private val BLANKS =
            "                                                                                                                                                                                                                                                                                                                                                                                                                "

        fun padBlanks(value: String, n: Int): String {
            return if (value.length < n) value + BLANKS.substring(0, n - value.length) else value
        }
    }
}
