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

package org.taktik.connector.business.recipe.prescriber.services

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckRequest
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckResponse
import be.fgov.ehealth.recipe.protocol.v1.CreatePrescriptionRequest
import be.fgov.ehealth.recipe.protocol.v1.CreatePrescriptionResponse
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPrescriberRequest
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPrescriberResponse
import be.fgov.ehealth.recipe.protocol.v1.ListFeedbacksRequest
import be.fgov.ehealth.recipe.protocol.v1.ListFeedbacksResponse
import be.fgov.ehealth.recipe.protocol.v1.ListOpenPrescriptionsRequest
import be.fgov.ehealth.recipe.protocol.v1.ListOpenPrescriptionsResponse
import be.fgov.ehealth.recipe.protocol.v1.RevokePrescriptionRequest
import be.fgov.ehealth.recipe.protocol.v1.RevokePrescriptionResponse
import be.fgov.ehealth.recipe.protocol.v1.SendNotificationRequest
import be.fgov.ehealth.recipe.protocol.v1.SendNotificationResponse
import be.fgov.ehealth.recipe.protocol.v1.UpdateFeedbackFlagRequest
import be.fgov.ehealth.recipe.protocol.v1.UpdateFeedbackFlagResponse
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.Credential
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface RecipePrescriberService {
    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    fun aliveCheck(
        samlToken: SAMLToken,
        credential: Credential,
        paramAliveCheckRequest: AliveCheckRequest
    ): AliveCheckResponse

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    fun createPrescription(
        samlToken: SAMLToken,
        credential: Credential,
        paramCreatePrescriptionRequest: CreatePrescriptionRequest
    ): CreatePrescriptionResponse

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    fun revokePrescription(
        samlToken: SAMLToken,
        credential: Credential,
        paramRevokePrescriptionRequest: RevokePrescriptionRequest
    ): RevokePrescriptionResponse

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    fun getPrescriptionForPrescriber(
        samlToken: SAMLToken,
        credential: Credential,
        paramGetPrescriptionForPrescriberRequest: GetPrescriptionForPrescriberRequest
    ): GetPrescriptionForPrescriberResponse

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    fun listOpenPrescriptions(
        samlToken: SAMLToken,
        credential: Credential,
        paramListOpenPrescriptionsRequest: ListOpenPrescriptionsRequest
    ): ListOpenPrescriptionsResponse

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    fun sendNotification(
        samlToken: SAMLToken,
        credential: Credential,
        paramSendNotificationRequest: SendNotificationRequest
    ): SendNotificationResponse

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    fun updateFeedbackFlag(
        samlToken: SAMLToken,
        credential: Credential,
        paramUpdateFeedbackFlagRequest: UpdateFeedbackFlagRequest
    ): UpdateFeedbackFlagResponse

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    fun listFeedbacks(
        samlToken: SAMLToken,
        credential: Credential,
        paramListFeedbacksRequest: ListFeedbacksRequest
    ): ListFeedbacksResponse
}
