package org.taktik.freehealth.middleware.dto.efact.segments

import java.util.LinkedHashMap

object Segment200Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(65)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "200", "Type",  "N", pos, 6, "920000")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2001", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "201", "N version format message premiere version 01", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2011", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "202", "Type message 12 prod/92 test","N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2021", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "203", "Statut message = code erreur si erreur !! IN - OUT !!", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2031", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "204", "Reference numerique message prestataire", "N", pos, 14)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2041", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "205", "Reference message OA", "N", pos, 14)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2051", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "206", "reserve", "N", pos, 15)
    }

}
