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

import org.joda.time.DateTime
import org.taktik.freehealth.middleware.dto.common.AuthorDto
import org.taktik.freehealth.middleware.dto.common.KmehrCd
import org.taktik.freehealth.middleware.dto.common.KmehrId

import java.io.Serializable

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 11/12/12
 * Time: 21:29
 * To change this template use File | Settings | File Templates.
 */
class TransactionSummary : Serializable {
    var ids: List<KmehrId> = mutableListOf()
    var cds: List<KmehrCd> = mutableListOf()
    var date: Long? = null
    var time: Long? = null
    var author: AuthorDto? = null
    var iscomplete: Boolean = false
    var isvalidated: Boolean = false
    var recorddatetime: Long? = null
    var dateTime: Long? = null
    var authorsList: String? = null
    var desc: String? = null
}
