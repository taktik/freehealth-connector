package be.vlaanderen.zg.vaccinnet.batchuploadtest.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.xml.datatype.DatatypeConfigurationException
import javax.xml.datatype.DatatypeConstants
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

object DateUtils {
    @Throws(DatatypeConfigurationException::class)
    fun toXmlGregorianCalendar(localDate: LocalDate?): XMLGregorianCalendar {
        val xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(toCalendar(localDate))
        xmlGregorianCalendar.timezone = DatatypeConstants.FIELD_UNDEFINED
        return xmlGregorianCalendar
    }

    @Throws(DatatypeConfigurationException::class)
    fun toXmlGregorianCalendar(localDateTime: LocalDateTime): XMLGregorianCalendar {
        val xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(toCalendar(localDateTime))
        xmlGregorianCalendar.timezone = DatatypeConstants.FIELD_UNDEFINED
        return xmlGregorianCalendar
    }

    private fun toCalendar(localDate: LocalDate?): GregorianCalendar {
        return GregorianCalendar.from(localDate!!.atStartOfDay(ZoneId.systemDefault()))
    }

    private fun toCalendar(localDateTime: LocalDateTime): GregorianCalendar {
        return GregorianCalendar.from(localDateTime.atZone(ZoneId.systemDefault()))
    }
}
