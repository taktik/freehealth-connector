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

object Record20Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(50)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "1,", "EnregistrementDeType20", "N", pos, 2, "20")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2", "NumeroDordreDeLenregistrement", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3", "AutorisationTiersPayant", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4", "HeureDadmission", "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5", "DateDadmission", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6a", "DateDeSortiePartie1", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6b", "DateDeSortiePartie2", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "7", "NumeroMutualiteDaffiliation", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8a,8b", "IdentificationBeneficiairePartie1et2", "N", pos, 13)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "9", "SexeBeneficiaire", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "10", "TypeFacture", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "11", "TypeDeFacturation", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "12", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "13", "Service721Bis", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "14", "NumeroDeLetablissementQuiFacture", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "15", "EtablissementDeSejour", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "16", "CodeLeveeDelaiDePrescription", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "17", "CausesDuTraitement", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "18", "NumeroMutualiteDeDestination", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "19", "NumeroDadmission", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "20,21", "DateAccordTraitementPartie1et2", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "22", "HeureDeSortie", "N", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "23", "Reserve", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "24,25", "NumeroDeLaFactureIndividuellePartie1et2", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "26", "ApplicationFranchiseSociale", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "27", "Ct1Ct2", "N", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "28", "ReferenceDeLetablissement", "A", pos, 25)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "29,30,31", "NumeroDeFacturePrecedentePartie1_2et3", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "32", "FlagIdentificationDuBeneficiaire", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "33", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "34,35,36", "NumeroEnvoiPrecedentPartie1_2et3", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "37", "NumeroMutualiteFacturationPrecedente", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "38,39", "ReferenceMutualiteNumeroDeCompteFinancierAPartie1et2", "A", pos, 22)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "40", "Reserve", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "41", "AnneeEtMoisDeFacturationPrecedente", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "42,43a,43b,44,45", "DonneesDeReferenceReseauOuCarteSisPartie1_2_3_4et5", "N", pos, 48)  //Forced to N so that it is padded with 0s
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "46", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "47", "Date de facturation", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "48", "Reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "49,50,51", "ReferenceMutualiteNumeroCompteFinancierBPartie1_2et3", "A", pos, 22)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "52", "Reserve", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "53", "DateDebutAssurabilite", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "54a,54b", "DateFinAssurabilite", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "55", "DateCommunicationInformation", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "56", "MafAnneeEnCours", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "57", "MafAnneeEnCours1", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "58", "MafAnneeEnCours2", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "59", "Reserve", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "98", "Reserve", "N", pos, 2, null, true)
    }

}
