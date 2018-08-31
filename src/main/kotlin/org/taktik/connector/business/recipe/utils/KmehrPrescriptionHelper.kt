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

package org.taktik.icure.be.ehealth.logic.recipe.impl

import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGCNKschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDFORMULARY
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDFORMULARYschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDGALENICFORM
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDGALENICFORMschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDQUANTITYPREFIX
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDQUANTITYPREFIXvalues
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDSUBSTANCE
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDSUBSTANCEschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDUNIT
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDUNITschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.dt.v1.TextType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHRschemes
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.CompoundType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.FormularyreferenceType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.GalenicformType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.MedicinalProductType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.QuantityType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.SubstanceType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.UnitType
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.middleware.domain.recipe.CompoundPrescription
import org.taktik.freehealth.middleware.domain.recipe.GalenicForm
import org.taktik.freehealth.middleware.domain.recipe.KmehrQuantity
import org.taktik.freehealth.middleware.domain.recipe.RegimenItem
import org.taktik.freehealth.utils.FuzzyValues
import java.io.Serializable
import java.time.temporal.ChronoUnit
import java.time.temporal.ChronoUnit.*
import javax.xml.bind.JAXBElement
import javax.xml.namespace.QName

/**
 * @author Bernard Paulus on 6/04/17.
 */
class KmehrPrescriptionHelper {
    companion object {
        @JvmStatic
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

        @JvmStatic
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

        @JvmStatic
        fun <T : Any> kmehrJaxbElement(tagName: String, content: T): JAXBElement<T> {
            @Suppress("UNCHECKED_CAST") return JAXBElement<T>(
                QName(
                    "http://www.ehealth.fgov.be/standards/kmehr/schema/v1",
                    tagName
                ), content::class.java as Class<T>, content
            )
        }

        @JvmStatic
        fun toCDDRUGCNK(c: Code) =
            org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGCNK().apply {
                s =
                    CDDRUGCNKschemes.CD_DRUG_CNK; sv = "LOCALDB"; value = c.code
            }

        @JvmStatic
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
                                        substancename = name
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
                                                Companion.toCDDRUGCNK(
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
                        add(Companion.kmehrJaxbElement("magistraltext", TextType().apply {
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

        @JvmStatic
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

    data class Period(val unit: ChronoUnit, val amount: Long) {
        private val supportedUnits = ChronoUnit.values().filter { it >= SECONDS }

        fun toBiggestTimeUnit(): Period {
            val seconds = toUnit(SECONDS).amount
            val biggestUnit = supportedUnits.reversed().first { (seconds % it.duration.seconds) == 0L }
            return toUnit(biggestUnit)
        }

        fun toUnit(unit: ChronoUnit): Period {
            require(supportedUnits.contains(unit), { "$unit not in $supportedUnits" })
            val seconds = amount * this.unit.duration.seconds
            return Period(unit, seconds / unit.duration.seconds)
        }
    }

    class ThrowIfSetTwice<T>(var value: T) {
        var assigned = false
            private set

        fun set(value: T) {
            if (assigned) {
                throw IllegalStateException("double assignment: current: ${this.value}; new: $value")
            }
            assigned = true
            this.value = value
        }

        fun get(): T {
            return value
        }
    }
}
