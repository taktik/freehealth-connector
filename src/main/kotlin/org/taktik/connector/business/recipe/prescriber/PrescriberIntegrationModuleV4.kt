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


interface PrescriberIntegrationModuleV4 : PrescriberIntegrationModule {
    @Throws(IntegrationModuleException::class)
    fun createPrescription(feedbackRequested: Boolean, patientId: String, prescription: ByteArray, prescriptionType: String, visibility: String? = null, expirationDate: String? = null): String?

    @Throws(IntegrationModuleException::class)
    fun getData(var1: GetPrescriptionStatusParam?): GetPrescriptionStatusResult?

    @Throws(IntegrationModuleException::class)
    fun getData(var1: ListRidsHistoryParam?): ListRidsHistoryResult?

    @Throws(IntegrationModuleException::class)
    fun putData(var1: PutVisionParam?): PutVisionResult?

    @Throws(IntegrationModuleException::class)
    fun getData(var1: ListOpenRidsParam?): ListOpenRidsResult?

    @Throws(IntegrationModuleException::class)
    fun getData(var1: ValidationPropertiesParam?): ValidationPropertiesResult?
}
