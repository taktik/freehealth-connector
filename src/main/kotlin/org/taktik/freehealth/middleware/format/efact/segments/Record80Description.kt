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

object Record80Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(65)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "1", "EnregistrementDeType80", "N", pos, 2, "80")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2", "NumeroDordreDeLenregistrement", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4", "HeureDadmission", "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5", "DateDadmission", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6a,6b", "DateDeSortiePartie1et2", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "7", "NumeroMutualiteDaffiliation", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8a,8b", "IdentificationBeneficiairePartie1", "N", pos, 13)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "9", "SexeBeneficiaire", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "10", "TypeFacture", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "11", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "12", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "13", "Service721Bis", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "14", "NumeroDeLetablissementQuiFacture", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "15", "SigneMontantDeCompteFinancierB", "A", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "16", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "17", "CausesDuTraitement", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "18", "NumeroMutualiteDeDestination", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "19", "SigneMontantDeCompteFinancierA", "A", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "20,21", "DateDeLaFacturePartie1et2", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "22", "HeureDeSortie", "N", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "23", "Reserve", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "24,25", "NumeroDeLaFactureIndividuellePartie1et2", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "26", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "27", "SigneInterventionPersonnellePatient", "A", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "28", "ReferenceDeLetablissement", "A", pos, 25)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "29", "Reserve", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "30,31", "Z27SigneMontantSupplementPartie1et2", "A", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "32", "FlagIdentificationDuBeneficiaire", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "33", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "34", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "35", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "36", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "37", "Reserve", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "38", "SigneAcompteNumeroCompteFinancierA", "A", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "39", "Reserve", "N", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "40", "Reserve", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "41", "Reserve", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "42", "Reserve", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43a", "Reserve", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43b", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "44", "Reserve", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "45", "Reserve", "N", pos, 26)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "46", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "47", "Reserve", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "48", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "49", "Reserve", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "50", "Reserve", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "51", "Reserve", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "52", "Reserve", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "53", "Reserve", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "54a,54b", "Reserve", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "55", "Reserve", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "56", "Reserve", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "57", "Reserve", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "58", "Reserve", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "59", "Reserve", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "98", "chiffres de controle de la facture", "N", pos, 2)
              register(ZONE_DESCRIPTIONS_BY_ZONE, "99", "Chiffres de controle de l'enregistrement", "N", pos, 2, null, true)
    }

}
