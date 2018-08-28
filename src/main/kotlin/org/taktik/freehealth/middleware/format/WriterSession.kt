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

package org.taktik.freehealth.middleware.format

import org.taktik.freehealth.middleware.format.efact.Zone
import org.taktik.freehealth.middleware.format.efact.segments.RecordOrSegmentDescription

import java.io.IOException
import java.io.Writer
import java.math.BigInteger
import java.text.DecimalFormat

class WriterSession(private val writer: Writer, private val format: RecordOrSegmentDescription) {

    internal var fields: MutableMap<Int, Zone> = HashMap()

    fun write(zones: String, value: Any?) {
        val zone = format.zoneDescriptionsByZone[zones.split(",").first()]
        requireNotNull(zone) { "Zones $zones are invalid" }
        zone?.let { fields[it.position] =
            Zone(it, value)
        }
    }

    @Throws(IOException::class)
    fun writeFieldsWithCheckSum() {
        var bi = BigInteger.ZERO
        for (z in format.zoneDescriptions) {
            val content = (fields[z.position] ?: Zone(z, z.value)).write(writer)
            bi = bi.add(BigInteger.valueOf(checksum(content)))
        }
        val nf = DecimalFormat("00")
        val modulo = bi.mod(BigInteger.valueOf(97)).toInt()
        writer.write(nf.format((if (modulo == 0) 97 else modulo).toLong()))
    }

    private fun checksum(value: String): Long {
        var res: Long = 0
        for (i in 0 until value.length) {
            val c = value[i]
            res += when (c) {
                in '0'..'9' -> (c - '0').toLong()
                ' ' -> 10
                in 'A'..'Z' -> (c - 'A' + 11).toLong()
                in 'a'..'z' -> (c - 'a' + 11).toLong()
                else -> 37
            }
        }
        return res
    }

    @Throws(IOException::class)
    fun writeFieldsWithoutCheckSum() {
        for (z in format.zoneDescriptions) {
            (fields[z.position] ?: Zone(z, z.value)).write(writer)
        }
    }
}
