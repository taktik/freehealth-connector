/*
 *
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.connector.business.recipe.utils

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl

import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDDAYPERIOD
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDDAYPERIODvalues
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDPERIODICITY
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTIMEUNIT
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTIMEUNITschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.DurationType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.FrequencyType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.PeriodicityType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.TimequantityType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.TimeunitType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGCNK
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGCNKschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDFORMULARY
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDFORMULARYschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDGALENICFORM
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDGALENICFORMschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDQUANTITYPREFIX
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDQUANTITYPREFIXvalues
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDSUBSTANCE
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDSUBSTANCEschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDUNIT
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDUNITschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.dt.v1.TextType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.CompoundType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.DayperiodType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.FormularyreferenceType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.GalenicformType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.ItemType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.MedicinalProductType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.QuantityType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.SubstanceType
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.UnitType
import org.taktik.connector.business.domain.kmehr.v20190301.makeXMLGregorianCalendarFromHHMMSSLong
import org.taktik.freehealth.middleware.domain.recipe.CompoundPrescription
import org.taktik.freehealth.middleware.domain.recipe.Duration
import org.taktik.freehealth.middleware.domain.recipe.GalenicForm
import org.taktik.freehealth.middleware.domain.recipe.KmehrQuantity
import org.taktik.freehealth.middleware.domain.recipe.RegimenItem
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.utils.FuzzyValues
import java.io.Serializable
import java.math.BigDecimal
import java.time.temporal.ChronoUnit
import java.time.temporal.ChronoUnit.DAYS
import java.time.temporal.ChronoUnit.SECONDS
import java.time.temporal.ChronoUnit.WEEKS
import javax.xml.bind.JAXBElement
import javax.xml.namespace.QName
import org.taktik.connector.business.domain.kmehr.v20190301.s

object KmehrPrescriptionHelperV4 {
    fun mapPeriodToFrequency(period: Period): FrequencyType {
        val frequency = FrequencyType()
        val periodCode = when (period.toBiggestTimeUnit()) {
            // when body generated with
            // perl -ne 'if (/^(\w+)\s+per\s+(\d+)\s+(\w+)/i) { $unit = uc $3 ; print "Period(ChronoUnit.$unit, $2) -> \"$1\"\n" }' tmp.txt
            // tmp.txt contains the copy of https://www.ehealth.fgov.be/standards/kmehr/content/page/tables/194/periodicity, edited to add 1 and "S" to singulars, convert half units to value in sub units
            Period(ChronoUnit.MINUTES, 30) -> "UH"
            Period(ChronoUnit.HOURS, 1) -> "U"
            Period(ChronoUnit.HOURS, 2) -> "UT"
            Period(ChronoUnit.HOURS, 3) -> "UD"
            Period(ChronoUnit.HOURS, 4) -> "UV"
            Period(ChronoUnit.HOURS, 5) -> "UQ"
            Period(ChronoUnit.HOURS, 6) -> "UZ"
            Period(ChronoUnit.HOURS, 7) -> "US"
            Period(ChronoUnit.HOURS, 8) -> "UA"
            Period(ChronoUnit.HOURS, 9) -> "UN"
            Period(ChronoUnit.HOURS, 10) -> "UX"
            Period(ChronoUnit.HOURS, 11) -> "UE"
            Period(ChronoUnit.HOURS, 12) -> "UW"
            Period(ChronoUnit.DAYS, 1) -> "D"
            Period(ChronoUnit.DAYS, 2) -> "DT"
            Period(ChronoUnit.DAYS, 3) -> "DD"
            Period(ChronoUnit.DAYS, 4) -> "DV"
            Period(ChronoUnit.DAYS, 5) -> "DQ"
            Period(ChronoUnit.DAYS, 6) -> "DZ"
            Period(ChronoUnit.WEEKS, 1) -> "W"
            Period(ChronoUnit.DAYS, 8) -> "DA"
            Period(ChronoUnit.DAYS, 9) -> "DN"
            Period(ChronoUnit.DAYS, 10) -> "DX"
            Period(ChronoUnit.DAYS, 11) -> "DE"
            Period(ChronoUnit.DAYS, 12) -> "DW"
            Period(ChronoUnit.WEEKS, 2) -> "WT"
            Period(ChronoUnit.WEEKS, 3) -> "WD"
            Period(ChronoUnit.WEEKS, 4) -> "WV"
            Period(ChronoUnit.MONTHS, 1) -> "M"
            Period(ChronoUnit.WEEKS, 5) -> "WQ"
            Period(ChronoUnit.WEEKS, 6) -> "WZ"
            Period(ChronoUnit.WEEKS, 7) -> "WS"
            Period(ChronoUnit.WEEKS, 8) -> "WA"
            Period(ChronoUnit.MONTHS, 2) -> "MT"
            Period(ChronoUnit.WEEKS, 9) -> "WN"
            Period(ChronoUnit.WEEKS, 10) -> "WX"
            Period(ChronoUnit.WEEKS, 11) -> "WE"
            Period(ChronoUnit.WEEKS, 12) -> "WW"
            Period(ChronoUnit.MONTHS, 3) -> "MD"
            Period(ChronoUnit.MONTHS, 4) -> "MV"
            Period(ChronoUnit.MONTHS, 5) -> "MQ"
            Period(ChronoUnit.WEEKS, 24) -> "WP"
            Period(ChronoUnit.DAYS, 183) -> "JH2"
            Period(ChronoUnit.MONTHS, 6) -> "MZ2"
            Period(ChronoUnit.MONTHS, 7) -> "MS"
            Period(ChronoUnit.MONTHS, 8) -> "MA"
            Period(ChronoUnit.MONTHS, 9) -> "MN"
            Period(ChronoUnit.MONTHS, 10) -> "MX"
            Period(ChronoUnit.MONTHS, 11) -> "ME"
            Period(ChronoUnit.YEARS, 1) -> "J"
            Period(ChronoUnit.MONTHS, 18) -> "MC"
            Period(ChronoUnit.YEARS, 2) -> "JT"
            Period(ChronoUnit.YEARS, 3) -> "JD"
            Period(ChronoUnit.YEARS, 4) -> "JV"
            Period(ChronoUnit.YEARS, 5) -> "JQ"
            Period(ChronoUnit.YEARS, 6) -> "JZ"
            else -> null
        }
        if (periodCode != null) {
            frequency.periodicity = PeriodicityType().apply { cd = CDPERIODICITY().apply { s = "CD-PERIODICITY"; value = periodCode } }
        } else {
            val timeUnit = toCdTimeUnit(period.unit)
            val actualTimeUnit = timeUnit ?: toCdTimeUnit(ChronoUnit.YEARS)
            val actualAmount = if (timeUnit != null) period.amount else period.toUnit(ChronoUnit.YEARS).amount
            frequency.apply {
                nominator = FrequencyType.Nominator().apply {
                    quantity = TimequantityType().apply {
                        decimal = BigDecimal(actualAmount)
                        unit = TimeunitType().apply {
                            cd = CDTIMEUNIT().apply { s(CDTIMEUNITschemes.CD_TIMEUNIT); value = actualTimeUnit }
                        }
                    }
                }
                denominator = FrequencyType.Denominator().apply {
                    quantity = TimequantityType().apply {
                        decimal = BigDecimal.ONE
                        unit = TimeunitType().apply {
                            cd = CDTIMEUNIT().apply { s(CDTIMEUNITschemes.CD_TIMEUNIT); value = actualTimeUnit }
                        }
                    }
                }
            }
        }
        return frequency
    }

    fun toDurationType(d: Duration?): DurationType? {
        if (d == null) {
            return null
        }
        return DurationType().apply {
            decimal = d.value?.let { BigDecimal(it) }
            unit = TimeunitType().apply {
                cd = CDTIMEUNIT().apply { s(CDTIMEUNITschemes.CD_TIMEUNIT); value = d.unit?.code }
            }
        }
    }

    fun toDaytime(intake: RegimenItem): ItemType.Regimen.Daytime {
        return ItemType.Regimen.Daytime().apply {
            if (intake.timeOfDay != null) {
                time = makeXMLGregorianCalendarFromHHMMSSLong(intake.timeOfDay!!)
            } else {
                val timeOfDay = intake.dayPeriod?.code ?: CDDAYPERIODvalues.DURINGLUNCH.value()
                when (timeOfDay) {
                    CDDAYPERIODvalues.AFTERNOON.value() -> time = XMLGregorianCalendarImpl.parse("16:00:00")
                    CDDAYPERIODvalues.EVENING.value() -> time = XMLGregorianCalendarImpl.parse("19:00:00")
                    CDDAYPERIODvalues.NIGHT.value() -> time = XMLGregorianCalendarImpl.parse("22:00:00")
                    CDDAYPERIODvalues.AFTERMEAL.value(), CDDAYPERIODvalues.BETWEENMEALS.value() -> throw IllegalArgumentException("$timeOfDay not supported: corresponds to multiple possible moments in a day")
                    else -> dayperiod = DayperiodType().apply {
                        cd = CDDAYPERIOD().apply {  value = CDDAYPERIODvalues.fromValue(timeOfDay) }
                    }
                }
            }
        }
    }

    fun inferPeriodFromRegimen(intakes: List<RegimenItem>?): Period? {
        if (intakes == null) {
            return null
        }
        intakes.forEach { assertValidRegimen(it) }
        when (intakes.size) {
            0 -> return null
            1 -> {
                val intake = intakes[0]
                if (isDaily(intake)) {
                    return Period(DAYS, 1)
                } else {
                    if (intake.weekday?.weekDay?.code != null && intake?.weekday?.weekNumber == null && intake?.weekday?.weekDay?.type == "CD-WEEKDAY") {
                        return Period(WEEKS, 1)
                    }
                    return null
                }
            }
            else -> {
                when (getCommonField(intakes)) {
                    "date" -> return getPeriodByDate(intakes)
                    "dayNumber" -> return getPeriodByDayNumber(intakes)
                    "weekday" -> return getPeriodByWeekDay(intakes)
                    "daily" -> return Period(
                        DAYS,
                        1
                                            ) // not looking into the intake hours: currently supporting >= DAYS (see precisionBelowDaysNotSupportedDaily test)
                    else -> return null
                }
            }
        }
    }

    private fun isDaily(intake: RegimenItem) =
        intake.date == null && intake.dayNumber == null && intake.weekday == null

    private fun assertValidRegimen(intake: RegimenItem) {
        val dayFields = listOf(intake.date, intake.dayNumber, intake.weekday)
        require(dayFields.filterNotNull().size <= 1, { dayFields })
        val intakeMoments = listOf(intake.timeOfDay, intake.dayPeriod)
        require(intakeMoments.filterNotNull().size <= 1, { intakeMoments })
        intake.weekday?.let { require(it.weekDay?.let { it.type == "CD-WEEKDAY" } ?: false) }
    }

    private fun getCommonField(intakes: List<RegimenItem>): String {
        if (intakes.all { isDaily(it) }) {
            return "daily"
        }
        val ret = ThrowIfSetTwice("none")
        if (intakes.all { it.date != null }) {
            ret.set("date")
        }
        if (intakes.all { it.dayNumber != null }) {
            ret.set("dayNumber")
        }
        if (intakes.all { it.weekday != null }) {
            ret.set("weekday")
        }
        return ret.get()
    }

    private fun getPeriodByDate(intakes: List<RegimenItem>): Period? {
        if (!isSameTimeEachDay(intakes)) {
            return null
        }
        val secondsInterval =
            getRegularInterval(intakes.map { it.date?.let { FuzzyValues.getLocalDateTime(it) } },
                               { a, b -> SECONDS.between(a, b) })
        if (secondsInterval == null || secondsInterval == 0L) {
            return null
        }
        return Period(SECONDS, secondsInterval).toBiggestTimeUnit()
    }

    private fun getPeriodByDayNumber(intakes: List<RegimenItem>): Period? {
        if (!isSameTimeEachDay(intakes)) {
            return null
        }
        val interval = getRegularInterval(intakes.map { it.dayNumber ?: 0 }.sorted(), { a, b -> b.toLong() - a })
        if (interval == null || interval == 0L) {
            return null
        }
        return Period(DAYS, interval).toBiggestTimeUnit()
    }

    private fun getPeriodByWeekDay(intakes: List<RegimenItem>): Period? {
        if (!isSameTimeEachDay(intakes)) {
            return null
        }
        if (intakes.map { it.weekday?.weekDay?.code }.filterNotNull().toSet().size != 1) {
            return null
        }
        if (intakes.map { it.weekday?.weekNumber }.contains(null)) {
            return null
        }
        val interval =
            getRegularInterval(intakes.map { it.weekday?.weekNumber ?: 0 }.sorted(), { a, b -> b.toLong() - a })
        if (interval == null || interval == 0L) {
            return null
        }
        return Period(WEEKS, interval).toBiggestTimeUnit()
    }

    private fun isSameTimeEachDay(intakes: List<RegimenItem>) =
        (intakes.map { it.dayPeriod }.toSet().size <= 1 && intakes.map { it.timeOfDay }.toSet().size <= 1)

    fun <E, I> getRegularInterval(elements: List<E>, intervalBetween: (E, E) -> I): I? {
        val interval = intervalBetween(elements[0], elements[1])
        var i = 2
        while (i < elements.size) {
            if (interval != intervalBetween(elements[i - 1], elements[i])) {
                return null
            }
            i++
        }
        return interval
    }

    fun <T : Any> kmehrJaxbElement(tagName: String, content: T): JAXBElement<T> {
        @Suppress("UNCHECKED_CAST") return JAXBElement<T>(
            QName(
                "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
                tagName
                 ), content::class.java as Class<T>, content
                                                         )
    }

    fun toCDDRUGCNK(c: Code) = CDDRUGCNK().apply {
            s = CDDRUGCNKschemes.CD_DRUG_CNK; sv = "LOCALDB"; value = c.code
        }

    fun toCompoundPrescriptionElements(
        compoundPrescription: CompoundPrescription,
        language: String?
                                      ): List<JAXBElement<out Serializable>> {
        return when (compoundPrescription) {
            is CompoundPrescription.Compounds -> {
                mutableListOf<JAXBElement<out Serializable>>().apply {
                    addAll(compoundPrescription.compounds.mapIndexed { idx, compound ->
                        kmehrJaxbElement("compound", CompoundType().apply {
                            require(compound.isValid(), { "compounds[$idx] is invalid: $compound" })
                            ids.add(IDKMEHR().apply {
                                s = IDKMEHRschemes.ID_KMEHR; sv = "1.0"; value =
                                (idx + 1).toString()
                            })
                            compound.substanceProduct?.let { (substanceCode, name) ->
                                substance = SubstanceType().apply {
                                    substanceCode?.let {
                                        cd = CDSUBSTANCE().apply {
                                            s = CDSUBSTANCEschemes.fromValue(substanceCode.type)
                                            sv = substanceCode.version ?: "1.0"
                                            substanceCode.label?.get(language)?.let { translation ->
                                                dn = translation
                                                l = language
                                            }
                                            value = substanceCode.code
                                        }
                                    }
                                }
                            }
                            compound.medicinalProduct?.let { med ->
                                medicinalproduct = MedicinalProductType().apply {
                                    med.intendedcds?.let {
                                        intendedcds.addAll(it.filter { it.type == "CD-DRUG-CNK" }.map {
                                            toCDDRUGCNK(
                                                it
                                                       )
                                        })
                                        intendedname = med.intendedname
                                    }
                                }
                            }
                            compound.quantityprefix?.let {
                                quantityprefix = toQuantityprefix(it)
                            }
                            compound.quantity?.let {
                                quantity = toQuantityType(it)
                            }
                        })
                    })
                    compoundPrescription.galenicForm?.let { galenicForm ->
                        add(
                            kmehrJaxbElement(
                                "galenicform",
                                toGalenicform(
                                    galenicForm,
                                    language
                                             )
                                            )
                           )
                    }
                    compoundPrescription.quantity?.let { quantity ->
                        add(
                            kmehrJaxbElement(
                                "quantity",
                                toQuantityType(quantity)
                                            )
                           )
                    }
                }
            }
            is CompoundPrescription.FormularyReference.FormularyName -> {
                mutableListOf<JAXBElement<out Serializable>>().apply {
                    add(kmehrJaxbElement("formularyreference", FormularyreferenceType().apply {
                        formularyname = compoundPrescription.name
                    }))
                    compoundPrescription.galenicForm?.let { galenicForm ->
                        add(
                            kmehrJaxbElement(
                                "galenicform",
                                toGalenicform(
                                    galenicForm,
                                    language
                                             )
                                            )
                           )
                    }
                    compoundPrescription.quantity?.let { quantity ->
                        add(
                            kmehrJaxbElement(
                                "quantity",
                                toQuantityType(quantity)
                                            )
                           )
                    }
                }
            }
            is CompoundPrescription.FormularyReference.Formulary -> {
                mutableListOf<JAXBElement<out Serializable>>().apply {
                    add(kmehrJaxbElement("formularyreference", FormularyreferenceType().apply {
                        compoundPrescription.formularyId?.let { id ->
                            cds.add(CDFORMULARY().apply {
                                s =
                                    CDFORMULARYschemes.CD_FORMULARY; sv = "1.0"; value = id
                            })
                        }
                        compoundPrescription.reference?.let { reference ->
                            cds.add(CDFORMULARY().apply {
                                s = CDFORMULARYschemes.CD_FORMULARYREFERENCE
                                sv = "1.0"
                                reference.label?.get(language)?.let { label ->
                                    l = language
                                    dn = label
                                }
                                value = reference.code
                            })
                        }
                    }))
                    compoundPrescription.galenicForm?.let { galenicForm ->
                        add(
                            kmehrJaxbElement(
                                "galenicform",
                                toGalenicform(
                                    galenicForm,
                                    language
                                             )
                                            )
                           )
                    }
                    compoundPrescription.quantity?.let { quantity ->
                        add(
                            kmehrJaxbElement(
                                "quantity",
                                toQuantityType(quantity)
                                            )
                           )
                    }
                }
            }
            is CompoundPrescription.MagistralText -> {
                mutableListOf<JAXBElement<out Serializable>>().apply {
                    add(kmehrJaxbElement("magistraltext", TextType().apply {
                        value = compoundPrescription.text
                        l = language
                    }))
                    compoundPrescription.galenicForm?.let { galenicForm ->
                        add(
                            kmehrJaxbElement(
                                "galenicform",
                                toGalenicform(
                                    galenicForm,
                                    language
                                             )
                                            )
                           )
                    }
                    compoundPrescription.quantity?.let { quantity ->
                        add(
                            kmehrJaxbElement(
                                "quantity",
                                toQuantityType(quantity)
                                            )
                           )
                    }
                }
            }
        }
    }

    private fun toGalenicform(_galenicForm: GalenicForm, language: String?): GalenicformType {
        val (galenicForm, text) = _galenicForm
        return GalenicformType().apply {
            galenicForm?.code?.let { code ->
                cd = CDGALENICFORM().apply {
                    s = CDGALENICFORMschemes.CD_MAGISTRALFORM; sv = "1.0"; value = code
                }
            }
            galenicformtext = toTextType(language, text)
        }
    }

    fun toTextType(language: String?, s: String?) = s?.let { TextType().apply { l = language; value = it } }

    private fun toQuantityprefix(code: Code): CompoundType.Quantityprefix {
        return CompoundType.Quantityprefix().apply {
            cd =
                CDQUANTITYPREFIX().apply {
                    s = "CD-QUANTITYPREFIX"; sv = "1.0"; value =
                    CDQUANTITYPREFIXvalues.fromValue(code.code)
                }
        }
    }

    private fun toQuantityType(quantity: KmehrQuantity): QuantityType {
        return QuantityType().apply {
            decimal = quantity.amount
            quantity.unit?.let {
                unit =
                    UnitType().apply {
                        cd =
                            CDUNIT().apply { s = CDUNITschemes.CD_UNIT; sv = "1.7"; value = it.code }
                    }
            }
        }
    }
}
