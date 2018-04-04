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

package org.taktik.freehealth.middleware.domain

import org.taktik.freehealth.middleware.dto.common.IdentifierType
import java.io.Serializable

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 07/12/12
 * Time: 18:13
 * To change this template use File | Settings | File Templates.
 */
class QualityType : Serializable {
    var quality: String? = null
    var identifierType: IdentifierType? = null

    constructor() {}

    constructor(quality: String, identfierType: IdentifierType) {
        this.quality = quality
        this.identifierType = identfierType
    }

}
