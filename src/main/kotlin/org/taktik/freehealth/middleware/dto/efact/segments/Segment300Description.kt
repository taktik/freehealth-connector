package org.taktik.freehealth.middleware.dto.efact.segments

import java.util.LinkedHashMap

object Segment300Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(65)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "300", "Lien T10 Z22&23 Anne et mois facturation", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3001", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "301", "Lien T10 Z7 Numro d'envoi", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3011", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "302", "Lien T10 Z25 Date cration facture", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3021", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "303", "Refrence facture","A", pos, 13)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3031", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "304", "Lien T10 Z4 Numro version instructions 0001999 production / 9991999 test",  "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3041", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "305", "Nom personne contact", "A", pos, 45)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3051", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "306", "Prnom personne de contact", "A", pos, 24)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3061", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "307", "Numro telephone personne contact", "N", pos, 10)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3071", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "308", "Type de facture : 01 hospit / 03 ambulatoire / 09 mixte", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3081", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "309", "Type facturation : 01 1 fichier 1 compte / 02 1 fichier 2 comptes", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3091", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "310", "reserve", "N", pos, 20)
    }

    override fun toString(): String {
        return "300"
    }
}
