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

package org.taktik.freehealth.middleware.dto.common

import java.io.Serializable

import org.apache.commons.lang.StringUtils.defaultString
import org.apache.commons.lang.StringUtils.isEmpty

/**
 * Created by aduchate on 8/11/13, 16:16
 */
class Addressee(
    val identifierType: IdentifierType,
    val id: String? = null,
    val quality: String? = null,
    val applicationId: String? = null,
    val lastName: String? = null,
    val firstName: String? = null,
    val organizationName: String? = null,
    val personInOrganisation: String? = null
) : Serializable {
    override fun toString(): String {
        return if (isEmpty(organizationName)) String.format(
            "%s %s %s",
            defaultString(quality!!.toString()),
            defaultString(firstName),
            defaultString(lastName)
        )
        else String.format(
            "%s %s - %s %s %s",
            defaultString(organizationName),
            defaultString(personInOrganisation),
            defaultString(quality!!.toString()),
            defaultString(firstName),
            defaultString(lastName)
        )
    }
}
