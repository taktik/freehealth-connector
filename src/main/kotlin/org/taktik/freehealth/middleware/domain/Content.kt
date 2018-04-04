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

package org.taktik.freehealth.middleware.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.taktik.freehealth.utils.InstantDeserializer
import org.taktik.freehealth.utils.InstantSerializer
import java.io.Serializable
import java.time.Instant


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
class Content() : Serializable {
    @JsonProperty("s")
    var stringValue: String? = null

    @JsonProperty("n")
    var numberValue: Double? = null

    @JsonProperty("b")
    var booleanValue: Boolean? = null

    @JsonProperty("i")
    @JsonSerialize(using = InstantSerializer::class, include = JsonSerialize.Inclusion.NON_NULL)
    @JsonDeserialize(using = InstantDeserializer::class)
    var instantValue: Instant? = null

    @JsonProperty("x")
    var binaryValue: ByteArray? = null

    @JsonProperty("d")
    var documentId: String? = null

    @JsonProperty("m")
    var measureValue: Measure? = null

    @JsonProperty("p")
    var medicationValue: Medication? = null

    constructor(stringValue: String) : this() {
        this.stringValue = stringValue
    }

    constructor(numberValue: Double?) : this()  {
        this.numberValue = numberValue
    }

    constructor(booleanValue: Boolean?) : this()  {
        this.booleanValue = booleanValue
    }

    constructor(instantValue: Instant) : this()  {
        this.instantValue = instantValue
    }

    constructor(measureValue: Measure) : this()  {
        this.measureValue = measureValue
    }

    constructor(binaryValue: ByteArray) : this()  {
        this.binaryValue = binaryValue
    }

    constructor(medicationValue: Medication) : this()  {
        this.medicationValue = medicationValue
    }


}
