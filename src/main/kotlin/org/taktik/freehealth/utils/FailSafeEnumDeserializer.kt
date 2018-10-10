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

package org.taktik.freehealth.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

import java.io.IOException
import com.fasterxml.jackson.core.JsonProcessingException


open class FailSafeEnumDeserializer<T : Enum<T>>(private val enumClass: Class<T>, private val defaultValue: T? = null) : JsonDeserializer<T>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): T? {
        val value = jp.text
        for (enumValue in enumClass.enumConstants) {
            if (enumValue.name == value) {
                return enumValue
            }
        }
        return defaultValue
    }
}

