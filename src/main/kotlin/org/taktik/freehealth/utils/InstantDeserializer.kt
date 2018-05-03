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
import java.math.BigDecimal
import java.time.Instant

class InstantDeserializer : JsonDeserializer<Instant>() {
    @Throws(IOException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): Instant {
        return getInstant(jp.decimalValue)
    }

    private fun getInstant(value: BigDecimal): Instant {
        return Instant.ofEpochSecond(
            value.divide(aThousand).toLong(),
            value.remainder(aThousand).multiply(aMillion).toLong()
        )
    }

    companion object {
        private val aMillion = BigDecimal.valueOf(1000000)
        private val aThousand = BigDecimal.valueOf(1000)
    }
}
