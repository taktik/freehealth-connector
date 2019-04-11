package org.taktik.freehealth.middleware.format.efact.segments

import java.util.LinkedHashMap

object Segment400Record95Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(65)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "400", "Type", "recordType", "N", pos, 2, "95")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4001", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "401", "Lien T10 N mutualit", "mutualityNumber", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4011", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "402", "n facture recapitulative", null, "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4021", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "403", "Signe montant demand compte A", "signAmount1", "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "404", "Montant demand compte A", "askedAmount1", "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4041", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "405", "Signe montant demand compte B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "406", "Montant demand compte B", null, "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4061", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "407", "Signe Montant demand A + B", null, "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "408", "Montant demand compte A + B = lien Cpt A", null, "N", pos, 11)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4081", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "409", "Nombre d'enregistrement", null, "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4091", "Code erreur", null, "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "410", "Lien T80 Z98 N controle par mutuelle", "mutualityControlNumber", "N", pos, 2)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4101", "Code erreur", null, "N", pos, 2)
              register(ZONE_DESCRIPTIONS_BY_ZONE, "411", "Reserve", null, "A", pos, 271)
    }

}
