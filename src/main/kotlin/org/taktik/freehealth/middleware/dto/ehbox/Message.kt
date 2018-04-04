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

import org.taktik.freehealth.middleware.dto.common.Addressee
import java.io.Serializable

abstract class Message(
        val id: String? = null,
        val publicationId: String? = null,
        val sender: Addressee? = null,
        val mandatee: Addressee? = null,
        val destinations: List<Addressee>? = null,
        val isImportant: Boolean = false,
        val isEncrypted: Boolean = false,
        val isUsePublicationReceipt: Boolean = false,
        val isUseReceivedReceipt: Boolean = false,
        val isUseReadReceipt: Boolean = false,
        val isHasAnnex: Boolean = false,
        val isHasFreeInformations: Boolean = false,
        val publicationDateTime: Long? = null,
        val expirationDateTime: Long? = null,
        val size: String? = null,
        val customMetas: Map<String, String>? = null
) : Serializable
