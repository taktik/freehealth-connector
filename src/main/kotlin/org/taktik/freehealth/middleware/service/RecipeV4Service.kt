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

import org.taktik.connector.technical.exception.ConnectorException
import org.taktik.freehealth.middleware.domain.recipe.Medication
import org.taktik.freehealth.middleware.domain.common.Patient
import org.taktik.freehealth.middleware.domain.recipe.Feedback
import org.taktik.freehealth.middleware.domain.recipe.Prescription
import org.taktik.freehealth.middleware.dto.HealthcareParty
import java.security.KeyStoreException
import java.security.cert.CertificateExpiredException
import java.time.LocalDateTime
import java.util.UUID
import java.util.zip.DataFormatException

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 16/06/13
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
interface RecipeV4Service {
    @Throws(ConnectorException::class)
    fun createPrescriptionV4(
        keystoreId: UUID,
        tokenId: UUID,
        hcpQuality: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpName: String,
        passPhrase: String,
        patient: Patient,
        hcp: HealthcareParty,
        feedback: Boolean,
        medications: List<Medication>,
        prescriptionType: String?,
        notification: String?,
        executorId: String?,
        samVersion: String?,
        deliveryDate: LocalDateTime?,
        vendorName: String?,
        packageName: String?,
        packageVersion: String?,
        vendorEmail: String?,
        vendorPhone: String?,
        vision: String?,
        expirationDate: LocalDateTime?
                            ): Prescription

    @Throws(ConnectorException::class)
    fun listOpenPrescriptionV4(
        keystoreId: UUID,
        tokenId: UUID,
        hcpQuality: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpName: String,
        passPhrase: String,
        patientId: String
    ): List<Prescription>

    @Throws(ConnectorException::class, DataFormatException::class, KeyStoreException::class, CertificateExpiredException::class)
    fun listFeedbacksV4(
        keystoreId: UUID,
        tokenId: UUID,
        hcpQuality: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpName: String,
        passPhrase: String
    ): List<Feedback>

    @Throws(ConnectorException::class, KeyStoreException::class, CertificateExpiredException::class)
    fun revokePrescriptionV4(
        keystoreId: UUID,
        tokenId: UUID,
        hcpQuality: String,
        hcpNihii: String,
        hcpSsin: String,
        hcpName: String,
        passPhrase: String,
        rid: String,
        reason: String
    )
}
