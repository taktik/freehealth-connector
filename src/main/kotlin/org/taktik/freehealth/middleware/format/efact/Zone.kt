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

package org.taktik.freehealth.middleware.format.efact

import org.taktik.freehealth.middleware.format.StringUtils
import org.taktik.freehealth.middleware.format.efact.segments.ZoneDescription
import java.io.IOException
import java.io.Writer
import java.lang.NumberFormatException
import java.text.DecimalFormat

class Zone(private val zoneDescription:ZoneDescription, val value: Any?) {
    init {
        if (zoneDescription.length > 500) {
            throw IllegalArgumentException("${zoneDescription.label} too large")
        }
    }

    @Throws(IOException::class)
    fun write(w: Writer): String {
        if (zoneDescription.type == ZoneDescription.ZoneType.NUMERICAL) {
            val nf = DecimalFormat(ZEROS.substring(0, zoneDescription.length))
            var longValue: Long = 0

            try {
                if (value is Number) {
                    longValue = value.toLong()
                } else if (value is String) {
                    longValue = if (value.length > 0) java.lang.Long.valueOf((value as String?)!!) else 0L
                }
            } catch (e:NumberFormatException) {
                throw IllegalArgumentException("Zone: ${zoneDescription.zone} [${zoneDescription.label}] of length ${zoneDescription.length}: invalid input value: $value", e)
            }

            val stringValue = nf.format(if (value != null) longValue else 0L)
            if (stringValue.length != zoneDescription.length) {
                throw IllegalStateException("Zone: ${zoneDescription.zone} [${zoneDescription.label}] of length ${zoneDescription.length}: value is too long: $value")
            }
            w.write(stringValue)
            return stringValue
        } else if (zoneDescription.type == ZoneDescription.ZoneType.ALPHANUMERICAL) {
            var stringValue = if (value != null) StringUtils.removeDiacriticalMarks(value.toString()) else ""
            if (stringValue.length > zoneDescription.length) {
                throw IllegalStateException("${zoneDescription.label} value is too long")
            } else if (stringValue.length < zoneDescription.length) {
                stringValue += BLANKS.substring(0, zoneDescription.length - stringValue.length)
            }
            w.write(stringValue)
            return stringValue
        } else {
            throw IllegalStateException("Illegal type ${zoneDescription.type} for ${zoneDescription.label}")
        }
    }

    override fun toString(): String {
        return "${padBlanks(zoneDescription.zone, 4)}[${padBlanks(zoneDescription.position.toString(), 3)}]:\t$value"
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
