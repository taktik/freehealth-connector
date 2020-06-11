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

package org.taktik.freehealth.middleware.dto.mhm

import be.cin.types.v1.FaultType
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError


class StartSubscriptionResultWithResponse(
    reference: String? = null,
    subscriptionsStartDate: Int? = null,
    inscriptionDate: Int? = null,
    commonOutput: CommonOutput? = null,
    var xades: ByteArray?,
    mycarenetConversation: MycarenetConversation? = null,
    var kmehrMessage: ByteArray?,
    var errors: List<MycarenetError>? = null
                                         ): StartSubscriptionResult(reference, subscriptionsStartDate, inscriptionDate, commonOutput, mycarenetConversation)
