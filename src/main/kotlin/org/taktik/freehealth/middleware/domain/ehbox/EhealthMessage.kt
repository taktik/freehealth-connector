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

package org.taktik.freehealth.middleware.domain.ehbox

import org.taktik.freehealth.middleware.dto.common.Addressee
import java.io.Serializable

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 04/10/12
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
open class EhealthMessage : Serializable {
    var id: String? = null
    var publicationId: String? = null
    var sender: Addressee? = null
    var mandatee: Addressee? = null
    var destinations: List<Addressee>? = null
    var isImportant: Boolean = false
    var isEncrypted: Boolean = false
    var isHasAnnex: Boolean = false
    var isHasFreeInformations: Boolean = false
    var publicationDate: Long? = null
    var expirationDate: Long? = null
    var size: String? = null
    var customMetas: Map<String, String>? = null
}
