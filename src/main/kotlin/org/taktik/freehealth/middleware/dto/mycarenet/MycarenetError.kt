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

package org.taktik.freehealth.middleware.dto.mycarenet

class MycarenetError(
    var uid: String? = null,
    var path: String? = null,
    var regex: String? = null,
    var locFr: String? = null,
    var locNl: String? = null,
    var msgFr: String? = null,
    var msgNl: String? = null,
    var msgEn: String? = null,
    var code: String? = null,
    var subCode: String? = null,
    var faultCode: String? = null,
    var faultSource: String? = null,
    var detailCode: String? = null,
    var detailSource: String? = null,
    var value: String? = null
)
