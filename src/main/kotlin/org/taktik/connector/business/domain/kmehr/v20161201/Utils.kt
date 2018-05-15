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

package org.taktik.connector.business.domain.kmehr.v20161201

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.MomentType
import org.taktik.connector.business.domain.kmehr.v20161201.be.fgov.ehealth.standards.kmehr.schema.v1.DateType
import java.util.Date
import java.util.GregorianCalendar

import javax.xml.datatype.DatatypeConstants.FIELD_UNDEFINED
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

class Utils {

    companion object {
        @JvmStatic
        fun makeXMLGregorianCalendarFromFuzzyLong(date: Long?): XMLGregorianCalendarImpl? {
            return date?.let { date ->
                /*normalize*/
                when {
                    date % 10000000000 == 0L -> date / 10000000000
                    date % 100000000 == 0L -> date / 100000000
                    date < 99991231 && date % 10000 == 0L -> date / 10000
                    date < 99991231 && date % 100 == 0L -> date / 100
                    else -> date
                }
            }?.let { d ->
                XMLGregorianCalendarImpl().apply {
                    millisecond = FIELD_UNDEFINED
                    timezone = FIELD_UNDEFINED
                    when (d) {
                        in 0..9999 -> {
                            year = d.toInt(); month = FIELD_UNDEFINED; day = FIELD_UNDEFINED
                        }
                        in 0..999912 -> {
                            year = (d / 100).toInt(); month = (d % 100).toInt(); day = FIELD_UNDEFINED
                        }
                        in 0..99991231 -> {
                            year = (d / 10000).toInt(); month = ((d / 100) % 100).toInt(); day = (d % 100).toInt()
                        }
                        else -> {
                            year = (d / 10000000000).toInt()
                            month = ((d / 100000000) % 100).toInt()
                            day = ((d / 1000000) % 100).toInt()
                            hour = ((d / 10000) % 100).toInt()
                            minute = ((d / 100) % 100).toInt()
                            second = (d % 100).toInt()
                        }
                    }
                }
            }
        }

        @JvmStatic
        fun makeXMLGregorianCalendarFromHHMMSSLong(date: Long): XMLGregorianCalendarImpl? {
            return XMLGregorianCalendarImpl().apply {
                hour = (date / 10000 % 100).toInt()
                minute = (date / 100 % 100).toInt()
                second = (date % 100).toInt()
            }
        }

        @JvmStatic
        fun makeDateTypeFromFuzzyLong(date: Long?): DateType? {
            return makeXMLGregorianCalendarFromFuzzyLong(date)?.let {
                DateType().apply {
                    when (FIELD_UNDEFINED) {
                        it.month -> {
                            year = it
                        }
                        it.day -> {
                            yearmonth = it
                        }
                        it.hour -> {
                            this.date = it
                        }
                        else -> {
                            this.date = it; this.time = it
                        }
                    }
                }
            }
        }

        @JvmStatic
        fun makeMomentTypeFromFuzzyLong(date: Long?): MomentType? {
            return makeXMLGregorianCalendarFromFuzzyLong(date)?.let {
                MomentType().apply {
                    when (FIELD_UNDEFINED) {
                        it.month -> {
                            year = it
                        }
                        it.day -> {
                            yearmonth = it
                        }
                        it.hour -> {
                            this.date = it
                        }
                        else -> {
                            this.date = it; this.time = it
                        }
                    }
                }
            }
        }

        @JvmStatic
        fun makeXGC(date: Long?): XMLGregorianCalendar? {
            return date?.let {
                DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(GregorianCalendar.getInstance().apply {
                        time = Date(date)
                    } as GregorianCalendar).apply { timezone = FIELD_UNDEFINED }
            }
        }
    }
}
