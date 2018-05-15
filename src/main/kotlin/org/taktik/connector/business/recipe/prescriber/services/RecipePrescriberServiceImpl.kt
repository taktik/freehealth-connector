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
import org.taktik.connector.business.recipeprojects.common.utils.EndpointResolver
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceCaller
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceRequest
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.Credential
import org.taktik.connector.technical.service.sts.security.SAMLToken

class RecipePrescriberServiceImpl : RecipePrescriberService {

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    override fun aliveCheck(
        samlToken: SAMLToken,
        credential: Credential,
        paramAliveCheckRequest: AliveCheckRequest
    ): AliveCheckResponse {
        return GenericWebserviceCaller.callGenericWebservice(
            samlToken,
            credential,
            createDefaultGenericWebserviceRequest(
                paramAliveCheckRequest
            ),
            AliveCheckResponse::class.java
        )
    }

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    override fun createPrescription(
        samlToken: SAMLToken,
        credential: Credential,
        paramCreatePrescriptionRequest: CreatePrescriptionRequest
    ): CreatePrescriptionResponse {
        return GenericWebserviceCaller.callGenericWebservice(
            samlToken,
            credential,
            createDefaultGenericWebserviceRequest(
                paramCreatePrescriptionRequest
            ),
            CreatePrescriptionResponse::class.java
        )
    }

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    override fun revokePrescription(
        samlToken: SAMLToken,
        credential: Credential,
        paramRevokePrescriptionRequest: RevokePrescriptionRequest
    ): RevokePrescriptionResponse {
        return GenericWebserviceCaller.callGenericWebservice(
            samlToken,
            credential,
            createDefaultGenericWebserviceRequest(
                paramRevokePrescriptionRequest
            ),
            RevokePrescriptionResponse::class.java
        )
    }

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    override fun getPrescriptionForPrescriber(
        samlToken: SAMLToken,
        credential: Credential,
        paramGetPrescriptionForPrescriberRequest: GetPrescriptionForPrescriberRequest
    ): GetPrescriptionForPrescriberResponse {
        return GenericWebserviceCaller.callGenericWebservice(
            samlToken,
            credential,
            createDefaultGenericWebserviceRequest(
                paramGetPrescriptionForPrescriberRequest
            ),
            GetPrescriptionForPrescriberResponse::class.java
        )
    }

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    override fun listOpenPrescriptions(
        samlToken: SAMLToken,
        credential: Credential,
        paramListOpenPrescriptionsRequest: ListOpenPrescriptionsRequest
    ): ListOpenPrescriptionsResponse {
        return GenericWebserviceCaller.callGenericWebservice(
            samlToken,
            credential,
            createDefaultGenericWebserviceRequest(
                paramListOpenPrescriptionsRequest
            ),
            ListOpenPrescriptionsResponse::class.java
        )
    }

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    override fun sendNotification(
        samlToken: SAMLToken,
        credential: Credential,
        paramSendNotificationRequest: SendNotificationRequest
    ): SendNotificationResponse {
        return GenericWebserviceCaller.callGenericWebservice(
            samlToken,
            credential,
            createDefaultGenericWebserviceRequest(
                paramSendNotificationRequest
            ),
            SendNotificationResponse::class.java
        )
    }

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    override fun updateFeedbackFlag(
        samlToken: SAMLToken,
        credential: Credential,
        paramUpdateFeedbackFlagRequest: UpdateFeedbackFlagRequest
    ): UpdateFeedbackFlagResponse {
        return GenericWebserviceCaller.callGenericWebservice(
            samlToken,
            credential,
            createDefaultGenericWebserviceRequest(
                paramUpdateFeedbackFlagRequest
            ),
            UpdateFeedbackFlagResponse::class.java
        )
    }

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    override fun listFeedbacks(
        samlToken: SAMLToken,
        credential: Credential,
        paramListFeedbacksRequest: ListFeedbacksRequest
    ): ListFeedbacksResponse {
        return GenericWebserviceCaller.callGenericWebservice(
            samlToken,
            credential,
            createDefaultGenericWebserviceRequest(
                paramListFeedbacksRequest
            ),
            ListFeedbacksResponse::class.java
        )
    }

    @Throws(IntegrationModuleException::class)
    private fun createDefaultGenericWebserviceRequest(requestObject: Any): GenericWebserviceRequest {
        val request = GenericWebserviceRequest()
        request.request = requestObject
        request.endpoint = EndpointResolver.getEndpointUrlString(ENDPOINT_NAME)
        request.serviceName = SERVICE_NAME
        request.isAddLoggingHandler = true
        request.isAddSoapFaultHandler = true
        request.isAddMustUnderstandHandler = true
        request.isAddInsurabilityHandler = false
        return request
    }

    companion object {
        private val ENDPOINT_NAME = "endpoint.prescriber"
        private val SERVICE_NAME = RecipePrescriberServiceImpl::class.java.name
    }
}
