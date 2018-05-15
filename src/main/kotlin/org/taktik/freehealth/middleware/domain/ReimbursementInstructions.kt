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

enum class ReimbursementInstructions(val translations: Map<String, String>) {
    PAYING_THIRD_PARTY(mapOf("fr" to "Tiers-payant applicable", "nl" to "Derdebetalersregeling van toepassing")),
    FIRST_DOSE(mapOf("fr" to "1ère dose", "nl" to "1ste dosis")),
    SECOND_DOSE(mapOf("fr" to "2ème dose", "nl" to "2de dosis")),
    THIRD_DOSE(mapOf("fr" to "3ème dose", "nl" to "3de dosis")),
    CHRONIC_KINDEY_DISEASE(
        mapOf(
            "fr" to "Trajet de soins insuffisance rénale chronique",
            "nl" to "Zorgtraject chronische nierinsufficiëntie"
        )
    ),
    DIABETES_TREATMENT(mapOf("fr" to "Trajet de soins diabète", "nl" to "Zorgtraject diabetes")),
    DIABETES_CONVENTION(mapOf("fr" to "Convention diabète", "nl" to "Diabetesconventie")),
    NOT_REIMBURSABLE(mapOf("fr" to "Non remboursable", "nl" to "Niet-vergoedbaar"))
}
