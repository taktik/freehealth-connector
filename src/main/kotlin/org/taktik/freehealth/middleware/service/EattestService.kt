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
import java.util.*

interface EattestService {
    fun sendAttest(
        keystoreId: UUID,
        tokenId: UUID,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        hcpCbe: String,
        traineeSupervisorSsin: String?,
        traineeSupervisorNihii: String?,
        traineeSupervisorFirstName: String?,
        traineeSupervisorLastName: String?,
        guardPostNihii: String?,
        guardPostSsin: String?,
        guardPostName: String?,
        passPhrase: String,
        patientSsin: String,
        patientFirstName:String,
        patientLastName:String,
        patientGender:String,
        referenceDate: Int?,
        attest: Eattest
    ): SendAttestResultWithResponse?

    fun sendAttestV2(
        keystoreId: UUID,
        tokenId: UUID,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        hcpCbe: String,
        treatmentReason : String?,
        traineeSupervisorSsin: String?,
        traineeSupervisorNihii: String?,
        traineeSupervisorFirstName: String?,
        traineeSupervisorLastName: String?,
        guardPostNihii: String?,
        guardPostSsin: String?,
        guardPostName: String?,
        passPhrase: String,
        patientSsin: String,
        patientFirstName:String,
        patientLastName:String,
        patientGender:String,
        referenceDate: Int?,
        attest: Eattest
                  ): SendAttestResultWithResponse?


    fun cancelAttest(
        keystoreId: UUID,
        tokenId: UUID,
        hcpNihii: String,
        hcpSsin: String,
        hcpFirstName: String,
        hcpLastName: String,
        hcpCbe: String,
        traineeSupervisorSsin: String?,
        traineeSupervisorNihii: String?,
        traineeSupervisorFirstName: String?,
        traineeSupervisorLastName: String?,
        passPhrase: String,
        patientSsin: String,
        patientFirstName: String,
        patientLastName: String,
        patientGender: String,
        referenceDate: Int?,
        eAttestRef: String,
        reason: String
                    ): SendAttestResultWithResponse?
}
