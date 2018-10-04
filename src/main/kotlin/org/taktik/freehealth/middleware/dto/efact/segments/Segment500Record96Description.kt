package org.taktik.freehealth.middleware.dto.efact.segments

import java.util.LinkedHashMap

object Segment500Record96Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(65)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "500", "Type", "N", pos, 2, "96")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5001", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "501", "Lien T10 N mutualit", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5011", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "502", "not used",  "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5021", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "503", "Signe montant demand compte A", "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "504", "Montant demand compte A", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5041", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "505", "Signe montant demand compte B", "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "506", "Montant demand compte B", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5061", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "507", "Signe Montant demand A + B", "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "508", "Montant demand compte A + B = lien Cpt A", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5081", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "509", "Nombre d'enregistrement", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5091", "Code erreur", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "510", "Lien T90 Z98 N controle envoi", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5101", "Code erreur", "N", pos, 2)
              register(ZONE_DESCRIPTIONS_BY_ZONE, "511", "Reserve", "A", pos, 271)
    }

}
