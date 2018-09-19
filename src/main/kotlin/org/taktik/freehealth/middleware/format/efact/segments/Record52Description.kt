/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.taktik.freehealth.middleware.format.efact.segments

import java.util.LinkedHashMap

object Record52Description : RecordOrSegmentDescription() {
    private val ZONE_DESCRIPTIONS_BY_ZONE = LinkedHashMap<String, ZoneDescription>(19)

    override val zoneDescriptionsByZone: Map<String, ZoneDescription>
        get() = ZONE_DESCRIPTIONS_BY_ZONE

    init {
        var pos = 1

        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "1", "Enregistrement de type 52", "N", pos, 2, "52")
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "2", "Numero d'ordre de l'enregistrement", "N", pos, 6)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "3", "reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "4", "Code nomenclature", "N", pos, 7)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "5", "Date de prestation", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "6a,6b", "Date de lecture document identite electronique (1 et 2)", "N", pos, 8)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "7", "reserve", "N", pos, 3)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "8a,8b", "Numero NISS du patient sauf en cas de convention internationale ou nouveaux-nes (1 et 2)", "A", pos, 13)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "9", "reserve", "N", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "10", "Type de support document identite electronique", "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "11", "Type de lecture document identite electronique", "A", pos, 1)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "12,13", "Heure de lecture document identite electronique (1 et 2)", "N", pos, 4)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "14", "reserve", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "15", "Numero INAMI", "N", pos, 12)
        pos = register(ZONE_DESCRIPTIONS_BY_ZONE, "16", "reserve", "N", pos, 269)
              register(ZONE_DESCRIPTIONS_BY_ZONE, "99", "Chiffres de controle de l'enregistrement", "N", pos, 2, null, true)
    }
}
