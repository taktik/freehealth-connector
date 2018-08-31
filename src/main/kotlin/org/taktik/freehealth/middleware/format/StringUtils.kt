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

import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.text.Normalizer

object StringUtils {
    fun sanitizeString(key: String?): String? {
        return if (key == null) {
            null
        } else removeDiacriticalMarks(key).replace("[\\s]".toRegex(), "")
            .replace("\\W".toRegex(), "").toLowerCase()

    }

    fun removeDiacriticalMarks(key: String): String {
        try {
            if (Normalizer::class.java.getMethod("normalize", CharSequence::class.java, Normalizer.Form::class.java) != null) {
                return Normalizer.normalize(key.replace("ø".toRegex(), "o").replace("æ".toRegex(), "ae").replace("Æ".toRegex(), "AE").replace("Œ".toRegex(), "oe").replace("œ".toRegex(), "oe"), Normalizer.Form.NFD)
                    .replace("\\p{InCombiningDiacriticalMarks}".toRegex(), "")
            }
        } catch (ignored: NoSuchMethodException) {
        }

        //Fallback
        return key.replace("[\u00E8\u00E9\u00EA\u00EB]".toRegex(), "e")
            .replace("[\u00FB\u00F9\u00FC]".toRegex(), "u")
            .replace("[\u00E7]".toRegex(), "c")
            .replace("[\u00EF\u00EE\u00EC]".toRegex(), "i")
            .replace("[\u00E0\u00E2\u00E4]".toRegex(), "a")
            .replace("[\u00F6\u00F2\u00F4]".toRegex(), "o")
            .replace("[\u00C8\u00C9\u00CA\u00CB]".toRegex(), "E")
            .replace("[\u00DB\u00D9\u00DC]".toRegex(), "U")
            .replace("[\u00CF\u00CE\u00CC]".toRegex(), "I")
            .replace("[\u00C0\u00C2\u00C4]".toRegex(), "A")
            .replace("[\u00D4\u00D6\u00D2]".toRegex(), "O")
            .replace("ø".toRegex(), "o")
            .replace("æ".toRegex(), "ae")
            .replace("Æ".toRegex(), "AE")
            .replace("Œ".toRegex(), "oe")
            .replace("œ".toRegex(), "oe")
    }

    fun safeConcat(vararg components: String): String {
        val sb = StringBuffer()
        for (c in components) {
            if (c != null) {
                sb.append(c)
            }
        }
        return sb.toString()
    }

    fun detectFrenchCp850Cp1252(data: ByteArray): String? {
        val br: BufferedReader
        var score = 0
        try {
            br = BufferedReader(InputStreamReader(ByteArrayInputStream(data), "cp850"))
            var c: Int
            var done = false
            while (!done) {
                c = br.read()
                when (c) {
                    -1 -> done = true
                    '\u00e8'.toInt() -> score++
                    '\u00e9'.toInt() -> score++
                    '\u00e0'.toInt() -> score++
                    '\u00e7'.toInt() -> score++
                    '\u00b5'.toInt() -> score++
                    '\u00d3'.toInt() -> score--
                    '\u00fe'.toInt() -> score--
                    '\u00de'.toInt() -> score--
                    '\u00da'.toInt() -> score--
                    '\u00c1'.toInt() -> score--
                }
            }
            return if (score > 3) "cp850" else if (score < -3) "cp1252" else null
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun equals(s1: String?, s2: String?): Boolean {
        return s1 != null && s2 != null && (org.apache.commons.lang.StringUtils.equals(s1, s2) || org.apache.commons.lang.StringUtils.equals(sanitizeString(s1), sanitizeString(s2)))
    }
}
