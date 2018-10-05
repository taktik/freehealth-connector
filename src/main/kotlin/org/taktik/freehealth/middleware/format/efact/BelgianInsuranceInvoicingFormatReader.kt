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

import org.taktik.freehealth.middleware.format.ReaderSession
import org.taktik.freehealth.middleware.format.efact.segments.Record10Description
import org.taktik.freehealth.middleware.format.efact.segments.Record20Description
import org.taktik.freehealth.middleware.format.efact.segments.Record30Description
import org.taktik.freehealth.middleware.format.efact.segments.Record50Description
import org.taktik.freehealth.middleware.format.efact.segments.Record51Description
import org.taktik.freehealth.middleware.format.efact.segments.Record52Description
import org.taktik.freehealth.middleware.format.efact.segments.Record80Description
import org.taktik.freehealth.middleware.format.efact.segments.Record90Description
import org.taktik.freehealth.middleware.format.efact.segments.RecordOrSegmentDescription
import org.taktik.freehealth.middleware.format.efact.segments.Segment200Description
import org.taktik.freehealth.middleware.format.efact.segments.Segment300Description
import org.taktik.freehealth.middleware.format.efact.segments.Segment300ErrorDescription
import org.taktik.freehealth.middleware.format.efact.segments.Segment400Record95Description
import org.taktik.freehealth.middleware.format.efact.segments.Segment500Record96Description
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
    fun parse(reader: Reader, parseErrors: Boolean = true): List<Record<*>>? {
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
                        "10" -> this.add(Record(Record10Description, Record10Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }).apply { if (parseErrors) this.errorDetail = readErrorDetails(session, this) })
                        "20" -> this.add(Record(Record20Description, Record20Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }).apply { if (parseErrors) this.errorDetail = readErrorDetails(session, this) })
                        "30" -> this.add(Record(Record30Description, Record30Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }).apply { if (parseErrors) this.errorDetail = readErrorDetails(session, this) })
                        "50" -> this.add(Record(Record50Description, Record50Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }).apply { if (parseErrors) this.errorDetail = readErrorDetails(session, this) })
                        "51" -> this.add(Record(Record51Description, Record51Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }).apply { if (parseErrors) this.errorDetail = readErrorDetails(session, this) })
                        "52" -> this.add(Record(Record52Description, Record52Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }).apply { if (parseErrors) this.errorDetail = readErrorDetails(session, this) })
                        "80" -> this.add(Record(Record80Description, Record80Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }).apply { if (parseErrors) this.errorDetail = readErrorDetails(session, this) })
                        "90" -> this.add(Record(Record90Description, Record90Description.zoneDescriptions.map { zd -> Zone(zd, session.read(zd.label, zd.length)) }).apply { if (parseErrors) this.errorDetail = readErrorDetails(session, this) })

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
    private fun readErrorDetails(reader: ReaderSession, record: Record<*>) = ErrorDetail().apply {
        this.sendingId = reader.readInt("Identification envoi", 3)
        this.creationDate = reader.readInt("Date creation envoi", 8)
        this.invoicingYearMonth = reader.readInt("Mois et annee de facturation", 6)
        try {
            this.mutualityCode = reader.readInt("Mutualite", 3)
        } catch (ignored: NumberFormatException) {
        }

        reader.read("", 86)
        this.rejectionLetter1 = reader.read("Lettre rejet 1", 1)
        this.rejectionCode1 = reader.read("Code rejet 1", 6)
        this.rejectionLetter2 = reader.read("Lettre rejet 2", 1)
        this.rejectionCode2 = reader.read("Code rejet 2", 6)
        this.rejectionLetter3 = reader.read("Lettre rejet 3", 1)
        this.rejectionCode3 = reader.read("Code rejet 3", 6)
        reader.read("", 44)
        this.oaResult = reader.read("Resultat OA", 12)
        this.errorCodeComment = reader.read("Commentaire du code erreur", 200)
        this.reserve = reader.read("Reserve", 61)
        this.index = reader.readInt("Index", 6)

        this.rejectionCode1?.let {
            if (it != "000000" && "BREFS".contains(this.rejectionLetter1!!)) {
                this.rejectionDescr1 = getErrorCodeDescription(this.rejectionLetter1 + this.rejectionCode1, null)
                val zoneDescription = record.description!!.zoneDescriptionsByZone[it.substring(2, 4)]
                this.rejectionZoneDescr1 = zoneDescription?.label ?: ""
            }
        }

        this.rejectionCode2?.let {
            if (it != "000000" && "BREFS".contains(this.rejectionLetter2!!)) {
                this.rejectionDescr2 = getErrorCodeDescription(this.rejectionLetter2 + this.rejectionCode2, null)
                val zoneDescription = record.description!!.zoneDescriptionsByZone[it.substring(2, 4)]
                this.rejectionZoneDescr2 = zoneDescription?.label ?: ""
            }
        }

        this.rejectionCode3?.let {
            if (it != "000000" && "BREFS".contains(this.rejectionLetter3!!)) {
                this.rejectionDescr3 = getErrorCodeDescription(this.rejectionLetter3 + this.rejectionCode3, null)
                val zoneDescription = record.description!!.zoneDescriptionsByZone[it.substring(2, 4)]
                this.rejectionZoneDescr3 = zoneDescription?.label ?: ""
            }
        }
    }

}
