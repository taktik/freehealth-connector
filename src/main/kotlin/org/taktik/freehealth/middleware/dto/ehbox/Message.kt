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

package org.taktik.freehealth.middleware.dto.ehbox

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.taktik.freehealth.middleware.dto.common.Addressee
import java.io.Serializable

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = DocumentMessage::class, name = "DocumentMessage"),
    JsonSubTypes.Type(value = AcknowledgeMessage::class, name = "AcknowledgeMessage"),
    JsonSubTypes.Type(value = ErrorMessage::class, name = "ErrorMessage")
)
abstract class Message(
    val id: String? = null,
    val publicationId: String? = null,
    val sender: Addressee? = null,
    val mandatee: Addressee? = null,
    val destinations: List<Addressee>? = null,
    val important: Boolean = false,
    val encrypted: Boolean = false,
    val usePublicationReceipt: Boolean = false,
    val useReceivedReceipt: Boolean = false,
    val useReadReceipt: Boolean = false,
    val hasAnnex: Boolean = false,
    val hasFreeInformations: Boolean = false,
    val publicationDateTime: Long? = null,
    val expirationDateTime: Long? = null,
    val size: String? = null,
    val customMetas: Map<String, String>? = null
) : Serializable
