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

package org.taktik.freehealth.utils

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import org.apache.commons.lang3.math.NumberUtils
import org.joda.time.DateTime
import java.time.Instant

import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit
import javax.xml.datatype.DatatypeConstants

/**
 * This utility class provides methods to detect the type of value submitted to it (dates, ssin,...) and handle the
 * value consequently.<br></br>
 * <br></br>
 * Detected fully-formed dates are: <br></br>
 *
 *  * dd/MM/yyyy
 *  * dd-MM-yyyy
 *  * yyyyMMdd
 *
 * Detected partially-formed dates are: <br></br>
 *
 *  * MM/yyyy
 *  * MM-yyyy
 *  * MMyyyy
 *  * yyyy
 *
 */
object FuzzyValues {

    val currentFuzzyDate: Long
        get() = getCurrentFuzzyDateTime(ChronoUnit.DAYS)
    val currentFuzzyDateTime: Long
        get() = getCurrentFuzzyDateTime(ChronoUnit.SECONDS)

    fun getMaxRangeOf(text: String): Int {

        val fullyFormedDate = toYYYYMMDDString(text)

        val year = fullyFormedDate.substring(0, 4)
        val month = fullyFormedDate.substring(4, 6)
        val day = fullyFormedDate.substring(6, 8)

        val sb = StringBuilder(year)
        if (month == "00") {
            sb.append("99")
        } else {
            sb.append(month)
        }
        if (day == "00") {
            sb.append("99")
        } else {
            sb.append(day)
        }

        return Integer.parseInt(sb.toString())
    }

    fun getLocalDateTime(dateTime: Long): LocalDateTime? {
        var date = dateTime

        var h = 0
        var m = 0
        var s = 0

        var plusOne = false

        if (dateTime > 99991231L) {
            //Full date time format
            val time = dateTime % 1000000L
            date = dateTime / 1000000L

            h = (time / 10000L).toInt()
            m = (time / 100L % 100L).toInt()
            s = (time % 100L).toInt()

            if (s == 60) { s = 0; m++ }
            if (m == 60) { m = 0; h++ }
            if (h == 24) { h = 0; plusOne = true }
        }

        val y = (date / 10000L).toInt()
        var mm = (date / 100L % 100L).toInt()
        var d = (date % 100L).toInt()

        if (mm == 0) {
            mm = 1
        }
        if (d == 0) {
            d = 1
        }

        if (h > 24) {  return null }
        if (m > 60) {  return null }
        if (s > 60) {  return null }

        return LocalDateTime.of(y, mm, d, h, m, s).plus(Period.ofDays(if (plusOne) 1 else 0))
    }


    fun getJodaDateTime(dateTime: Long) : DateTime? {
        val localDateTime = getLocalDateTime(dateTime)

        return localDateTime?.let { DateTime(it.year, it.monthValue, it.dayOfMonth, it.hour, it.minute, it.second) }
    }

    fun getCurrentFuzzyDateTime(precision: TemporalUnit): Long {
        return getFuzzyDate(LocalDateTime.now(), precision)
    }

    fun getFuzzyDateTime(dateTime: LocalDateTime, precision: TemporalUnit): Long {
        var dateTime = dateTime
        val seconds = dateTime.second
        /*if (seconds == 0 && precision==ChronoUnit.SECONDS) {
			seconds = 60;
			dateTime = dateTime.minusMinutes(1);
		}*/

        var minutes = dateTime.minute
        if (minutes == 0 && precision === ChronoUnit.MINUTES) {
            minutes = 60
            dateTime = dateTime.minusHours(1)
        }

        var hours = dateTime.hour
        if (hours == 0 && precision === ChronoUnit.HOURS) {
            hours = 24
            dateTime = dateTime.minusDays(1)
        }


        return getFuzzyDate(dateTime, precision) * 1000000L + when {
            precision === ChronoUnit.DAYS -> 0
            else -> hours * 10000L + when {
                precision === ChronoUnit.HOURS -> 0
                else -> minutes * 100L + if (precision === ChronoUnit.MINUTES) 0 else seconds
            }
        }
    }

    fun getFuzzyDate(dateTime: LocalDateTime, precision: TemporalUnit = ChronoUnit.DAYS): Long {
        return dateTime.year * 10000L + when {
            precision === ChronoUnit.YEARS -> 0
            else -> dateTime.monthValue * 100L + when {
                precision === ChronoUnit.MONTHS -> 0
                else -> dateTime.dayOfMonth
            }
        }
    }

    fun getXMLGregorianCalendarFromFuzzyLong(date : Long?) : XMLGregorianCalendarImpl? {
        return date?.let {
            if (it%10000000000 == 0L) it/10000000000 else if (it%100000000 == 0L) it/100000000 else if (it<99991231 && it%10000 == 0L) it/10000 else if (it<99991231 && it%100 == 0L) it/100 else it /*normalize*/
        }?.let { d ->
            XMLGregorianCalendarImpl().apply {
                millisecond = DatatypeConstants.FIELD_UNDEFINED
                timezone = DatatypeConstants.FIELD_UNDEFINED
                when (d) {
                    in 0..9999 -> {  year = d.toInt(); month = DatatypeConstants.FIELD_UNDEFINED; day =
                        DatatypeConstants.FIELD_UNDEFINED
                    }
                    in 0..999912 -> { year = (d / 100).toInt(); month = (d % 100).toInt(); day =
                        DatatypeConstants.FIELD_UNDEFINED
                    }
                    in 0..99991231 -> { year = (d / 10000).toInt(); month = ((d / 100) % 100).toInt(); day = (d % 100).toInt() }
                    else -> {
                        year = (d / 10000000000).toInt(); month = ((d / 100000000) % 100).toInt(); day = ((d / 1000000) % 100).toInt()
                        hour = ((d / 10000) % 100).toInt(); minute = ((d / 100) % 100).toInt(); second = (d % 100).toInt()
                    }
                }
            }
        }
    }


