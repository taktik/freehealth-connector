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

class ErrorMessage(
    id: String? = null,
    publicationId: String? = null,
    sender: Addressee? = null,
    mandatee: Addressee? = null,
    destinations: List<Addressee>? = null,
    size: String? = null,
    customMetas: Map<String, String>? = null,
    val title: String? = null,
    val errorPublicationId: String? = null,
    val errorCode: String? = null,
    val errorMsg: List<String> = listOf()
) : Message(
    id = id,
    publicationId = publicationId,
    sender = sender,
    mandatee = mandatee,
    destinations = destinations,
    size = size,
    customMetas = customMetas
)
