package org.taktik.freehealth.middleware.format.efact.segments

import java.util.LinkedHashMap

object Segment400Record91Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(65)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "400", "Type", "recordType", "N", pos, 2, "91")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4001", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "401", "Lien T10 N mutualit", null, "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4011", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "402", "n facture recapitulative", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4021", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "403", "Signe montant demand compte A", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "404", "Montant demand compte A", "askedAmountForAccount1", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4041", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "405", "Signe montant demand compte B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "406", "Montant demand compte B", "askedAmountForAccount2", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4061", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "407", "Signe Montant demand A + B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "408", "Montant demand compte A + B = lien Cpt A", "totalAskedAmount", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4081", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "409", "Nombre d'enregistrement", "numberOfRecord", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4091", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "410", "Lien T80 Z98 N contrle par mutuelle", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4101", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "411", "Signe montant accepte compte A", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "412", "montant accepte compte A", "acceptedAmountAccount1", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4121", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "413", "Signe montant refuse compte A", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "414", "montant refuse compte A", "rejectedAmountAccount1", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4141", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "415", "Signe montant accepte compte B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "416", "Montant accepte compte B", "acceptedAmountAccount2", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4161", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "417", "Signe montant refuse compte B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "418", "Montant refuse compte B", "rejectedAmountAccount2", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4181", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "419", "Signe total montants acceptes compte A + compte B", "totalAcceptedAmount", "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "420", "Total montants acceptes compte A+compte B", null, "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4201", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "421", "Signe total montants refuses compte A + compte B", "totalRejectedAmount", "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "422", "Total montants refuses compte A+compteB", null, "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4221", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "423", "Reference paiement compte A OA ou mutualite", "paymentReferenceAccount1", "A", pos, 22)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4231", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "424", "Reference paiement compte B OA ou mutualite", "paymentReferenceAccount2", "A", pos, 22)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4241", "Code erreur", null, "N", pos, 2)
              register(ZONE_DESCRIPTIONS_BY_ZONE, "425", "Reserve", null, "A", pos, 589)    }

}
