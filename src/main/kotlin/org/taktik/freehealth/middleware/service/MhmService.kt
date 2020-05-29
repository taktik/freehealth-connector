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

import org.taktik.freehealth.middleware.dto.eattest.Eattest
import org.taktik.freehealth.middleware.dto.eattest.SendAttestResultWithResponse
import org.taktik.freehealth.middleware.dto.mhm.CancelSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.dto.mhm.EndSubscriptionResultWithResponse
import org.taktik.freehealth.middleware.dto.mhm.StartSubscriptionResultWithResponse
import java.util.*

interface MhmService {
    fun startSubscription(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpName: String,
        hcpCbe: String,
        patientSsin: String?,
        patientFirstName:String,
        patientLastName:String,
        patientGender:String,
        io: String,
        ioMembership: String?,
        startDate: Int,
        isTrial: Boolean,
        signatureType: String
                         ): StartSubscriptionResultWithResponse?


    fun cancelSubscription(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpName: String,
        hcpCbe: String,
        patientSsin: String,
        patientFirstName:String,
        patientLastName:String,
        patientGender:String,
        io: String,
        ioMembership: String,
        reference: String,
        endDate: Int,
        reason: String
                         ): CancelSubscriptionResultWithResponse?

    fun endSubscription(
        keystoreId: UUID,
        tokenId: UUID,
        passPhrase: String,
        hcpNihii: String,
        hcpName: String,
        hcpCbe: String,
        patientSsin: String,
        patientFirstName:String,
        patientLastName:String,
        patientGender:String,
        io: String,
        ioMembership: String,
        reference: String,
        endDate: Int,
        reason: String
                          ): EndSubscriptionResultWithResponse?
}
