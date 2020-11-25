package org.taktik.connector.business.recipe.utils

import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDPERIODICITY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTIMEUNIT
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDTIMEUNITschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.FrequencyType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.PeriodicityType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipedurationType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.RecipefrequencyType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.TimequantityType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.TimeunitType
import org.taktik.freehealth.middleware.domain.recipe.Duration
import java.math.BigDecimal
import java.time.temporal.ChronoUnit

fun toCdTimeUnit(chronoUnit: ChronoUnit): String? {
    return when (chronoUnit) {
        // when body generated with
        // perl -ne 'if (/^(\w+)\s+(\w+?)(:?second)?\s*$/i) { $unit = uc $2 ; print "ChronoUnit.${unit}S -> \"$1\"\n" }' tmp.txt
        // tmp.txt contains the copy of https://www.ehealth.fgov.be/standards/kmehr/content/page/tables/244/time-unit
        ChronoUnit.YEARS -> "a"
        ChronoUnit.MONTHS -> "mo"
        ChronoUnit.WEEKS -> "wk"
        ChronoUnit.DAYS -> "d"
        ChronoUnit.HOURS -> "hr"
        ChronoUnit.MINUTES -> "min"
        ChronoUnit.SECONDS -> "s"
        ChronoUnit.MILLIS -> "ms"
        ChronoUnit.MICROS -> "us"
        ChronoUnit.NANOS -> "ns"
        else -> null
    }
}

