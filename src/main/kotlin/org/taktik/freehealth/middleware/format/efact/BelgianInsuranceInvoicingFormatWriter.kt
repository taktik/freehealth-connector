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

package org.taktik.freehealth.middleware.format.efact

import org.taktik.freehealth.middleware.domain.common.InsuranceParameter
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.dto.common.Gender
import org.taktik.freehealth.middleware.dto.efact.*
import org.taktik.freehealth.middleware.format.StringUtils
import org.taktik.freehealth.middleware.format.WriterSession
import org.taktik.freehealth.middleware.format.efact.segments.Record10Description
import org.taktik.freehealth.middleware.format.efact.segments.Record20Description
import org.taktik.freehealth.middleware.format.efact.segments.Record50Description
import org.taktik.freehealth.middleware.format.efact.segments.Record51Description
import org.taktik.freehealth.middleware.format.efact.segments.Record52Description
import org.taktik.freehealth.middleware.format.efact.segments.Record80Description
import org.taktik.freehealth.middleware.format.efact.segments.Record90Description
import org.taktik.freehealth.middleware.format.efact.segments.Segment200Description
import org.taktik.freehealth.middleware.format.efact.segments.Segment300Description
import org.taktik.freehealth.middleware.format.efact.segments.Segment400Record95Description
import org.taktik.freehealth.middleware.format.efact.segments.Segment500Record96Description
import org.taktik.freehealth.utils.FuzzyValues
import java.io.IOException
import java.io.Writer
import java.math.BigInteger
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Arrays
import java.util.Calendar
import java.util.GregorianCalendar

class BelgianInsuranceInvoicingFormatWriter(private val writer: Writer) {
    private val dtf = DateTimeFormatter.ofPattern("yyyyMMdd")

    private fun getInsurabilityParameters(patient: Patient, parameter: InsuranceParameter): String? {
        patient.insurabilities
        if (patient.insurabilities.isNotEmpty()) {
            val parameters = patient.insurabilities[0].parameters
            return parameters[parameter]
        }
        return null
    }

    fun getDestCode(affCode: String, invoiceSender: InvoiceSender): String {
        val firstCode = affCode.substring(0, 3).replace("[^0-9]".toRegex(), "")
        if (affCode.startsWith("3")) {
            return if (Arrays.asList("305", "315", "317", "319", "323", "325").contains(firstCode)) if (invoiceSender.isSpecialist) "317" else "319" else firstCode
        } else if (affCode.startsWith("4")) {
            return "400"
        }
        return firstCode
    }

    //022464328
    @Throws(IOException::class)
    fun write200and300(sender: InvoiceSender,
                       numericalRef: Long,
                       fileRef: String,
                       fileVersion: Int,
                       sendingNumber: Long,
                       invoicingYear: Int,
                       invoicingMonth: Int,
                       isTest: Boolean) {
        val ws200 = WriterSession(writer, Segment200Description)

        val creationDate = LocalDateTime.now()
        val formattedCreationDate = creationDate.format(dtf)

        assert(formattedCreationDate.length == 8)

        ws200.write("200", 920000)
        ws200.write("2001", 0)
        ws200.write("201", 1)
        ws200.write("2011", 0)
        ws200.write("202", fileVersion)
        ws200.write("2021", 0)
        ws200.write("203", 0)
        ws200.write("2031", 0)
        ws200.write("204", numericalRef)
        ws200.write("2041", 0)
        ws200.write("205", 0)
        ws200.write("2051", 0)
        ws200.write("206", 0)

        ws200.writeFieldsWithoutCheckSum()

        val ws300 = WriterSession(writer, Segment300Description)

        ws300.write("300", invoicingYear * 100 + invoicingMonth)
        ws300.write("3001", 0)
        ws300.write("301", sendingNumber)
        ws300.write("3011", 0)
        ws300.write("302", formattedCreationDate)
        ws300.write("3021", 0)
        ws300.write("303", fileRef)
        ws300.write("3031", 0)
        ws300.write("304", if (isTest) 9991999 else 1999)
        ws300.write("3041", 0)
        ws300.write("305", StringUtils.removeDiacriticalMarks(sender.lastName!!))
        ws300.write("3051", 0)
        ws300.write("306", StringUtils.removeDiacriticalMarks(sender.firstName!!))
        ws300.write("3061", 0)
        ws300.write("307", sender.phoneNumber!!)
        ws300.write("3071", 0)
        ws300.write("308", 3)
        ws300.write("3081", 0)
        ws300.write("309", 1)
        ws300.write("3091", 0)
        ws300.write("310", 0)

        ws300.writeFieldsWithoutCheckSum()
    }

