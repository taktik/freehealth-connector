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

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "1", "EnregistrementDeType80", "recordType", "N", pos, 2, "80")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2", "NumeroDordreDeLenregistrement", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4", "HeureDadmission", null, "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5", "DateDadmission", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6a,6b", "DateDeSortiePartie1et2", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "7", "NumeroMutualiteDaffiliation", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8a,8b", "IdentificationBeneficiairePartie1", "recipientIdentifier", "N", pos, 13)  //Forced to A so that it is padded with blank spaces
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "9", "SexeBeneficiaire", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "10", "TypeFacture", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "11", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "12", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "13", "Service721Bis", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "14", "NumeroDeLetablissementQuiFacture", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "15", "SigneMontantDeCompteFinancierB", null, "A", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "16", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "17", "CausesDuTraitement", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "18", "NumeroMutualiteDeDestination", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "19", "SigneMontantDeCompteFinancierA", null, "A", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "20,21", "DateDeLaFacturePartie1et2", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "22", "HeureDeSortie", null, "N", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "23", "Reserve", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "24,25", "NumeroDeLaFactureIndividuellePartie1et2", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "26", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "27", "SigneInterventionPersonnellePatient", null, "A", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "28", "ReferenceDeLetablissement", null, "A", pos, 25)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "29", "Reserve", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "30,31", "Z27SigneMontantSupplementPartie1et2", null, "A", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "32", "FlagIdentificationDuBeneficiaire", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "33", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "34", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "35", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "36", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "37", "Reserve", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "38", "SigneAcompteNumeroCompteFinancierA", "", "A", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "39", "Reserve", null, "N", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "40", "Reserve", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "41", "Reserve", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "42", "Reserve", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43a", "Reserve", null, "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43b", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "44", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "45", "Reserve", null, "N", pos, 26)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "46", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "47", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "48", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "49", "Reserve", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "50", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "51", "Reserve", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "52", "Reserve", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "53", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "54a,54b", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "55", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "56", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "57", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "58", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "59", "Reserve", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "98", "chiffres de controle de la facture", null, "N", pos, 2)
              register(ZONE_DESCRIPTIONS_BY_ZONE, "99", "Chiffres de controle de l'enregistrement", null, "N", pos, 2, null, true)
    }

}
