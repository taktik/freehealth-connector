package org.taktik.freehealth.middleware.format.efact.segments

import java.util.LinkedHashMap

object Segment300StubDescription : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(65)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "300", "Nom du message", "messageType", "A", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3001", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "301", "Reserve", null, "A", pos, 152)
    }

    override fun toString(): String {
        return "300"
    }

}
