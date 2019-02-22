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

package org.taktik.freehealth.middleware.format.efact.segments

import org.apache.commons.lang.StringUtils
import java.lang.IllegalArgumentException

class ZoneDescription private constructor(
    val label: String,
    val property: String?,
    val position: Int,
    val length: Int,
    val type: ZoneType,
    val zones: List<String>,
    val value: String? = null,
    val cs: Boolean = false,
    val optional: Boolean = false
                                         ) {
    var zonesList: String? = null
        get() = field ?: StringUtils.join(zones, ",")

    val zone: String
        get() = zones[0]

    init {
        this.zonesList = StringUtils.join(zones, ",")
    }

    enum class ZoneType private constructor(val symbol: String) {
        ALPHANUMERICAL("A"),
        NUMERICAL("N");

        companion object {
            fun fromSymbol(symbol: String): ZoneType? {
                return values().find { it.symbol === symbol }
            }
        }
    }

    companion object {
        fun build(commaSeparatedZones: String,
            label: String,
            property: String?,
            typeSymbol: String,
            position: Int,
            length: Int,
            value: String? = null,
            cs: Boolean = false,
            optional: Boolean = false): ZoneDescription {
            val type = ZoneType.fromSymbol(typeSymbol) ?: throw IllegalArgumentException("Invalid type $typeSymbol")
            val splitZones = commaSeparatedZones.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val zones = splitZones.map { it.trim { it <= ' ' } }
            return ZoneDescription(label, property, position, length, type, zones, value, cs, optional)
        }
    }
}
