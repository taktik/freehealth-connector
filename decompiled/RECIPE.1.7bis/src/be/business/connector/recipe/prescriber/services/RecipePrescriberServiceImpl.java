package org.taktik.connector.business.recipe.prescriber.services;

import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceRequest;
import be.ehealth.technicalconnector.session.SessionItem;
import org.apache.log4j.Logger;


import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceCaller;
import org.taktik.connector.business.recipeprojects.common.utils.EndpointResolver;
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckResponse;
import be.fgov.ehealth.recipe.protocol.v1.CreatePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v1.CreatePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPrescriberRequest;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPrescriberResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListFeedbacksRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListFeedbacksResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListOpenPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListOpenPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v1.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v1.RevokePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v1.SendNotificationRequest;
import be.fgov.ehealth.recipe.protocol.v1.SendNotificationResponse;
import be.fgov.ehealth.recipe.protocol.v1.UpdateFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v1.UpdateFeedbackFlagResponse;


public class RecipePrescriberServiceImpl implements RecipePrescriberService {

    /**
     * The Constant LOG.
     */
    private final static Logger LOG = Logger.getLogger(RecipePrescriberServiceImpl.class);

    private static final String ENDPOINT_NAME = "endpoint.prescriber";
    private static final String SERVICE_NAME = RecipePrescriberServiceImpl.class.getName();

    private static RecipePrescriberService recipePrescriberService;

    private RecipePrescriberServiceImpl() {
    }

    /**
     * Gets the singleton instance of RecipePrescriberServiceImpl.
     *
     * @return singleton instance of RecipePrescriberServiceImpl
     */
    public static RecipePrescriberService getInstance() {
        if (recipePrescriberService == null) {
            recipePrescriberService = new RecipePrescriberServiceImpl();
        }
        return recipePrescriberService;
    }

    @Override
    public AliveCheckResponse aliveCheck(AliveCheckRequest aliveCheckRequest) throws IntegrationModuleException {
        return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(aliveCheckRequest), AliveCheckResponse.class);
    }

    @Override
    public CreatePrescriptionResponse createPrescription(CreatePrescriptionRequest createPrescriptionRequest) throws IntegrationModuleException {
        return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(createPrescriptionRequest), CreatePrescriptionResponse.class);
    }

    @Override
    public RevokePrescriptionResponse revokePrescription(RevokePrescriptionRequest revokePrescriptionRequest) throws IntegrationModuleException {
        return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(revokePrescriptionRequest), RevokePrescriptionResponse.class);
    }

    @Override
    public GetPrescriptionForPrescriberResponse getPrescriptionForPrescriber(GetPrescriptionForPrescriberRequest getPrescriptionForPrescriberRequest) throws IntegrationModuleException {
        return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(getPrescriptionForPrescriberRequest), GetPrescriptionForPrescriberResponse.class);
    }

    @Override
    public ListOpenPrescriptionsResponse listOpenPrescriptions(ListOpenPrescriptionsRequest listOpenPrescriptionsRequest) throws IntegrationModuleException {
        return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(listOpenPrescriptionsRequest), ListOpenPrescriptionsResponse.class);
    }

    @Override
    public SendNotificationResponse sendNotification(SendNotificationRequest sendNotificationRequest) throws IntegrationModuleException {
        return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(sendNotificationRequest), SendNotificationResponse.class);
    }

    @Override
    public UpdateFeedbackFlagResponse updateFeedbackFlag(UpdateFeedbackFlagRequest updateFeedbackFlagRequest) throws IntegrationModuleException {
        return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(updateFeedbackFlagRequest), UpdateFeedbackFlagResponse.class);
    }

    @Override
    public ListFeedbacksResponse listFeedbacks(ListFeedbacksRequest listFeedbacksRequest) throws IntegrationModuleException {
        return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(listFeedbacksRequest), ListFeedbacksResponse.class);
    }

    private GenericWebserviceRequest createDefaultGenericWebserviceRequest(final Object requestObject) throws IntegrationModuleException {
        final GenericWebserviceRequest request = new GenericWebserviceRequest();
        request.setRequest(requestObject);
        request.setEndpoint(EndpointResolver.getEndpointUrlString(ENDPOINT_NAME));
        request.setServiceName(SERVICE_NAME);
        request.setAddLoggingHandler(true);
        request.setAddSoapFaultHandler(true);
        request.setAddMustUnderstandHandler(true);
        request.setAddInsurabilityHandler(false);
        return request;
    }
}