    fun getFuzzyDate(instant: Instant, zoneId: ZoneId = ZoneId.systemDefault(), precision: TemporalUnit = ChronoUnit.DAYS): Long {
        return getFuzzyDate(LocalDateTime.ofInstant(instant, zoneId), precision)
    }

    fun getFuzzyDate(dateTime: DateTime, precision: TemporalUnit = ChronoUnit.DAYS): Long {
        return getFuzzyDate(LocalDateTime.of(dateTime.year, dateTime.monthOfYear, dateTime.dayOfMonth,
                                             dateTime.hourOfDay, dateTime.minuteOfHour, dateTime.secondOfMinute,
                                             dateTime.millisOfSecond*1_000_000), precision)
    }


    /**
     * Indicates if the submitted text is a fully-formed or partially-formed date.
     */
    fun isDate(text: String): Boolean {
        return isPartiallyFormedYYYYMMDD(text) || isPartiallyFormedDashDate(text) || isPartiallyFormedSlashDate(text)
    }

    /**
     * Indicates if the submitted text is a fully-formed date.
     */
    fun isFullDate(text: String): Boolean {
        return isFullyFormedYYYYMMDDDate(text) || isFullyFormedDashDate(text) || isFullyFormedSlashDate(text)
    }

    /**
     * Indicates if the submitted text has the format of a SSIN. <br></br>
     * It does **NOT** check if the SSIN is valid!
     */
    fun isSsin(text: String): Boolean {
        return NumberUtils.isDigits(text) && text.length == 11
    }

    /**
     * Converts a text value into a YYYYMMDD integer, where DD and MM are replaced by 00 characters if the submitted
     * value does not contain the information to extract the day or month of the date.<br></br>
     * For example, submitting *11/2008* will return *20081100*. <br></br>
     * All dates detected by [.isFullDate] and [.isDate] will be converted.
     */
    fun toYYYYMMDD(text: String): Int {
        return Integer.parseInt(toYYYYMMDDString(text))
    }

    private fun toYYYYMMDDString(text: String): String {
        val result: String

        val fields: Array<String>
        if (isPartiallyFormedDashDate(text)) {
            fields = text.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        } else if (isPartiallyFormedSlashDate(text)) {
            fields = text.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        } else {
            fields = arrayOf(text)
        }

        var day = "00"
        var month = "00"
        val year: String

        when {
            fields.size == 3 -> {
                day = if (fields[0].isEmpty()) "00" else String.format("%1$02d", Integer.parseInt(fields[0]))
                month = if (fields[1].isEmpty()) "00" else String.format("%1$02d", Integer.parseInt(fields[1]))
                year = if (fields[2].isEmpty()) "0000" else String.format("%1$04d", Integer.parseInt(fields[2]))

                result = year + month + day
            }
            fields.size == 2 -> {
                month = if (fields[0].isEmpty()) "00" else String.format("%1$02d", Integer.parseInt(fields[0]))
                year = if (fields[1].isEmpty()) "0000" else String.format("%1$04d", Integer.parseInt(fields[1]))

                result = year + month + day
            }
            else -> when {
                isPartiallyFormedYYYYMMDD(text) -> {
                    when {
                        text.length <= 4 -> year = String.format("%1$04d", Integer.parseInt(text.substring(0, 4)))
                        text.length <= 6 -> {
                            month = String.format("%1$02d", Integer.parseInt(text.substring(4)))
                            year = String.format("%1$04d", Integer.parseInt(text.substring(0, 4)))
                        }
                        else -> {
                            day = String.format("%1$02d", Integer.parseInt(text.substring(6, 8)))
                            month = String.format("%1$02d", Integer.parseInt(text.substring(4, 6)))
                            year = String.format("%1$04d", Integer.parseInt(text.substring(0, 4)))
                        }
                    }
                    result = year + month + day
                }
                else -> result = text
            }
        }

        return result
    }

    private fun isFullyFormedDashDate(text: String): Boolean {
        return text.matches("(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-(\\d{4})".toRegex())
    }

    private fun isFullyFormedSlashDate(text: String): Boolean {
        return text.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/(\\d{4})".toRegex())
    }

    private fun isFullyFormedYYYYMMDDDate(text: String): Boolean {
        return text.matches("(\\d{4})(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])".toRegex())
    }

    private fun isPartiallyFormedDashDate(text: String): Boolean {
        return text.matches("(0?[1-9]|[12][0-9]|3[01])?(-)?(0?[1-9]|1[012])-(\\d{4})".toRegex())
    }

    private fun isPartiallyFormedSlashDate(text: String): Boolean {
        return text.matches("(0?[1-9]|[12][0-9]|3[01])?(/)?(0?[1-9]|1[012])/(\\d{4})".toRegex())
    }

    private fun isPartiallyFormedYYYYMMDD(text: String): Boolean {
        return NumberUtils.isDigits(text) && text.matches("(\\d{4})(0?[1-9]|1[012])?(0?[1-9]|[12][0-9]|3[01])?".toRegex())
    }

    fun compare(left: Long, right: Long): Int {
        return java.lang.Long.valueOf(if (left < 29991231) left * 1000000 else left)!!.compareTo(if (right < 29991231) right * 1000000 else right)
    }
}
