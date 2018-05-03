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

package org.taktik.connector.business.recipe.prescriber

import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult
import be.recipe.services.prescriber.ListFeedbackItem
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.service.sts.security.Credential
import org.taktik.connector.technical.service.sts.security.SAMLToken

interface PrescriberIntegrationModule {
    @Throws(IntegrationModuleException::class)
    fun createPrescription(
        samlToken: SAMLToken,
        credential: Credential,
        nihii: String,
        feedbackRequested: Boolean,
        patientId: String,
        prescription: ByteArray,
        prescriptionType: String
    ): String?

    @Throws(IntegrationModuleException::class)
    fun revokePrescription(samlToken: SAMLToken, credential: Credential, nihii: String, rid: String, reason: String)

    @Throws(IntegrationModuleException::class)
    fun getPrescription(
        samlToken: SAMLToken,
        credential: Credential,
        nihii: String,
        rid: String
    ): GetPrescriptionForPrescriberResult?

    @Throws(IntegrationModuleException::class)
    fun listOpenPrescription(
        samlToken: SAMLToken,
        credential: Credential,
        nihii: String,
        patientId: String
    ): List<String>

    @Throws(IntegrationModuleException::class)
    fun listOpenPrescription(samlToken: SAMLToken, credential: Credential, nihii: String): List<String>

    @Throws(IntegrationModuleException::class)
    fun sendNotification(
        samlToken: SAMLToken,
        credential: Credential,
        nihii: String,
        notificationText: ByteArray,
        patientId: String,
        executorId: String
    )

    @Throws(IntegrationModuleException::class)
    fun updateFeedbackFlag(
        samlToken: SAMLToken,
        credential: Credential,
        nihii: String,
        rid: String,
        feedbackAllowed: Boolean
    )

    @Throws(IntegrationModuleException::class)
    fun listFeedback(
        samlToken: SAMLToken,
        credential: Credential,
        nihii: String,
        readFlag: Boolean
    ): List<ListFeedbackItem>

    @Throws(IntegrationModuleException::class, TechnicalConnectorException::class)
    fun ping(samlToken: SAMLToken, credential: Credential)

    @Throws(IntegrationModuleException::class)
    fun setPersonalPassword(nihii: String, niss: String, personalPassword: String)

    @Throws(IntegrationModuleException::class)
    fun prepareCreatePrescription(nihii: String, patientId: String, prescriptionType: String)
}
