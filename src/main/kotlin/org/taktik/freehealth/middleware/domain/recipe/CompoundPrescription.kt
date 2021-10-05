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

package org.taktik.freehealth.middleware.domain.recipe

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.utils.CompoundPrescriptionDeserializer

@JsonDeserialize(using = CompoundPrescriptionDeserializer::class)
sealed class CompoundPrescription(var galenicForm : GalenicForm? = null, var quantity : KmehrQuantity? = null) {

    @JsonDeserialize(using = JsonDeserializer.None::class)
    data class Compounds(var compounds : MutableList<Compound> = mutableListOf()) : CompoundPrescription()
    @JsonDeserialize(using = JsonDeserializer.None::class)
    data class MagistralText(var text : String? = null) : CompoundPrescription()

    @JsonDeserialize(using = JsonDeserializer.None::class)
    sealed class FormularyReference : CompoundPrescription() {
        data class FormularyName(var name: String? = null) : FormularyReference()
        data class Formulary(var formularyId : String? = null /* CD-FORMULARY */, var reference : Code? = null /* CD-FORMULARYREFERENCE */) : FormularyReference()
    }
}

