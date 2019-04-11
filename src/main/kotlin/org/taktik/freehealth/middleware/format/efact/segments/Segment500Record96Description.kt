package org.taktik.freehealth.middleware.format.efact.segments

import java.util.LinkedHashMap

object Segment500Record96Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(65)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "500", "Type", "recordType", "N", pos, 2, "96")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5001", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "501", "Lien T10 N mutualit", "mutualityNumber", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5011", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "502", "not used", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5021", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "503", "Signe montant demand compte A", "signAmount1", "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "504", "Montant demand compte A", "askedAmount1", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5041", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "505", "Signe montant demand compte B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "506", "Montant demand compte B", null, "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5061", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "507", "Signe Montant demand A + B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "508", "Montant demand compte A + B = lien Cpt A", null, "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5081", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "509", "Nombre d'enregistrement", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5091", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "510", "Lien T90 Z98 N controle envoi", "mutualityControlNumber", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5101", "Code erreur", null, "N", pos, 2)
              register(ZONE_DESCRIPTIONS_BY_ZONE, "511", "Reserve", null, "A", pos, 271, null, false, true)
    }

}
