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

            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "1", "EnregistrementType", null, "N", pos, 2, "30")
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2", "numero d'ordre de l'enregistrement", null, "N", pos, 6)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3", "norme journee d'entretien", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4", "pseudo-code journee d'entretien et forfait", null, "N", pos, 7)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5", "date premier jour facture", null, "N", pos, 8)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6a,6b", "date dernier jour facture (partie 1 et 2)", null, "N", pos, 8)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "7", "numero mutualite d'affiliation", null, "N", pos, 3)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8a,8b", "identification beneficiaire (partie 1 et 2)", null, "N", pos, 13)  //Forced to N so that it is padded with 0s
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "9", "sexe beneficiaire", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "10", "accouchement", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "11", "reference numero de compte financier", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "12", "reserve", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "13", "code service", null, "N", pos, 3)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "14", "lieu de prestation", null, "N", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "15", "identification convention/etablissement de sejour", null, "N", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "16", "reserve", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "17,18", "prestation relative (partie 1 et 2)", null, "N", pos, 7)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "19", "signe + montant intervention de l'assurance", null, "A", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "20", "reserve", null, "N", pos, 7)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "21", "reserve", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "22", "signe + nombre de jours ou forfaits", null, "A", pos, 5)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "23", "reserve", null, "N", pos, 2)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "24,25", "signe + montant indicatif ordre de grandeur frais de sejour (partie 1 et 2)", null, "A", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "26", "reserve", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "27", "signe + intervention personnelle patient", null, "A", pos, 10)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "28", "reference de l'etablissement", null, "A", pos, 25)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "29", "reserve", null, "N", pos, 2)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "30,31", "signe + montant supplement (partie 1 et 2)", null, "N", pos, 10)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "32", "exception tiers payant", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "33", "code facturation intervention personnelle ou supplement", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "34", "reserve", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "35", "reserve", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "36", "reserve", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "37", "reserve", null, "N", pos, 3)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "38", "reserve", null, "N", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "39", "reserve", null, "N", pos, 10)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "40", "reserve", null, "N", pos, 2)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "41", "reserve", null, "N", pos, 6)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "42", "reserve", null, "N", pos, 6)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43a", "reserve", null, "N", pos, 11)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43b", "reserve", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "44", "reserve", null, "N", pos, 4)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "45", "reserve", null, "N", pos, 26)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "46", "reserve", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "47", "date accord prestation", null, "N", pos, 8)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "48", "transplantation", null, "N", pos, 1)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "49", "reserve", null, "N", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "50", "reserve", null, "N", pos, 4)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "51", "site hospitalier", null, "N", pos, 6)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "52", "identification association bassin de soins", null, "N", pos, 12)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "53", "reserve", null, "N", pos, 8)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "54a", "reserve", null, "N", pos, 3)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "54b", "reserve", null, "N", pos, 5)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "55", "reserve", null, "N", pos, 8)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "56", "reserve", null, "N", pos, 4)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "57", "reserve", null, "N", pos, 4)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "58", "reserve", null, "N", pos, 4)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "59", "reserve", null, "N", pos, 6)
            pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "98", "reserve", null, "N", pos, 2)
                  register(ZONE_DESCRIPTIONS_BY_ZONE, "99", "Chiffres de controle de l'enregistrement", null, "N", pos, 2, null, true)
        }

}
