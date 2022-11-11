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

import org.apache.commons.logging.LogFactory
import java.io.EOFException
import java.io.IOException
import java.io.PushbackReader
import java.io.Reader

class ReaderSession(reader: Reader) {
    private val log = LogFactory.getLog(this.javaClass)
    private val reader: PushbackReader = PushbackReader(reader, 6)

    val messageType: String
        @Throws(IOException::class)
        get() {
            val messageType = read("Message type", 6)
            reader.unread(messageType.toCharArray())
            return messageType
        }

    @Throws(IOException::class)
    fun peek(label: String, length: Int) = read(label, length).apply { reader.unread(length) }

    @Throws(IOException::class)
    fun read(label: String, length: Int, optional: Boolean = false): String {
        val chars = readChars(label, length, optional)
        return String(chars)
    }

    @Throws(IOException::class)
    fun peekInt(label: String, length: Int) =
        read(label, length).let {
            reader.unread(it.toCharArray())
            try {
                Integer.parseInt(it)
            } catch (e: NumberFormatException) {
                log.error("Could not convert segment '$this' into an integer, for the field '$label'")
                0
            }
        }

    @Throws(IOException::class)
    fun readInt(label: String, length: Int): Int {
        val segment = read(label, length)
        try {
            return Integer.parseInt(segment.trim())
        } catch (e: NumberFormatException) {
            log.error("Could not convert segment '$segment' into an integer, for the field '$label'")
        }

        return 0
    }

    @Throws(IOException::class)
    fun peekLong(label: String, length: Int) = readLong(label, length).apply { reader.unread(length) }

    @Throws(IOException::class)
    fun readLong(label: String, length: Int): Long {
        val segment = read(label, length)
        try {
            return java.lang.Long.parseLong(segment)
        } catch (e: NumberFormatException) {
            log.error("Could not convert segment '$segment' into a long, for the field '$label'")
        }

        return 0L
    }

    @Throws(IOException::class)
    fun peekChars(label: String, length: Int) = readChars(label, length).apply { reader.unread(length) }

    @Throws(IOException::class)
    private fun readChars(label: String, length: Int, optional: Boolean = false): CharArray {
        val chars = CharArray(length)
        val readChars = reader.read(chars)
        if (readChars < length) {
            if (optional) {
                chars.fill(' ')
            } else {
                throw EOFException("Not enough characters left to read $length characters from the field '$label'")
            }
        }
        return chars
    }
}