    @Throws(IOException::class)
    fun write400(oa: String, numericalRef: Long?, recordsCount: Long, codesNomenclature: List<Long>, amount: Long) {
        val ws = WriterSession(writer, Segment400Record95Description)

        val creationDate = LocalDateTime.now()
        val formattedCreationDate = creationDate.format(dtf)
        assert(formattedCreationDate.length == 8)

        var cs = BigInteger.ZERO
        for (codeValue in codesNomenclature) {
            cs = cs.add(BigInteger.valueOf(codeValue))
        }
        var modulo = cs.mod(BigInteger.valueOf(97)).toLong()
        modulo = if (modulo == 0L) 97 else modulo

        ws.write("400", 95)
        ws.write("4001", 0)
        ws.write("401", oa)
        ws.write("4011", 0)
        ws.write("402", numericalRef!!)
        ws.write("4021", 0)
        ws.write("403", if (amount >= 0) "+" else "-")
        ws.write("404", Math.abs(amount))
        ws.write("4041", 0)
        ws.write("405", "+")
        ws.write("406", 0)
        ws.write("4061", 0)
        ws.write("407", if (amount >= 0) "+" else "-")
        ws.write("408", Math.abs(amount))
        ws.write("4081", 0)
        ws.write("409", recordsCount)
        ws.write("4091", 0)
        ws.write("410", modulo)
        ws.write("4101", 0)
        ws.write("411", "")

        ws.writeFieldsWithoutCheckSum()
    }

    @Throws(IOException::class)
    fun write960000(oa: String, recordsCount: Long, codesNomenclature: List<Long>, amount: Long) {
        val ws = WriterSession(writer, Segment500Record96Description)

        val creationDate = LocalDateTime.now()
        val formattedCreationDate = creationDate.format(dtf)
        assert(formattedCreationDate.length == 8)

        var cs = BigInteger.ZERO
        for (`val` in codesNomenclature) {
            cs = cs.add(BigInteger.valueOf(`val`))
        }
        var modulo = cs.mod(BigInteger.valueOf(97)).toLong()
        modulo = if (modulo == 0L) 97 else modulo

        ws.write("500", 96)
        ws.write("5001", 0)
        ws.write("501", oa.substring(0, 1) + "99")
        ws.write("5011", 0)
        ws.write("502", 0)
        ws.write("5021", 0)
        ws.write("503", if (amount >= 0) "+" else "-")
        ws.write("504", Math.abs(amount))
        ws.write("5041", 0)
        ws.write("505", "+")
        ws.write("506", 0)
        ws.write("5061", 0)
        ws.write("507", if (amount >= 0) "+" else "-")
        ws.write("508", Math.abs(amount))
        ws.write("5081", 0)
        ws.write("509", recordsCount)
        ws.write("5091", 0)
        ws.write("510", modulo)
        ws.write("5101", 0)
        ws.write("511", "")

        ws.writeFieldsWithoutCheckSum()
    }

    @Throws(IOException::class)
    fun writeFileHeader(recordNumber: Int,
                        sender: InvoiceSender,
                        fileVersion: Long,
                        sendingNumber: Long,
                        invoicingYear: Int,
                        invoicingMonth: Int,
                        batchRef: String): Int {

        val ws = WriterSession(writer, Record10Description)

        val creationDate = LocalDateTime.now()
        val formattedCreationDate = creationDate.format(dtf)
        assert(formattedCreationDate.length == 8)

        ws.write("2", recordNumber)
        ws.write("4", fileVersion)
        ws.write("7", sendingNumber)
        ws.write("13", 40)
        ws.write("14", sender.nihii)
        ws.write("22", invoicingYear)
        ws.write("23", invoicingMonth)
        ws.write("25", formattedCreationDate)
        ws.write("27", sender.bce)
        ws.write("28", batchRef)
        ws.write("31", sender.bic)
        ws.write("36", sender.iban)

        ws.writeFieldsWithCheckSum()

        return recordNumber+1
    }

