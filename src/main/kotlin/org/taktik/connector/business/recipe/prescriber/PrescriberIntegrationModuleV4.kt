package org.taktik.connector.business.recipe.prescriber

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


interface PrescriberIntegrationModuleV4 : PrescriberIntegrationModule {
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
    fun getData(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: GetPrescriptionStatusParam): GetPrescriptionStatusResult?

    @Throws(IntegrationModuleException::class)
    fun getData(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: ListRidsHistoryParam): ListRidsHistoryResult?

    @Throws(IntegrationModuleException::class)
    fun putData(        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: PutVisionParam): PutVisionResult?

    @Throws(IntegrationModuleException::class)
    fun getData(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: ListOpenRidsParam): ListOpenRidsResult?

    @Throws(IntegrationModuleException::class)
    fun getData(
        keystore: KeyStore,
        samlToken: SAMLToken,
        passPhrase: String,
        credential: KeyStoreCredential,
        param: ValidationPropertiesParam): ValidationPropertiesResult?
}
