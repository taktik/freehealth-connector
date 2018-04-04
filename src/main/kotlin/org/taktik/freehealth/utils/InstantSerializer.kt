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

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

import java.io.IOException
import java.math.BigDecimal
import java.time.Instant

class InstantSerializer : JsonSerializer<Instant>() {
    @Throws(IOException::class)
    override fun serialize(value: Instant, jgen: JsonGenerator, provider: SerializerProvider) {
        jgen.writeNumber(getBigDecimal(value))
    }

    private fun getBigDecimal(value: Instant): BigDecimal {
        return BigDecimal.valueOf(1000L * value.epochSecond).add(BigDecimal.valueOf(value.nano.toLong()).divide(aMillion))
    }

    override fun isEmpty(provider: SerializerProvider,  value: Instant?): Boolean {
        return value == null
    }

    companion object {
        private val aMillion = BigDecimal.valueOf(1000000)
    }
}
