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

@file:Suppress("DEPRECATION")

package org.taktik.freehealth.middleware.mapper

import org.taktik.connector.business.ehbox.api.domain.Addressee
import org.taktik.connector.business.ehbox.api.domain.Document
import org.taktik.connector.business.ehbox.api.domain.AcknowledgeMessage
import org.taktik.connector.business.ehbox.api.domain.DocumentMessage
import org.taktik.connector.business.ehbox.api.domain.ErrorMessage
import org.taktik.connector.business.ehbox.api.domain.Message
import org.taktik.connector.business.ehbox.api.domain.NewsMessage
import org.taktik.freehealth.middleware.dto.common.IdentifierType
import org.taktik.freehealth.utils.FuzzyValues
import java.io.UnsupportedEncodingException
import java.time.temporal.ChronoUnit

fun <T> Message<T>.toMessageDto(): org.taktik.freehealth.middleware.dto.ehbox.Message? = when {
    this is NewsMessage -> this.toNewsMessage()
    this is AcknowledgeMessage -> this.toAcknowledgeMessage()
    this is DocumentMessage -> this.toDocumentMessage()
    this is ErrorMessage -> this.toErrorMessage()
    else -> null
}

fun <T> DocumentMessage<T>.toDocumentMessage(): org.taktik.freehealth.middleware.dto.ehbox.DocumentMessage =
    org.taktik.freehealth.middleware.dto.ehbox.DocumentMessage(
        id = id,
        publicationId = publicationId,
        sender = sender?.toAddresseeDto(),
        mandatee = mandatee?.toAddresseeDto(),
        destinations = getDestinations().map { it.toAddresseeDto() },
        important = isImportant,
        encrypted = isEncrypted,
        usePublicationReceipt = isUsePublicationReceipt,
        useReceivedReceipt = isUseReceivedReceipt,
        useReadReceipt = isUseReadReceipt,
        hasAnnex = isHasAnnex,
        hasFreeInformations = isHasFreeInformations,
        publicationDateTime = publicationDateTime?.let { FuzzyValues.getFuzzyDate(it, ChronoUnit.SECONDS) },
        expirationDateTime = expirationDateTime?.let { FuzzyValues.getFuzzyDate(it, ChronoUnit.SECONDS) },
        size = size,
        customMetas = getCustomMetas(),
        document = document?.toDocumentDto(),
        freeText = freeText,
        patientInss = patientInss,
        annex = annexList.map { it.toDocumentDto() },
        freeInformationTableTitle = freeInformationTableTitle,
        freeInformationTableRows = freeInformationTableRows,
        copyMailTo = copyMailTo
    )

fun <T> NewsMessage<T>.toNewsMessage(): org.taktik.freehealth.middleware.dto.ehbox.NewsMessage =
    org.taktik.freehealth.middleware.dto.ehbox.NewsMessage(
        id = id,
        publicationId = publicationId,
        sender = sender?.toAddresseeDto(),
        mandatee = mandatee?.toAddresseeDto(),
        destinations = getDestinations().map { it.toAddresseeDto() },
        important = isImportant,
        encrypted = isEncrypted,
        usePublicationReceipt = isUsePublicationReceipt,
        useReceivedReceipt = isUseReceivedReceipt,
        useReadReceipt = isUseReadReceipt,
        hasAnnex = isHasAnnex,
        hasFreeInformations = isHasFreeInformations,
        publicationDateTime = publicationDateTime?.let { FuzzyValues.getFuzzyDate(it, ChronoUnit.SECONDS) },
        expirationDateTime = expirationDateTime?.let { FuzzyValues.getFuzzyDate(it, ChronoUnit.SECONDS) },
        size = size,
        customMetas = getCustomMetas(),
        document = document?.toDocumentDto(),
        freeText = freeText,
        patientInss = patientInss,
        annex = annexList.map { it.toDocumentDto() },
        freeInformationTableTitle = freeInformationTableTitle,
        freeInformationTableRows = freeInformationTableRows,
        copyMailTo = copyMailTo
    )

fun <T> AcknowledgeMessage<T>.toAcknowledgeMessage(): org.taktik.freehealth.middleware.dto.ehbox.AcknowledgeMessage =
    org.taktik.freehealth.middleware.dto.ehbox.AcknowledgeMessage(
        id = id,
        publicationId = publicationId,
        sender = sender?.toAddresseeDto(),
        mandatee = mandatee?.toAddresseeDto(),
        destinations = getDestinations().map { it.toAddresseeDto() },
        isImportant = isImportant,
        isEncrypted = isEncrypted,
        isUsePublicationReceipt = isUsePublicationReceipt,
        isUseReceivedReceipt = isUseReceivedReceipt,
        isUseReadReceipt = isUseReadReceipt,
        isHasAnnex = isHasAnnex,
        isHasFreeInformations = isHasFreeInformations,
        publicationDateTime = publicationDateTime?.let { FuzzyValues.getFuzzyDate(it, ChronoUnit.SECONDS) },
        expirationDateTime = expirationDateTime?.let { FuzzyValues.getFuzzyDate(it, ChronoUnit.SECONDS) },
        size = size,
        customMetas = getCustomMetas(),
        document = if(original is be.fgov.ehealth.ehbox.consultation.protocol.v3.Message) (original as be.fgov.ehealth.ehbox.consultation.protocol.v3.Message).toDocument() else null
    )

