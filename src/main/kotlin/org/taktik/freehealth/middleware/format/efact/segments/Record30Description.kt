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

package org.taktik.freehealth.middleware.format.efact.segments

import java.util.LinkedHashMap

object Record30Description : RecordOrSegmentDescription() {

    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(51)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

        init {
            var pos = 1

            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "1", "EnregistrementType", "N", pos, 2, "30")
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2", "numero d'ordre de l'enregistrement", "N", pos, 6)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3", "norme journee d'entretien", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4", "pseudo-code journee d'entretien et forfait", "N", pos, 7)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5", "date premier jour facture", "N", pos, 8)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6a,6b", "date dernier jour facture (partie 1 et 2)", "N", pos, 8)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "7", "numero mutualite d'affiliation", "N", pos, 3)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8a,8b", "identification beneficiaire (partie 1 et 2)", "A", pos, 13)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "9", "sexe beneficiaire", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "10", "accouchement", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "11", "reference numero de compte financier", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "12", "reserve", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "13", "code service", "N", pos, 3)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "14", "lieu de prestation", "N", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "15", "identification convention/etablissement de sejour", "N", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "16", "reserve", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "17,18", "prestation relative (partie 1 et 2)", "N", pos, 7)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "19", "signe + montant intervention de l'assurance", "A", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "20", "reserve", "N", pos, 7)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "21", "reserve", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "22", "signe + nombre de jours ou forfaits", "A", pos, 5)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "23", "reserve", "N", pos, 2)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "24,25", "signe + montant indicatif ordre de grandeur frais de sejour (partie 1 et 2)", "A", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "26", "reserve", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "27", "signe + intervention personnelle patient", "A", pos, 10)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "28", "reference de l'etablissement", "A", pos, 25)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "29", "reserve", "N", pos, 2)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "30,31", "signe + montant supplement (partie 1 et 2)", "N", pos, 10)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "32", "exception tiers payant", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "33", "code facturation intervention personnelle ou supplement", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "34", "reserve", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "35", "reserve", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "36", "reserve", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "37", "reserve", "N", pos, 3)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "38", "reserve", "N", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "39", "reserve", "N", pos, 10)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "40", "reserve", "N", pos, 2)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "41", "reserve", "N", pos, 6)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "42", "reserve", "N", pos, 6)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43a", "reserve", "N", pos, 11)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43b", "reserve", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "44", "reserve", "N", pos, 4)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "45", "reserve", "N", pos, 26)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "46", "reserve", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "47", "date accord prestation", "N", pos, 8)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "48", "transplantation", "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "49", "reserve", "N", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "50", "reserve", "N", pos, 4)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "51", "site hospitalier", "N", pos, 6)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "52", "identification association bassin de soins", "N", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "53", "reserve", "N", pos, 8)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "54a", "reserve", "N", pos, 3)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "54b", "reserve", "N", pos, 5)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "55", "reserve", "N", pos, 8)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "56", "reserve", "N", pos, 4)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "57", "reserve", "N", pos, 4)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "58", "reserve", "N", pos, 4)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "59", "reserve", "N", pos, 6)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "98", "reserve", "N", pos, 2)
                  register(ZONE_DESCRIPTIONS_BY_ZONE, "99", "Chiffres de controle de l'enregistrement", "N", pos, 2)
        }

}
