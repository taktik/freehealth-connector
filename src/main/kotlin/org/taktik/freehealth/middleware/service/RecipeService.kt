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
import org.taktik.connector.technical.service.sts.security.Credential
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.freehealth.middleware.dto.Code
import org.taktik.freehealth.middleware.domain.Feedback
import org.taktik.freehealth.middleware.dto.HealthcareParty
import org.taktik.freehealth.middleware.domain.Medication
import org.taktik.freehealth.middleware.domain.Patient
import org.taktik.freehealth.middleware.domain.Prescription
import org.taktik.freehealth.middleware.domain.PrescriptionFullWithFeedback
import java.security.KeyStoreException
import java.security.cert.CertificateExpiredException
import java.util.*
import java.util.zip.DataFormatException
import javax.xml.bind.JAXBException

/**
 * Created with IntelliJ IDEA.
 * User: aduchate
 * Date: 16/06/13
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
interface RecipeService {
    @Throws(ConnectorException::class)
    fun createPrescription(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String, patient: Patient, hcp: HealthcareParty, feedback: Boolean, medications: List<Medication>, prescriptionType: String?, notification: String?, executorId: String?, deliveryDate: Date?): Prescription

    @Throws(ConnectorException::class, KeyStoreException::class, CertificateExpiredException::class)
    fun listOpenPrescriptions(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String): List<Prescription>

    @Throws(ConnectorException::class, DataFormatException::class, KeyStoreException::class, CertificateExpiredException::class)
    fun sendNotification(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String,  patientId: String, executorId: String, rid: String, text: String)

    @Throws(ConnectorException::class, KeyStoreException::class, CertificateExpiredException::class)
    fun revokePrescription(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String, rid: String, reason: String)

    @Throws(ConnectorException::class, KeyStoreException::class, CertificateExpiredException::class)
    fun updateFeedbackFlag(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String, rid: String, feedbackFlag: Boolean)

    @Throws(ConnectorException::class, DataFormatException::class, KeyStoreException::class, CertificateExpiredException::class)
    fun listFeedbacks(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String): List<Feedback>

    @Throws(ConnectorException::class, KeyStoreException::class, CertificateExpiredException::class)
    fun listOpenPrescriptions(keystoreId: UUID, tokenId: UUID, hcpQuality: String, hcpNihii: String, hcpSsin: String, hcpName: String, passPhrase: String,  patientId: String): List<Prescription>

	fun getGalToAdministrationUnit(galId: String): Code?

    @Throws(JAXBException::class)
    fun getPrescription(rid: String): PrescriptionFullWithFeedback?
}
