package org.taktik.freehealth.middleware.format.efact.segments

import java.util.LinkedHashMap

object Segment500Record92Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(65)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "500", "Type", "recordType", "N", pos, 2, "92")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5001", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "501", "Lien T10 N mutualit", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5011", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "502", "n facture recapitulative", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5021", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "503", "Signe montant demand compte A", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "504", "Montant demand compte A", "askedAmountAccount1", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5041", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "505", "Signe montant demand compte B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "506", "Montant demand compte B", "askedAmountAccount2", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5061", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "507", "Signe Montant demand A + B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "508", "Montant demand compte A + B = lien Cpt A", "totalAskedAmount", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5081", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "509", "Nombre d'enregistrement", "numberOfRecord", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5091", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "510", "Lien T80 Z98 N contrle par mutuelle", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5101", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "511", "Signe montant accepte compte A", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "512", "montant accepte compte A", "acceptedAmountAccount1", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5121", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "513", "Signe montant refuse compte A", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "514", "montant refuse compte A", "rejectedAmountAccount1", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5141", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "515", "Signe montant accepte compte B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "516", "Montant accepte compte B", "acceptedAmountAccount2", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5161", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "517", "Signe montant refuse compte B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "518", "Montant refuse compte B", "rejectedAmountAccount2", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5181", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "519", "Signe total montants acceptes compte A + compte B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "520", "Total montants acceptes compte A+compte B", "totalAcceptedAmount", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5201", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "521", "Signe total montants refuses compte A + compte B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "522", "Total montants refuses compte A+compteB", "totalRejectedAmount", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5221", "Code erreur", null, "N", pos, 2)
              register(ZONE_DESCRIPTIONS_BY_ZONE, "523", "Reserve", null, "A", pos, 637)
    }

}
