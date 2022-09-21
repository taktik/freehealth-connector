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

object Record50Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(50)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE


    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "1", "EnregistrementDeType50", "recordType", "N", pos, 2, "50")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2", "NumeroDordreDeLenregistrement", "recordOrderNumber", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3", "NormePrestationPourcentage", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4", "CodeNomenclatureOuPseudoCodeNomenclature", null, "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5", "DatePremierePrestationEffectuee", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6a,6b", "DateDernierePrestationEffectueePartie1et2", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "7", "NumeroMutualiteDaffiliation", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8a,8b", "IdentificationBeneficiairePartie1et2", null, "N", pos, 13)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "9", "SexeBeneficiaire", "sex", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "10", "Accouchement", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "11", "ReferenceNumeroDeCompteFinancier", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "12", "NuitWeekEndJourFerie", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "13", "CodeService", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "14", "LieuDePrestation", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "15", "IdentificationDuDispensateur", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "16", "NormeDispensateur", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "17,18", "PrestationRelativePartie1et2", null, "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "19", "SigneMontantInterventionDeLassurance", "montantInterventionAssurance", "A", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "20,21", "DatePrescriptionPartie1et2", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "22", "SigneNombreDunites", "units", "A", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "23", "DerogationNbrMaximalOuPrestationIdentique", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "24,25", "IdentificationPrescripteurPartie1et2", "prescriberNihii", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "26", "NormePrescripteur", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "27", "SigneInterventionPersonnellePatient", null, "A", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "28", "ReferenceDeLetablissement", "itemReference", "A", pos, 25)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "29", "DentTraitee", "tooth", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "30,31", "SigneMontantSupplementPartie1et2", null, "A", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "32", "ExceptionTiersPayant", "thirdPartyException", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "33", "CodeFacturationInterventionPersonnelleOuSupplement", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "34", "MembreTraite", "treatedMember", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "35", "PrestataireConventionne", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "36,37", "HeureDePrestationPartie1et2", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "38", "IdentificationAdministrateurDuSang", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "39,40", "NumeroDeLattestationDadministrationPartie1et2", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "41,42", "NumeroBonDeDelivranceOuSacPartie1et2", null, "N", pos, 12)   //Forced to N so that it is padded with 0s
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "43a,43b", "CodeImplantPartie1", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "44,45", "LibelleDuProduitPartie1et2", null, "A", pos, 30)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "46", "NormePlafond", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "47", "ValeurDeBasePrestation", null, "A", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "48", "Transplantation", null, "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "49", "identification de l'aide soignant", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "50", "Reserve", null, "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "51", "SiteHospitalier", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "52", "IdentificationAssociationBassinDeSoins", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "53,54a", "numero de course (partie 1 et 2)", null, "N", pos, 11)   //Forced to N so that it is padded with 0s
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "54b", "Reserve", null, "N", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "55,56", "CodeNotificationImplantPartie1et2", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "57,58,59", "code d'enregistrement Qermid (partie 1, 2 et 3)", null, "N", pos, 14)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "98", "reserve", null, "N", pos, 2)
              register(ZONE_DESCRIPTIONS_BY_ZONE, "99", "Chiffres de controle de l'enregistrement", null, "N", pos, 2, null, true)
    }

}
