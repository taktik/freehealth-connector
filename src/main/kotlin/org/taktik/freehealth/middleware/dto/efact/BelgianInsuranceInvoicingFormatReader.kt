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

package org.taktik.freehealth.middleware.dto.efact

import org.taktik.freehealth.middleware.format.ReaderSession
import org.taktik.freehealth.middleware.dto.efact.segments.Record10Description
import org.taktik.freehealth.middleware.dto.efact.segments.Record20Description
import org.taktik.freehealth.middleware.dto.efact.segments.Record30Description
import org.taktik.freehealth.middleware.dto.efact.segments.Record50Description
import org.taktik.freehealth.middleware.dto.efact.segments.Record51Description
import org.taktik.freehealth.middleware.dto.efact.segments.Record52Description
import org.taktik.freehealth.middleware.dto.efact.segments.Record80Description
import org.taktik.freehealth.middleware.dto.efact.segments.Record90Description
import org.taktik.freehealth.middleware.dto.efact.segments.RecordOrSegmentDescription
import org.taktik.freehealth.middleware.dto.efact.segments.Segment200Description
import org.taktik.freehealth.middleware.dto.efact.segments.Segment300Description
import org.taktik.freehealth.middleware.dto.efact.segments.Segment300ErrorDescription
import org.taktik.freehealth.middleware.dto.efact.segments.Segment400Record95Description
import org.taktik.freehealth.middleware.dto.efact.segments.Segment500Record96Description
import org.taktik.freehealth.middleware.serialize.UTF8Control
import java.io.EOFException

import java.io.IOException
import java.io.Reader
import java.io.StringReader
import java.util.Locale
import java.util.ResourceBundle

class BelgianInsuranceInvoicingFormatReader(private val language: String) {

    private var errorCodesBundle: ResourceBundle? = null
    private var messagesBundle: ResourceBundle? = null

    fun getMessageDescription(errorCode: String, message: String?): String? {
        try {
            if (messagesBundle == null) {
                messagesBundle =
                    ResourceBundle.getBundle(this.javaClass.getPackage().name + ".messages", Locale(language), UTF8Control())
            }
            return messagesBundle!!.getString(errorCode)
        } catch (e: Exception) {
            return message
        }

    }

    fun getErrorCodeDescription(errorCode: String, message: String?): String? {
        try {
            if (errorCodesBundle == null) {
                errorCodesBundle =
                    ResourceBundle.getBundle(this.javaClass.getPackage().name + "efacterrorcodes", Locale(language), UTF8Control())
            }
            return errorCodesBundle!!.getString(errorCode)
        } catch (e: Exception) {
            return message
        }
    }

