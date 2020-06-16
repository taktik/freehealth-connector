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

package org.taktik.freehealth.middleware.service

import org.taktik.connector.business.domain.common.GenAsyncResponse
import org.taktik.connector.business.domain.dmg.DmgsList
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataBatchRequest
import org.taktik.freehealth.middleware.domain.memberdata.MemberDataResponse
import org.taktik.freehealth.middleware.dto.memberdata.MemberDataResponseDto
import org.taktik.icure.cin.saml.extensions.Facet
import java.time.Instant
import java.util.UUID

interface MemberDataService {
    fun getMemberData(keystoreId: UUID,
        tokenId: UUID,
        hcpQuality: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpName: String,
        passPhrase: String,
        patientSsin: String?,
        io: String?,
        ioMembership: String?,
        startDate: Instant,
        endDate: Instant,
        hospitalized: Boolean? = null,
        requestType: String?,
        facets: List<Facet>? = null): MemberDataResponse

    fun sendMemberDataRequest(
        keystoreId: UUID,
        tokenId: UUID,
        hcpQuality: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpName: String,
        requestType: String = "information",
        io: String?,
        startDate: Instant,
        endDate: Instant,
        passPhrase: String,
        hospitalized: Boolean? = false,
        mdaRequest: MemberDataBatchRequest
                             ): GenAsyncResponse

    fun getMemberDataMessages(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpName: String,
        messageNames: List<String>?
    )
}
