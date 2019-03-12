package org.taktik.freehealth.middleware.format.efact.segments

import java.util.LinkedHashMap

object Segment300ErrorDescription : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(65)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "300", "Lien T10 Z22&23 Anne et mois facturation", null, "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3001", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "301", "Lien T10 Z7 Numro d'envoi", "sendingNumber", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3011", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "302", "Lien T10 Z25 Date cration facture", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3021", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "303", "Reference facture", "invoiceReference", "A", pos, 13)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3031", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "304", "Lien T10 Z4 Numro version instructions", null, "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3041", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "305", "Nom personne contact OA", "oaContactLastName", "A", pos, 45)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3051", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "306", "Prenom personne de contact OA", "oaContactFirstName", "A", pos, 24)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3061", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "307", "Numero telephone personne contact OA", "oaContactPhoneNumber", "N", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3071", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "308", "Type de facture", "invoiceType", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3081", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "309", "Mode facturation", "invoiceMode", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3091", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "310", "Pourcentage d'erreur", "errorPercentage", "N", pos, 5)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3101", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "311", "Type refus facturation", "invoiceRejectionType", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3111", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "312", "reserve", null, "N", pos, 459)
    }

    override fun toString(): String {
        return "300"
    }

}
