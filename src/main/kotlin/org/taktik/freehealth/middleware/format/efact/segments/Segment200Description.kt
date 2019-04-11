package org.taktik.freehealth.middleware.format.efact.segments

import java.util.LinkedHashMap

object Segment200Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(65)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "200", "Type", "recordType", "N", pos, 6, "920000")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2001", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "201", "N version format message premiere version 01", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2011", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "202", "Type message 12 prod/92 test", "testMessage", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2021", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "203", "Statut message = code erreur si erreur !! IN - OUT !!", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2031", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "204", "Reference numerique message prestataire", "healthcarePartyMessageReferenceNumber", "N", pos, 14)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2041", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "205", "Reference message OA", null, "N", pos, 14)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2051", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "206", "reserve", null, "N", pos, 15)
    }

}
