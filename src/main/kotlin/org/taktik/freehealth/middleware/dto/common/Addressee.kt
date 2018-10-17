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
    var identifierType: IdentifierType? = null,
    var id: String? = null,
    var quality: String? = null,
    var applicationId: String? = null,
    var lastName: String? = null,
    var firstName: String? = null,
    var organizationName: String? = null,
    var personInOrganisation: String? = null
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
