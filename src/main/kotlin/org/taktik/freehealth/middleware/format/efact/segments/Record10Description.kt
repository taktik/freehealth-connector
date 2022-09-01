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

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "1", "EnregistrementType", "recordType", "N", pos, 2, "10")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2", "NumeroOrdreEnregistrement", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3", "NombreNumerosComptesFinanciers", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4", "VersionFichier", "fileVersion", "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5,6a", "NumeroCompteFinancierAPartie1et2", "financialAccountNumber1", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6b", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "7", "NumeroDeLenvoi", "sendingNumber", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8a", "NumeroCompteFinancierB", "financialAccountNumber2", "A", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8b", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "9", "CodeSuppressionFacturePapier", "deletionCodePaperInvoice", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "10", "CodeFichierDeDecompte", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "11", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "12", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "13", "ContenuDeLaFacturation", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "14", "NumeroTiersPayant", "thirdPartyNumber", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "15", "NumeroDaccreditationCin", "accreditationCinNumber", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "16", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "17", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "18", "Reserve", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "19", "Reserve", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "20", "Reserve", null, "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "21", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "22", "AnneeDeFacturation", "invoicingYear", "N", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "23", "MoisDeFacturation", "invoicingMonth", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "24", "Reserve", null, "N", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "25,26", "DateDeCreationPartie1et2", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "27", "BCE", null, "N", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "28", "ReferenceDeLetablissement", "invoiceReference", "A", pos, 25)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "29", "Reserve", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "30", "Reserve", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "31,32,33,34", "BicCompteFinancierAPartie1_2_3et4", "bic1", "A", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "35", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "36, 37, 38, 39, 40, 41", "IbanCompteFinancierAPartie1_2_3_4_5et6", "iban1", "A", pos, 34)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "42", "Reserve", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43a", "BicCompteFinancierB", "bic2", "A", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43b", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "44", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "45", "Reserve", null, "N", pos, 26)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "46", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "47", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "48", "Reserve", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "49,50,51,52", "IbanCompteFinancierBPartie1_2_3et4", "iban2", "A", pos, 34)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "53", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "54", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "55", "Reserve", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "56", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "57", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "58", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "59", "Reserve", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "98", "Reserve", null, "N", pos, 2)
              register(ZONE_DESCRIPTIONS_BY_ZONE, "99", "Chiffres de controle de l'enregistrement", null, "N", pos, 2, null, true)
    }
}
