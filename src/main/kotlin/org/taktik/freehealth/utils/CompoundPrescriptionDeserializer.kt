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
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.boot.jackson.JsonObjectDeserializer
import org.taktik.freehealth.middleware.domain.recipe.CompoundPrescription
import org.taktik.icure.fhir.entities.r4.Resource

import java.io.IOException
import java.math.BigDecimal
import java.time.Instant

class CompoundPrescriptionDeserializer : JsonObjectDeserializer<CompoundPrescription>() {
    override fun deserializeObject(jsonParser: JsonParser,
                                   context: DeserializationContext,
                                   codec: ObjectCodec,
                                   tree: JsonNode
    ): CompoundPrescription = when {
        tree.has("compounds") -> codec.treeToValue(tree, CompoundPrescription.Compounds::class.java)
        tree.has("text") -> codec.treeToValue(tree, CompoundPrescription.MagistralText::class.java)
        tree.has("name") -> codec.treeToValue(tree, CompoundPrescription.FormularyReference.FormularyName::class.java)
        tree.has("formularyId") -> codec.treeToValue(tree, CompoundPrescription.FormularyReference.Formulary::class.java)
        else -> throw IllegalArgumentException("Any of compounds, text, name or formularyId must be present in a CompoundPrescription")
    }
}
