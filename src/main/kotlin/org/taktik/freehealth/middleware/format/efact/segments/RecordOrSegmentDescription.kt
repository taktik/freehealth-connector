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

import java.util.ArrayList
import java.util.HashSet

abstract class RecordOrSegmentDescription {
    abstract val zoneDescriptionsByZone: Map<String, ZoneDescription>

    val zoneDescriptions: List<ZoneDescription>
        get() {
            return ArrayList(HashSet(zoneDescriptionsByZone.values)).sortedWith(Comparator { zd1, zd2 -> zd1.position.compareTo(zd2.position) })
        }

    operator fun contains(zone: String): Boolean {
        return zoneDescriptionsByZone.containsKey(zone)
    }

    protected fun register(zoneDescriptionsByZone: MutableMap<String, ZoneDescription>,
                           zones: String,
                           label: String,
                           typeSymbol: String,
                           position: Int,
                           length: Int,
                           value: String? = null,
                           cs: Boolean = false,
                           optional: Boolean = false): Int {
        val zoneDescription = ZoneDescription.build(zones, label, typeSymbol, position, length, value, cs, optional)
        for (zone in zones.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            zoneDescriptionsByZone[zone.trim { it <= ' ' }] = zoneDescription
        }
        return position + length
    }

    override fun toString(): String {
        return zoneDescriptions.firstOrNull()?.let { it.value } ?: super.toString()
    }

}
