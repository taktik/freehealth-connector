package org.taktik.connector.business.recipe.prescriber.services

import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionRequest
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionResponse
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionRequest
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionResponse
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesRequest
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesResponse
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksRequest
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksResponse
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsRequest
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsResponse
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagRequest
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagResponse
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberRequest
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberResponse
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionResponse
import be.fgov.ehealth.recipe.protocol.v4.SendNotificationRequest
import be.fgov.ehealth.recipe.protocol.v4.SendNotificationResponse
import org.taktik.connector.business.recipeprojects.common.utils.EndpointResolver
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceCaller
import org.taktik.connector.technical.service.sts.security.Credential
import org.taktik.connector.technical.service.sts.security.SAMLToken


class RecipePrescriberServiceV4Impl : RecipePrescriberServiceV4 {
    private val ENDPOINT_NAME = "endpoint.recipe.prescriber.v4"

    @Throws(IntegrationModuleException::class)
    fun getPrescriptionForPrescriber(
        samlToken: SAMLToken,
        credential: Credential,
        request: GetPrescriptionRequest): GetPrescriptionResponse? {
        return GenericWebserviceCaller.callGenericWebservice(samlToken,
                                                             credential,
                                                             request, GetPrescriptionResponse::class.java, EndpointResolver.getEndpointUrlString("endpoint.recipe.prescriber.v4"), this.javaClass.getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:getPrescription\"")
    }

    @Throws(IntegrationModuleException::class)
    fun putFeedbackFlag(
        samlToken: SAMLToken,
        credential: Credential,
        request: PutFeedbackFlagRequest): PutFeedbackFlagResponse? {
        return GenericWebserviceCaller.callGenericWebservice(samlToken,
                                                             credential,
                                                             request, PutFeedbackFlagResponse::class.java, EndpointResolver.getEndpointUrlString("endpoint.recipe.prescriber.v4"), this.javaClass.getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:putFeedbackFlag\"")
    }

    @Throws(IntegrationModuleException::class)
    fun listFeedbacks(
        samlToken: SAMLToken,
        credential: Credential,
        request: ListFeedbacksRequest): ListFeedbacksResponse? {
        return GenericWebserviceCaller.callGenericWebservice(samlToken,
                                                             credential,
                                                             request, ListFeedbacksResponse::class.java, EndpointResolver.getEndpointUrlString("endpoint.recipe.prescriber.v4"), this.javaClass.getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listFeedbacks\"")
    }

    @Throws(IntegrationModuleException::class)
    fun revokePrescription(
        samlToken: SAMLToken,
        credential: Credential,
        request: RevokePrescriptionRequest): RevokePrescriptionResponse? {
        return GenericWebserviceCaller.callGenericWebservice(samlToken,
                                                             credential,
                                                             request, RevokePrescriptionResponse::class.java, EndpointResolver.getEndpointUrlString("endpoint.recipe.prescriber.v4"), this.javaClass.getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:revokePrescription\"")
    }

    @Throws(IntegrationModuleException::class)
    fun sendNotification(
        samlToken: SAMLToken,
        credential: Credential,
        request: SendNotificationRequest): SendNotificationResponse? {
        return GenericWebserviceCaller.callGenericWebservice(samlToken,
                                                             credential,
                                                             request, SendNotificationResponse::class.java, EndpointResolver.getEndpointUrlString("endpoint.recipe.prescriber.v4"), this.javaClass.getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:sendNotification\"")
    }

    @Throws(IntegrationModuleException::class)
    fun createPrescription(
        samlToken: SAMLToken,
        credential: Credential,
        request: CreatePrescriptionRequest): CreatePrescriptionResponse? {
        return GenericWebserviceCaller.callGenericWebservice(samlToken,
                                                             credential,
                                                             request, CreatePrescriptionResponse::class.java, EndpointResolver.getEndpointUrlString("endpoint.recipe.prescriber.v4"), this.javaClass.getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:createPrescription\"")
    }

    @Throws(IntegrationModuleException::class)
    fun getValidationProperties(
        samlToken: SAMLToken,
        credential: Credential,
        request: GetValidationPropertiesRequest): GetValidationPropertiesResponse? {
        return GenericWebserviceCaller.callGenericWebservice(samlToken,
                                                             credential,
                                                             request, GetValidationPropertiesResponse::class.java, EndpointResolver.getEndpointUrlString("endpoint.recipe.prescriber.v4"), this.javaClass.getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:getValidationProperties\"")
    }

    @Throws(IntegrationModuleException::class)
    fun getPrescriptionStatus(
        samlToken: SAMLToken,
        credential: Credential,
        request: GetPrescriptionStatusRequest): GetPrescriptionStatusResponse? {
        return GenericWebserviceCaller.callGenericWebservice(samlToken,
                                                             credential,
                                                             request, GetPrescriptionStatusResponse::class.java, EndpointResolver.getEndpointUrlString("endpoint.recipe.prescriber.v4"), this.javaClass.getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:getPrescriptionStatus\"")
    }

    @Throws(IntegrationModuleException::class)
    fun listOpenRids(
        samlToken: SAMLToken,
        credential: Credential,
        request: ListOpenRidsRequest): ListOpenRidsResponse? {
        return GenericWebserviceCaller.callGenericWebservice(samlToken,
                                                             credential,
                                                             request, ListOpenRidsResponse::class.java, EndpointResolver.getEndpointUrlString("endpoint.recipe.prescriber.v4"), this.javaClass.getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:ListOpenRids\"")
    }

    @Throws(IntegrationModuleException::class)
    fun putVisionForPrescriber(
        samlToken: SAMLToken,
        credential: Credential,
        request: PutVisionForPrescriberRequest): PutVisionForPrescriberResponse? {
        return GenericWebserviceCaller.callGenericWebservice(samlToken,
                                                             credential,
                                                             request, PutVisionForPrescriberResponse::class.java, EndpointResolver.getEndpointUrlString("endpoint.recipe.prescriber.v4"), this.javaClass.getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:putVisionForPrescriber\"")
    }

    @Throws(IntegrationModuleException::class)
    fun listRidsHistory(
        samlToken: SAMLToken,
        credential: Credential,
        request: ListRidsHistoryRequest): ListRidsHistoryResponse? {
        return GenericWebserviceCaller.callGenericWebservice(samlToken,
            credential,
            request, ListRidsHistoryResponse::class.java, EndpointResolver.getEndpointUrlString("endpoint.recipe.prescriber.v4"), this.javaClass.getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listRidsHistory\"") as ListRidsHistoryResponse?
    }
}
