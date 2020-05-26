package org.taktik.connector.business.recipe.utils

import java.time.temporal.ChronoUnit

data class Period(val unit: ChronoUnit, val amount: Long) {
    private val supportedUnits = ChronoUnit.values().filter { it >= ChronoUnit.SECONDS }

    fun toBiggestTimeUnit(): Period {
        val seconds = toUnit(ChronoUnit.SECONDS).amount
        val biggestUnit = supportedUnits.reversed().first { (seconds % it.duration.seconds) == 0L }
        return toUnit(biggestUnit)
    }

    fun toUnit(unit: ChronoUnit): Period {
        require(supportedUnits.contains(unit), { "$unit not in $supportedUnits" })
        val seconds = amount * this.unit.duration.seconds
        return Period(unit, seconds / unit.duration.seconds)
    }
}
