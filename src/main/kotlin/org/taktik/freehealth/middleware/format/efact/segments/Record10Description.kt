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

object Record10Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(50)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "1", "EnregistrementType", "N", pos, 2, "10")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2", "NumeroOrdreEnregistrement", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3", "NombreNumerosComptesFinanciers", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4", "VersionFichier", "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5,6a", "NumeroCompteFinancierAPartie1et2", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6b", "Reserve", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "7", "NumeroDeLenvoi", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8a", "NumeroCompteFinancierB", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8b", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "9", "CodeSuppressionFacturePapier", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "10", "CodeFichierDeDecompte", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "11", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "12", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "13", "ContenuDeLaFacturation", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "14", "NumeroTiersPayant", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "15", "NumeroDaccreditationCin", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "16", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "17", "Reserve", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "18", "Reserve", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "19", "Reserve", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "20", "Reserve", "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "21", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "22", "AnneeDeFacturation", "N", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "23", "MoisDeFacturation", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "24", "Reserve", "N", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "25,26", "DateDeCreationPartie1et2", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "27", "BCE", "N", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "28", "ReferenceDeLetablissement", "A", pos, 25)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "29", "Reserve", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "30", "Reserve", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "31,32,33,34", "BicCompteFinancierAPartie1_2_3et4", "A", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "35", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "36, 37, 38, 39, 40, 41", "IbanCompteFinancierAPartie1_2_3_4_5et6", "A", pos, 34)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "42", "Reserve", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43a", "BicCompteFinancierB", "A", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43b", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "44", "Reserve", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "45", "Reserve", "N", pos, 26)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "46", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "47", "Reserve", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "48", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "49,50,51,52", "IbanCompteFinancierBPartie1_2_3et4", "A", pos, 34)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "53", "Reserve", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "54", "Reserve", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "55", "Reserve", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "56", "Reserve", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "57", "Reserve", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "58", "Reserve", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "59", "Reserve", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "98", "Reserve", "N", pos, 2)
    }
}
