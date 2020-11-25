package org.taktik.connector.business.domain.kmehr.v20190301

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESS
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDADDRESSschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENT
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRY
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDCOUNTRYschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGCNK
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDDRUGCNKschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDINNCLUSTER
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDINNCLUSTERschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEM
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTELECOM
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTELECOMschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTIMEUNIT
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTIMEUNITschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTIONschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDUNIT
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDUNITschemes
import org.taktik.connector.business.domain.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1.DateType
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.GregorianCalendar
import javax.xml.datatype.DatatypeConstants
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

fun makeXMLGregorianCalendarFromHHMMSSLong(date: Long): XMLGregorianCalendarImpl? {
    return XMLGregorianCalendarImpl().apply {
        hour = (date / 10000 % 100).toInt()
        minute = (date / 100 % 100).toInt()
        second = (date % 100).toInt()
    }
}

fun makeXMLGregorianCalendarFromFuzzyLong(date: Long?): XMLGregorianCalendarImpl? {
    return date?.let {
        if (it % 10000000000 == 0L) it / 10000000000 else if (it % 100000000 == 0L) it / 100000000 else if (it < 99991231 && it % 10000 == 0L) it / 10000 else if (it < 99991231 && it % 100 == 0L) it / 100 else it /*normalize*/
    }?.let { d ->
        XMLGregorianCalendarImpl().apply {
            millisecond = DatatypeConstants.FIELD_UNDEFINED
            timezone = DatatypeConstants.FIELD_UNDEFINED

            hour = DatatypeConstants.FIELD_UNDEFINED
            minute = DatatypeConstants.FIELD_UNDEFINED
            second = DatatypeConstants.FIELD_UNDEFINED

            try {
                when (d) {
                    in 0..9999 -> {
                        year = d.toInt(); month = DatatypeConstants.FIELD_UNDEFINED; day =
                            DatatypeConstants.FIELD_UNDEFINED
                    }
                    in 0..999912 -> {
                        year = (d / 100).toInt(); month = (d % 100).toInt(); day = DatatypeConstants.FIELD_UNDEFINED
                    }
                    in 0..99991231 -> {
                        year = (d / 10000).toInt(); month = ((d / 100) % 100).toInt(); day = (d % 100).toInt()
                    }
                    else -> {
                        year = (d / 10000000000).toInt(); month = ((d / 100000000) % 100).toInt(); day = ((d / 1000000) % 100).toInt()
                        hour = ((d / 10000) % 100).toInt(); minute = ((d / 100) % 100).toInt(); second = (d % 100).toInt()
                    }
                }
            }catch(ex : Exception) {
                val tmp = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault())
                year = tmp.year; month = tmp.monthValue; day = tmp.dayOfMonth
                hour = tmp.hour; minute = tmp.minute; second = tmp.second
            }
        }
    }
}

fun makeDateTypeFromFuzzyLong(date: Long?): DateType? {
    return makeXMLGregorianCalendarFromFuzzyLong(date)?.let {
        DateType().apply {
            when (DatatypeConstants.FIELD_UNDEFINED) {
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


fun makeFuzzyIntFromXMLGregorianCalendar(cal: XMLGregorianCalendar?): Int? {
    return cal?.let {
        it.year * 10000 + it.month * 100 + it.day
    }
}

fun makeXGC(epochMillisTimestamp: Long?, unsetMillis: Boolean = false): XMLGregorianCalendar? {
    return epochMillisTimestamp?.let {
        DatatypeFactory.newInstance()
            .newXMLGregorianCalendar(GregorianCalendar.getInstance().apply { time = Date(epochMillisTimestamp) } as GregorianCalendar)
            .apply {
                timezone = DatatypeConstants.FIELD_UNDEFINED
                if (unsetMillis) {
                    millisecond = DatatypeConstants.FIELD_UNDEFINED
                }
            }
    }
}

fun makeXmlGregorianCalendar(instant: Instant): XMLGregorianCalendar {
    val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return XMLGregorianCalendarImpl.createDateTime(dateTime.year, dateTime.monthValue, dateTime.dayOfMonth, dateTime.hour, dateTime.minute, dateTime.second, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED)
}

fun makeXmlGregorianCalendar(dateTime: LocalDateTime): XMLGregorianCalendar {
    return XMLGregorianCalendarImpl.createDateTime(dateTime.year, dateTime.monthValue, dateTime.dayOfMonth, dateTime.hour, dateTime.minute, dateTime.second, DatatypeConstants.FIELD_UNDEFINED, DatatypeConstants.FIELD_UNDEFINED)
}

fun makeFuzzyLongFromXMLGregorianCalendar(cal: XMLGregorianCalendar?): Long? {
    return makeFuzzyIntFromXMLGregorianCalendar(cal)?.let {
        (it * 1000000L + (cal!!.hour ?: 0) * 10000 + (cal.minute ?: 0) * 100 + (cal.second ?: 0))
    }
}

fun makeFuzzyLongFromDateAndTime(date: XMLGregorianCalendar?, time: XMLGregorianCalendar?): Long? {
    return makeFuzzyIntFromXMLGregorianCalendar(date)?.let { d ->
        time?.let {
            d * 1000000L + (it.hour ?: 0) * 10000 + (it.minute ?: 0) * 100 + (it.second ?: 0)
        } ?: d.toLong()
    }
}

fun CDITEM.s(scheme: CDITEMschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDTELECOM.s(scheme: CDTELECOMschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDADDRESS.s(scheme: CDADDRESSschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDCOUNTRY.s(scheme: CDCOUNTRYschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDCONTENT.s(scheme: CDCONTENTschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDHCPARTY.s(scheme: CDHCPARTYschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDTRANSACTION.s(scheme: CDTRANSACTIONschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDINNCLUSTER.s(scheme: CDINNCLUSTERschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDDRUGCNK.s(scheme: CDDRUGCNKschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDUNIT.s(scheme: CDUNITschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

fun CDTIMEUNIT.s(scheme: CDTIMEUNITschemes) {
    s = scheme
    sv = scheme.version()?:"1.0"
}