    @Throws(IOException::class)
    fun writeRecordHeader(recordNumber: Int,
                          sender: InvoiceSender,
                          invoiceNumber: Long?,
                          treatmentReason: InvoicingTreatmentReasonCode,
                          invoiceRef: String,
                          patient: Patient,
                          insuranceCode: String,
                          ignorePrescriptionDate: Boolean,
                          hospitalisedPatient: Boolean,
                          creditNote: Boolean,
                          relatedBatchSendNumber: Long?,
                          relatedBatchYearMonth: Long?,
                          relatedInvoiceIoCode: String?,
                          relatedInvoiceNumber: Long?
        ): Int {

        val ws = WriterSession(writer, Record20Description)

        val creationDate = LocalDateTime.now()
        val formattedCreationDate = creationDate.format(dtf)

        assert(formattedCreationDate.length == 8)

        val tc1String = getInsurabilityParameters(patient, InsuranceParameter.tc1)
        val ct1 = if (tc1String != null) Integer.valueOf(tc1String) else 0
        val tc2String = getInsurabilityParameters(patient, InsuranceParameter.tc2)
        val ct2 = if (tc2String != null) Integer.valueOf(tc2String) else 0
        var noSIS: String? = if (patient.ssin != null) patient.ssin else ""
        noSIS = noSIS!!.replace("[^0-9]".toRegex(), "")


        ws.write("2", recordNumber)
        //Silly rules for this field
        var affCode = insuranceCode

        if (affCode.startsWith("2") || affCode.startsWith("5")) {
            affCode = "000"
        }

        ws.write("7", affCode)
        ws.write("8a", noSIS)
        ws.write("9", if (patient.gender == null || patient.gender == Gender.male) 1 else 2)
        ws.write("10", if (hospitalisedPatient) 1 else 3)
        ws.write("11", if (relatedInvoiceNumber == null) 0 else { if(!creditNote) 1 else 3 })
        ws.write("14", sender.nihii)
        ws.write("16", if (ignorePrescriptionDate) 1 else 0)
        ws.write("17", treatmentReason.code)

        //Silly rules for this field
        val destCode = getDestCode(insuranceCode, sender)
        val relatedDestCode = if (relatedInvoiceIoCode != null) getDestCode(relatedInvoiceIoCode, sender) else null

        ws.write("18", destCode)
        ws.write("24", invoiceNumber)
        ws.write("27", ct1 * 1000 + ct2)
        ws.write("29", relatedInvoiceNumber)
        ws.write("28", invoiceRef)
        ws.write("32", 1)
        ws.write("34", relatedBatchSendNumber)
        ws.write("37", relatedDestCode)
        ws.write("41", relatedBatchYearMonth)

        ws.writeFieldsWithCheckSum()

        return recordNumber+1
    }

    @Throws(IOException::class)
    fun writeRecordContent(recordNumber: Int,
                           sender: InvoiceSender,
                           invoicingYear: Int?,
                           invoicingMonth: Int?,
                           patient: Patient,
                           insuranceCode: String,
                           icd: InvoiceItem): Int {
        val ws = WriterSession(writer, Record50Description)

        val creationDate = LocalDateTime.now()
        val formattedCreationDate = creationDate.format(dtf)

        assert(formattedCreationDate.length == 8)

        val nf11 = DecimalFormat("00000000000")
        val nf9 = DecimalFormat("000000000")
        val nf4 = DecimalFormat("0000")

        val c = GregorianCalendar()
        c.set(Calendar.YEAR, invoicingYear!!)
        c.set(Calendar.MONTH, invoicingMonth!! - 1)
        c.set(Calendar.DAY_OF_MONTH, 1)
        c.add(Calendar.MONTH, 1)
        c.add(Calendar.DAY_OF_MONTH, -1)

        var noSIS: String? = if (patient.ssin != null) patient.ssin else ""
        noSIS = noSIS!!.replace("[^0-9]".toRegex(), "")

        ws.write("2", recordNumber)
        ws.write("3", (icd.percentNorm?: InvoicingPercentNorm.None).code)
        ws.write("4", icd.codeNomenclature)
        ws.write("5", FuzzyValues.getLocalDateTime(icd.dateCode!!)!!.format(dtf))
        ws.write("6a", FuzzyValues.getLocalDateTime(icd.dateCode!!)!!.format(dtf))
        ws.write("7", insuranceCode)
        ws.write("8a", noSIS)
        ws.write("9", if (patient.gender == null || patient.gender == Gender.male) 1 else 2)
        ws.write("12", (icd.timeOfDay?: InvoicingTimeOfDay.Other).code)
        ws.write("13",990)
        ws.write("15", icd.doctorIdentificationNumber)
        ws.write("16", if (icd.gnotionNihii == null || icd.gnotionNihii?.let { it.isEmpty() } == true) 1 else 4)
        ws.write("17", icd.relatedCode)
        ws.write("19",(if (icd.reimbursedAmount >= 0) "+" else "-") + nf11.format(Math.abs(icd.reimbursedAmount)))
        ws.write("22",(if (icd.units >= 0) "+" else "-") + nf4.format(Math.abs(icd.units)))
        ws.write("23", (icd.derogationMaxNumber?: InvoicingDerogationMaxNumberCode.Other).code)
        ws.write("24", icd.prescriberNihii)
        ws.write("26", (icd.percentNorm?: InvoicingPercentNorm.None).code)
        ws.write("27",(if (icd.patientFee >= 0) "+" else "-") + nf9.format(Math.abs(icd.patientFee)))
        ws.write("28", icd.invoiceRef)
        ws.write("30",(if (icd.doctorSupplement >= 0) "+" else "-") + nf9.format(Math.abs(icd.doctorSupplement)))
        ws.write("32", icd.override3rdPayerCode?. let { if (it >= 0) it else 0 } ?: 0)
        ws.write("33", icd.personalInterventionCoveredByThirdPartyCode?. let { if (it >= 0) it else 0 } ?: 0)//MAF Zone 33 todo //Mettre 1 si a charge du medecin
        ws.write("34", (icd.sideCode?: InvoicingSideCode.None).code)
        ws.write("35", sender.conventionCode)
        ws.write("49",icd.gnotionNihii)

        ws.writeFieldsWithCheckSum()
        return recordNumber+1
    }