    @Throws(IOException::class)
    fun parse(reader: Reader): List<Record<*>>? {
        val session = ReaderSession(reader)
        return mutableListOf<Record<*>>().apply {
            loop@ while(true) {
                try {
                    val messageType = session.messageType
                    when (messageType.substring(0, 2)) {
                        "92" -> {
                            this.add(Record(Segment200Description, Segment200Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                            if (messageType.substring(2, 6) == "0000") {
                                this.add(Record(Segment300Description, Segment300Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                            } else {
                                this.add(Record(Segment300ErrorDescription, Segment300ErrorDescription.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                            }

                        }
                        "93" -> {
                            this.add(Record(Segment200Description, Segment200Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                            this.add(Record(Segment300Description, Segment300Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                        }
                        "10" -> this.add(Record(Record10Description, Record10Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                        "20" -> this.add(Record(Record20Description, Record20Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                        "30" -> this.add(Record(Record30Description, Record30Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                        "50" -> this.add(Record(Record50Description, Record50Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                        "51" -> this.add(Record(Record51Description, Record51Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                        "52" -> this.add(Record(Record52Description, Record52Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                        "80" -> this.add(Record(Record80Description, Record80Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                        "90" -> this.add(Record(Record90Description, Record90Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                        "95" -> this.add(Record(Segment400Record95Description, Segment400Record95Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                        "96" -> this.add(Record(Segment500Record96Description, Segment500Record96Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }))
                        else -> break@loop
                    }
                } catch (e: EOFException) {
                    break
                }
            }
        }
    }

    @Throws(IOException::class)
    fun read920000(message: String): Message {
        val reader = ReaderSession(StringReader(message))

        return Message().apply {
            segment200 = Record(Segment200Description, Segment200Description.zoneDescriptions.map { zd -> Zone(zd, reader.read(zd.label, zd.length)) })
            segment300 = Record(Segment300Description, Segment300Description.zoneDescriptions.map { zd -> Zone(zd, reader.read(zd.label, zd.length)) })
            readErrorDetails(reader, this)
        }
    }

    @Throws(IOException::class)
    fun read920098(message: String): Message {
        return read92Generic(message)
    }

    @Throws(IOException::class)
    private fun read92Generic(message: String): Message {
        val reader = ReaderSession(StringReader(message))

        return Message().apply {
            segment200 = Record(Segment200Description, Segment200Description.zoneDescriptions.map { zd -> Zone(zd, reader.read(zd.label, zd.length)) })
            segment300Error = Record(Segment300ErrorDescription, Segment300ErrorDescription.zoneDescriptions.map { zd -> Zone(zd, reader.read(zd.label, zd.length)) })
            readErrorDetails(reader, this)
        }
    }

    @Throws(IOException::class)
    fun read920099(message: String): Message {
        val reader = ReaderSession(StringReader(message))

        return Message().apply {
            segment200 = Record(Segment200Description, Segment200Description.zoneDescriptions.map { zd -> Zone(zd, reader.read(zd.label, zd.length)) })
            segment300Error = Record(Segment300ErrorDescription, Segment300ErrorDescription.zoneDescriptions.map { zd -> Zone(zd, reader.read(zd.label, zd.length)) })
            readErrorDetails(reader, this)
        }
    }

    @Throws(IOException::class)
    fun read920900(message: String): Message {
        return read92Generic(message)
    }

    @Throws(IOException::class)
    fun read920999(message: String): Message {
        val reader = ReaderSession(StringReader(message))

        return Message().apply {
            segment200 = Record(Segment200Description, Segment200Description.zoneDescriptions.map { zd -> Zone(zd, reader.read(zd.label, zd.length)) })
            segment300 = Record(Segment300Description, Segment300Description.zoneDescriptions.map { zd -> Zone(zd, reader.read(zd.label, zd.length)) })
            readErrorDetails(reader, this)
            readInvoiceReceipt(reader, this)

            var receipt95HasBeenRead = readReceipt95(reader, this)
            while (receipt95HasBeenRead) {
                receipt95HasBeenRead = readReceipt95(reader, this)
            }
        }
    }

    @Throws(IOException::class)
    fun read931000(message: String): Message {
        val reader = ReaderSession(StringReader(message))

        return Message().apply {
            segment200 = Record(Segment200Description, Segment200Description.zoneDescriptions.map { zd -> Zone(zd, reader.read(zd.label, zd.length)) })
            readAcknowledgment(reader, this)
        }
    }

    @Throws(IOException::class)
    private fun readInvoiceRecord(reader: ReaderSession): Record<RecordOrSegmentDescription>? {
        return try {
            when (reader.peekInt("InvoiceRecordType", 2)) {
                10 -> Record10Description
                20 -> Record20Description
                30 -> Record30Description
                50 -> Record50Description
                51 -> Record51Description
                52 -> Record52Description
                80 -> Record80Description
                90 -> Record90Description
                else -> null
            }
        } catch (e: IOException) {
            null
        }?.let { rec ->
            Record(rec, rec.zoneDescriptions.map { zd -> Zone(zd, reader.read(zd.label, zd.length)) })
        }
    }

    @Throws(IOException::class)
    private fun readAcknowledgment(reader: ReaderSession, invoicing: Message) {
        val acknowledgment = Acknowledgment()
        acknowledgment.messageName = reader.read("Nom du message vise par cette communication", 6)
        reader.readInt("Code erreur", 2)
        acknowledgment.reserve = reader.read("Reserve", 152)
        invoicing.acknowledgment = acknowledgment
    }

    private fun readReceipt95(reader: ReaderSession, invoicing: Message): Boolean {
        try {
            val receipt = Receipt95()
            receipt.type = reader.readInt("Type de record", 2)
            reader.readInt("Code erreur", 2)
            receipt.mutualityCode = reader.readInt("Numero de mutualite", 3)
            reader.readInt("Code erreur", 2)
            receipt.invoiceRecapNumber = reader.readLong("Numero de facture recapitulative", 12)
            reader.readInt("Code erreur", 2)
            receipt.accountARequestedAmountSign = reader.read("Signe montant demande compte A", 1)
            receipt.accountARequestedAmount = reader.readLong("Montant demande compte A", 11)
            reader.readInt("Code erreur", 2)
            receipt.accountBRequestedAmountSign = reader.read("Signe montant demande code B", 1)
            receipt.accountBRequestedAmount = reader.readLong("Montant demande compte B", 11)
            reader.readInt("Code erreur", 2)
            receipt.accountsABTotalRequestedAmountSign = reader.read("Signe montant demande compte A + compte B", 1)
            receipt.accountsABTotalRequestedAmount = reader.readLong("Total montants demandes compte A + compte B", 11)
            reader.readInt("Code erreur", 2)
            receipt.recordsAmount = reader.readInt("Nombre d'enregistrements", 8)
            reader.readInt("Code erreur", 2)
            receipt.mutualityControlNumber = reader.readInt("N de controle par mutualite", 2)
            reader.readInt("Code erreur", 2)
            receipt.reserve = reader.read("Reserve", 271)
            invoicing.receipts95.add(receipt)
            return true
        } catch (e: IOException) {
            return false
        }

    }

    @Throws(IOException::class)
    private fun readMessageDetails(reader: ReaderSession, invoicing: Message) {
        val errorDetails = invoicing.errorDetails

        var invoiceRecord = readInvoiceRecord(reader)
        while (invoiceRecord != null) {
            errorDetails.add(ErrorDetail(invoiceRecord))
            invoiceRecord = readInvoiceRecord(reader)
        }
    }

    @Throws(IOException::class)
    private fun readErrorDetails(reader: ReaderSession, invoicing: Message) {
        val errorDetails = invoicing.errorDetails

        var record = readInvoiceRecord(reader)
        while (record != null) {
            val errorDetail = ErrorDetail(record)
            errorDetail.sendingId = reader.readInt("Identification envoi", 3)
            errorDetail.creationDate = reader.readInt("Date creation envoi", 8)
            errorDetail.invoicingYearMonth = reader.readInt("Mois et annee de facturation", 6)
            try {
                errorDetail.mutualityCode = reader.readInt("Mutualite", 3)
            } catch (ignored: NumberFormatException) {
            }

            reader.read("", 86)
            errorDetail.rejectionLetter1 = reader.read("Lettre rejet 1", 1)
            errorDetail.rejectionCode1 = reader.read("Code rejet 1", 6)
            errorDetail.rejectionLetter2 = reader.read("Lettre rejet 2", 1)
            errorDetail.rejectionCode2 = reader.read("Code rejet 2", 6)
            errorDetail.rejectionLetter3 = reader.read("Lettre rejet 3", 1)
            errorDetail.rejectionCode3 = reader.read("Code rejet 3", 6)
            reader.read("", 44)
            errorDetail.oaResult = reader.read("Resultat OA", 12)
            errorDetail.errorCodeComment = reader.read("Commentaire du code erreur", 200)
            errorDetail.reserve = reader.read("Reserve", 61)
            errorDetail.index = reader.readInt("Index", 6)

            errorDetail.rejectionCode1?.let {if (it != "000000" && "BREFS".contains(errorDetail.rejectionLetter1!!)) {
                errorDetail.rejectionDescr1 = getErrorCodeDescription(errorDetail.rejectionLetter1 + errorDetail.rejectionCode1, null)
                val zoneDescription= record!!.description!!.zoneDescriptionsByZone.get(it.substring(2, 4))
                errorDetail.rejectionZoneDescr1 = zoneDescription?.label ?: ""
            }}

            errorDetail.rejectionCode2?.let {if (it != "000000" && "BREFS".contains(errorDetail.rejectionLetter2!!)) {
                errorDetail.rejectionDescr2 = getErrorCodeDescription(errorDetail.rejectionLetter2 + errorDetail.rejectionCode2, null)
                val zoneDescription= record!!.description!!.zoneDescriptionsByZone.get(it.substring(2, 4))
                errorDetail.rejectionZoneDescr2 = zoneDescription?.label ?: ""
            }}

            errorDetail.rejectionCode3?.let {if (it != "000000" && "BREFS".contains(errorDetail.rejectionLetter3!!)) {
                errorDetail.rejectionDescr3 = getErrorCodeDescription(errorDetail.rejectionLetter3 + errorDetail.rejectionCode3, null)
                val zoneDescription= record!!.description!!.zoneDescriptionsByZone.get(it.substring(2, 4))
                errorDetail.rejectionZoneDescr3 = zoneDescription?.label ?: ""
            }}

            errorDetails.add(errorDetail)

            record = readInvoiceRecord(reader)
        }
    }

    @Throws(IOException::class)
    private fun readInvoiceReceipt(reader: ReaderSession, invoicing: Message) {
        val bordereau = InvoiceReceipt()
        bordereau.invoicingYearMonth = reader.readInt("Annee et mois de facturation", 6)
        reader.readInt("Code erreur", 2)
        bordereau.sendNumber = reader.readInt("Numero d'envoi", 3)
        reader.readInt("Code erreur", 2)
        bordereau.invoiceCreationDate = reader.readInt("Date de creation de la facture", 8)
        reader.readInt("Code erreur", 2)
        bordereau.invoiceReference = reader.read("Reference de la facture", 13)
        reader.readInt("Code erreur", 2)
        bordereau.instructionsVersion = reader.readInt("Numero de version des instructions", 7)
        reader.readInt("Code erreur", 2)
        bordereau.contactPersonLastName = reader.read("Nom de la personne de contact", 45)
        reader.readInt("Code erreur", 2)
        bordereau.contactPersonFirstName = reader.read("Prenom de la personne de contact", 24)
        reader.readInt("Code erreur", 2)
        bordereau.contactPersonPhone = reader.read("Numero de telephone de la personne de contact", 10)
        reader.readInt("Code erreur", 2)
        bordereau.invoiceType = reader.readInt("Type de facture", 2)
        reader.readInt("Code erreur", 2)
        bordereau.invoicingType = reader.readInt("Type de facturation", 2)
        reader.readInt("Code erreur", 2)
        bordereau.reserve = reader.read("Reserve", 20)
        invoicing.invoiceReceipt = bordereau
    }

    @Throws(IOException::class)
    fun read(message: String): Message {
        val reader = ReaderSession(StringReader(message))
        val messageType = reader.messageType
        return when (messageType) {
            "920000" -> read920000(message)
            "920098" -> read920098(message)
            "920099" -> read920099(message)
            "920900" -> read920900(message)
            "920999" -> read920999(message)
            "931000" -> read931000(message)
            else -> throw RuntimeException("Unknown message type for message: $message")
        }
    }
}