fun be.fgov.ehealth.ehbox.consultation.protocol.v3.Message.toDocument(): org.taktik.freehealth.middleware.dto.common.Document =
    org.taktik.freehealth.middleware.dto.common.Document(
        title = contentInfo.title,
        content = null,
        textContent = null,
        filename = null,
        mimeType = contentInfo.mimeType
    )

fun Document.toDocumentDto(): org.taktik.freehealth.middleware.dto.common.Document =
    org.taktik.freehealth.middleware.dto.common.Document(
        title = title, content = getContent(), textContent = getContent().let {
        if (mimeType == "text/plain") {
            try {
                String(it, Charsets.UTF_8)
            } catch (e: UnsupportedEncodingException) {
                null
            }
        } else null
    }, filename = filename, mimeType = mimeType
    )

fun <T> ErrorMessage<T>.toErrorMessage(): org.taktik.freehealth.middleware.dto.ehbox.ErrorMessage =
    org.taktik.freehealth.middleware.dto.ehbox.ErrorMessage(
        id = id,
        publicationId = publicationId,
        sender = sender?.toAddresseeDto(),
        mandatee = mandatee?.toAddresseeDto(),
        destinations = getDestinations().map { it.toAddresseeDto() },
        size = size,
        customMetas = getCustomMetas(),
        title = title + " " + errorMsg.joinToString(" "),
        errorPublicationId = errorPublicationId,
        errorCode = errorCode
    )

fun Addressee.toAddresseeDto(): org.taktik.freehealth.middleware.dto.common.Addressee =
    org.taktik.freehealth.middleware.dto.common.Addressee(
        identifierType = IdentifierType(
            identifierTypeHelper.getType(org.taktik.connector.technical.utils.IdentifierType.EHBOX) ?: ""
        ),
        id = id,
        quality = quality,
        applicationId = applicationId,
        lastName = lastName,
        firstName = firstName,
        organizationName = organizationName,
        personInOrganisation = personInOrganisation
    )

fun org.taktik.freehealth.middleware.dto.ehbox.DocumentMessage.toDocumentMessage(): DocumentMessage<be.fgov.ehealth.ehbox.consultation.protocol.v3.Message> =
    DocumentMessage<be.fgov.ehealth.ehbox.consultation.protocol.v3.Message>().apply {
        val that = this@toDocumentMessage
        id = that.id
        publicationId = that.publicationId
        sender = that.sender?.toAddressee()
        mandatee = that.mandatee?.toAddressee()
        that.destinations?.let { getDestinations().addAll(it.map { it.toAddressee() }) }
        isImportant = that.important
        isEncrypted = that.encrypted
        isUsePublicationReceipt = that.usePublicationReceipt
        isUseReceivedReceipt = that.useReceivedReceipt
        isUseReadReceipt = that.useReadReceipt
        isHasAnnex = that.hasAnnex
        isHasFreeInformations = that.hasFreeInformations
        publicationDateTime = that.publicationDateTime?.let { FuzzyValues.getJodaDateTime(that.publicationDateTime) }
        expirationDateTime = that.expirationDateTime?.let { FuzzyValues.getJodaDateTime(that.expirationDateTime) }
        size = that.size
        that.customMetas?.let { getCustomMetas().putAll(from = it) }
        document = that.document?.toDocument()
        freeText = that.freeText
        patientInss = that.patientInss
        annexList.addAll(that.annexList.map { it.toDocument() })
        freeInformationTableTitle = that.freeInformationTableTitle
        freeInformationTableRows = that.freeInformationTableRows as MutableMap<String, String>
        copyMailTo.addAll(that.copyMailTo)
    }

fun org.taktik.freehealth.middleware.dto.common.Addressee.toAddressee(): Addressee =
    Addressee(identifierType?.type?.let { org.taktik.connector.technical.utils.IdentifierType.valueOf(it) }).apply {
        val that = this@toAddressee
        id = that.id
        quality = that.quality
        applicationId = that.applicationId
        lastName = that.lastName
        firstName = that.firstName
        organizationName = that.organizationName
        personInOrganisation = that.personInOrganisation
    }

fun org.taktik.freehealth.middleware.dto.common.Document.toDocument(): Document = Document().apply {
    val that = this@toDocument
    title = that.title
    setContent(that.content ?: that.textContent?.toByteArray(Charsets.UTF_8))
    filename = that.filename
    mimeType = that.mimeType
}