    @Throws(IOException::class)
    fun writeInvolvementRecordContent(recordNumber: Int,
                                      invoicingYear: Int?,
                                      invoicingMonth: Int?,
                                      patient: Patient,
                                      icd: InvoiceItem): Int {
        val ws = WriterSession(writer, Record51Description)

        val creationDate = LocalDateTime.now()
        val formattedCreationDate = creationDate.format(dtf)

        assert(formattedCreationDate.length == 8)

        val nf3 = DecimalFormat("000")
        val nf11 = DecimalFormat("00000000000")

        val c = GregorianCalendar()
        c.set(Calendar.YEAR, invoicingYear!!)
        c.set(Calendar.MONTH, invoicingMonth!! - 1)
        c.set(Calendar.DAY_OF_MONTH, 1)
        c.add(Calendar.MONTH, 1)
        c.add(Calendar.DAY_OF_MONTH, -1)

        var noSIS: String? = if (patient.ssin != null) patient.ssin else ""
        noSIS = noSIS!!.replace("[^0-9]".toRegex(), "")

        val tc1String = getInsurabilityParameters(patient, InsuranceParameter.tc1)
        val ct1 = if (tc1String != null) Integer.valueOf(tc1String) else 0
        val tc2String = getInsurabilityParameters(patient, InsuranceParameter.tc2)
        val ct2 = if (tc2String != null) Integer.valueOf(tc2String) else 0

        ws.write("2", recordNumber)
        ws.write("3", 0)
        ws.write("4", icd.codeNomenclature)
        ws.write("5", FuzzyValues.getLocalDateTime(icd.dateCode!!)!!.format(dtf))
        ws.write("8a", noSIS)
        ws.write("15", icd.doctorIdentificationNumber)
        ws.write("19", (if (icd.reimbursedAmount >= 0) "+" else "-") + nf11.format(Math.abs(icd.reimbursedAmount)))
        ws.write("27", "0000" + nf3.format(ct1) + nf3.format(ct2))
        ws.write("42", icd.insuranceRef)
        ws.write("55", FuzzyValues.getLocalDateTime(icd.insuranceRefDate!!)!!.format(dtf))

        ws.writeFieldsWithCheckSum()
        return recordNumber+1
    }

