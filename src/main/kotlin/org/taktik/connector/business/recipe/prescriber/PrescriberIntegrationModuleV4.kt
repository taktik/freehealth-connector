package org.taktik.connector.business.recipe.prescriber

import be.fgov.ehealth.recipe.protocol.v1.AliveCheckResponse
import be.recipe.services.prescriber.GetPrescriptionStatusParam
import be.recipe.services.prescriber.GetPrescriptionStatusResult
import be.recipe.services.prescriber.ListOpenRidsParam
import be.recipe.services.prescriber.ListOpenRidsResult
import be.recipe.services.prescriber.ListRidsHistoryParam
import be.recipe.services.prescriber.ListRidsHistoryResult
import be.recipe.services.prescriber.PutVisionParam
import be.recipe.services.prescriber.PutVisionResult
import be.recipe.services.prescriber.ValidationPropertiesParam
import be.recipe.services.prescriber.ValidationPropertiesResult
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.technical.service.sts.security.SAMLToken
import org.taktik.connector.technical.service.sts.security.impl.KeyStoreCredential
import java.security.KeyStore
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


interface PrescriberIntegrationModuleV4 {
    @Throws(IntegrationModuleException::class)
    fun createPrescription(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        nihii: String,
        feedbackRequested: Boolean,
        patientId: String,
        prescription: ByteArray,
        prescriptionType: String,
        visibility: String? = null,
        vendorName: String?,
        packageName: String?,
        packageVersion: String?,
        expirationDate: LocalDateTime = LocalDateTime.now().plusMonths(3)
                          ): String?

    @Throws(IntegrationModuleException::class)
    fun getPrescriptionStatus(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        nihii: String,
        rid: String

                             ): GetPrescriptionStatusResult?

    @Throws(IntegrationModuleException::class)
    fun listRidsHistory(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        param: ListRidsHistoryParam): ListRidsHistoryResult?

    @Throws(IntegrationModuleException::class)
    fun setVision(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: PutVisionParam): PutVisionResult?

    @Throws(IntegrationModuleException::class)
    fun listOpenRids(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: ListOpenRidsParam): ListOpenRidsResult?

    @Throws(IntegrationModuleException::class)
    fun getValidationProperties(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: ValidationPropertiesParam): ValidationPropertiesResult?

    @Throws(exceptionClasses = { IntegrationModuleException::class, org.taktik.connector.technical.exception.TechnicalConnectorException::class })
    fun ping(samlToken: SAMLToken, credential: KeyStoreCredential): AliveCheckResponse
    @Throws(exceptionClasses = { IntegrationModuleException::class })
    fun revokePrescription(
        samlToken: SAMLToken,
        credential: KeyStoreCredential,
        nihii: String,
        rid: String,
        reason: String
    )
}
