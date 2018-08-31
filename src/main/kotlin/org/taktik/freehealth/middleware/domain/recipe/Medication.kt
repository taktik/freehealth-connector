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

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.google.common.base.Joiner
import org.apache.commons.lang.StringUtils
import org.taktik.freehealth.middleware.dto.Code
import java.io.Serializable
import java.util.stream.Collectors

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
data class Medication(
    @Deprecated(
        "retained for backward compatibility with the db",
        ReplaceWith("compoundPrescriptionV2")
    ) var compoundPrescription: String? = null,
    var compoundPrescriptionV2: CompoundPrescription? = null,
    var substanceProduct: Substanceproduct? = null,
    var medicinalProduct: Medicinalproduct? = null,
    var numberOfPackages: Int? = null,
    /** vaccine batch number bought by the doctor: do not send to Recip-e */
    var batch: String? = null,
    @Deprecated("not supported in Recipe 1.19") var commentForDelivery: String? = null,
    var beginMoment: Long? = null,
    var endMoment: Long? = null,
    var temporality: Code? = null, // CD-TEMPORALITY
    var duration: Duration? = null,
    var knownUsage: Boolean? = null,
    var regimen: MutableList<RegimenItem>? = null,
    var renewal: MedicationRenewal? = null,
    var intakeRoute: Code? = null, // CD-DRUG-ROUTE
    var instructionForPatient: String? = null,
    var instructionsForReimbursement: ReimbursementInstructions? = null,
    var substitutionAllowed: Boolean? = null,
    var recipeInstructionForPatient: String? = null,
    var options: MutableMap<String, Content>? = null
) : Serializable {

    @JsonIgnore
    fun getPosology(language: String? = null): String? {
        val lang = language ?: "en"
        require(lang in listOf("fr", "nl", "en"), { "unknown language $lang" })
        if (knownUsage ?: false) {
            return mapOf("nl" to "gekend gebruik", "fr" to "usage connu", "en" to "known usage")[lang]
        }
        if (!StringUtils.isEmpty(instructionForPatient)) {
            return this.instructionForPatient
        }
        if (regimen == null || regimen!!.size == 0) {
            return null
        }

        var unit =
            regimen!![0].administratedQuantity?.administrationUnit?.code ?: regimen!![0].administratedQuantity?.unit
        var quantity = regimen!![0].administratedQuantity?.quantity

        for (ri in regimen!!.subList(1, regimen!!.size)) {
            val oUnit = ri.administratedQuantity?.administrationUnit?.code ?: ri.administratedQuantity?.unit
            val oQuantity = ri.administratedQuantity?.quantity

            if (!StringUtils.equals(unit, oUnit)) {
                unit = "take(s)"
            }
            if ((quantity == null && oQuantity != null) || (quantity != null && oQuantity == null) || (quantity != null && quantity != oQuantity)) {
                quantity = -1.0
            }
        }
        return String.format(
            "%s, %d x %s, %s",
            if (quantity == null || quantity == -1.0) "x" else quantity.toString(),
            regimen!!.size,
            "daily",
            Joiner.on(", ").skipNulls().join(
                regimen!!.stream().map(RegimenItem::toString).collect(Collectors.toList()) as Iterable<String>
            )
        )
    }

    companion object {
        const val REIMBURSED = "REIMBURSED"
    }
}