    @Throws(IOException::class)
    fun writeEid(recordNumber: Int,
                 icd: InvoiceItem, patient: Patient, invoiceSender: InvoiceSender): Int {
        val ws = WriterSession(writer, Record52Description)

        if (icd.eidItem == null) { return recordNumber }

        val eidItem = icd.eidItem!!

        val nf4 = DecimalFormat("0000")

        var noSIS: String? = if (patient.ssin != null) patient.ssin else ""
        noSIS = noSIS!!.replace("[^0-9]".toRegex(), "")

        ws.write("2", recordNumber)
        ws.write("3", 0)
        ws.write("4", icd.codeNomenclature)
        ws.write("5", FuzzyValues.getLocalDateTime(icd.dateCode!!)!!.format(dtf))
        ws.write("6a", FuzzyValues.getLocalDateTime(eidItem.readDate!!)!!.format(dtf))
        ws.write("7", 0)
        ws.write("8a", noSIS)
        ws.write("9", 0)
        ws.write("10", eidItem.deviceType)
        ws.write("11", eidItem.readType)
        ws.write("12", nf4.format(eidItem.readHour))
        ws.write("14", 0)
        ws.write("15", invoiceSender.nihii)
        ws.write("16", eidItem.readvalue)

        ws.writeFieldsWithCheckSum()

        return recordNumber+1
    }

    @Throws(IOException::class)
    fun writeRecordFooter(recordNumber: Int,
                          sender: InvoiceSender,
                          invoiceNumber: Long?,
                          invoiceRef: String,
                          patient: Patient,
                          insuranceCode: String,
                          codesNomenclature: List<Long>,
                          amount: Long,
                          fee: Long,
                          sup: Long): Int {
        val ws = WriterSession(writer, Record80Description)

        val creationDate = LocalDateTime.now()
        val formattedCreationDate = creationDate.format(dtf)
        assert(formattedCreationDate.length == 8)

        var noSIS: String? = if (patient.ssin != null) patient.ssin else ""
        noSIS = noSIS!!.replace("[^0-9]".toRegex(), "")

        val nf11 = DecimalFormat("00000000000")
        val nf9 = DecimalFormat("000000000")


        ws.write("2", recordNumber)
        ws.write("7", insuranceCode)
        ws.write("8a", noSIS)
        ws.write("9", if (patient.gender == Gender.male) 1 else 2)
        ws.write("10", 3)
        ws.write("14", sender.nihii)
        ws.write("15", "+00000000000")

        //Silly rules for this field
        val destCode = getDestCode(insuranceCode, sender)

        ws.write("18", destCode)
        ws.write("19", (if (amount >= 0) "+" else "-") + nf11.format(Math.abs(amount)))
        ws.write("20", formattedCreationDate)
        ws.write("24", invoiceNumber)
        ws.write("27", (if (fee >= 0) "+" else "-") + nf9.format(Math.abs(fee)))
        ws.write("28", invoiceRef)
        ws.write("30", (if (sup >= 0) "+" else "-") + nf9.format(Math.abs(sup)))
        ws.write("32", 1)
        ws.write("38", "+00000000000")

        var cs = BigInteger.ZERO
        for (`val` in codesNomenclature) {
            cs = cs.add(BigInteger.valueOf(`val`))
        }
        val modulo = cs.mod(BigInteger.valueOf(97)).toLong()

        ws.write("98", if (modulo == 0L) 97 else modulo)

        ws.writeFieldsWithCheckSum()
        return recordNumber+1
    }

    @Throws(IOException::class)
    fun writeFileFooter(recordNumber: Int,
                        sender: InvoiceSender,
                        sendingNumber: Long?,
                        invoicingYear: Int?,
                        invoicingMonth: Int?,
                        codesNomenclature: List<Long>,
                        amount: Long?): Int {
        val ws = WriterSession(writer, Record90Description)

        val creationDate = LocalDateTime.now()
        val formattedCreationDate = creationDate.format(dtf)

        assert(formattedCreationDate.length == 8)
        val nf = DecimalFormat("00000000000")

        ws.write("1", 90)
        ws.write("2", recordNumber)
        ws.write("7", sendingNumber)
        ws.write("14", sender.nihii)
        ws.write("15", "+00000000000")
        ws.write("19", (if ((amount ?: 0) >= 0) "+" else "-") + nf.format(Math.abs(amount!!)))
        ws.write("22", invoicingYear)
        ws.write("23", invoicingMonth)
        ws.write("27", sender.bce)
        ws.write("28", invoicingYear!! * 100 + invoicingMonth!!)
        ws.write("31", sender.bic)
        ws.write("36", sender.iban)

        var cs = BigInteger.ZERO
        for (`val` in codesNomenclature) {
            cs = cs.add(BigInteger.valueOf(`val`))
        }
        val modulo = cs.mod(BigInteger.valueOf(97)).toLong()
        ws.write("98", if (modulo == 0L) 97 else modulo)

        ws.writeFieldsWithCheckSum()

        return recordNumber+1
    }
}
