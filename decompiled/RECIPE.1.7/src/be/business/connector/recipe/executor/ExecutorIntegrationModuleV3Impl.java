package org.taktik.connector.business.recipe.executor;

import be.business.connector.common.ApplicationConfig;
import org.taktik.connector.business.recipeprojects.core.domain.IdentifierTypes;
import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.handlers.InsurabilityHandler;
import org.taktik.connector.business.recipeprojects.core.utils.Exceptionutils;
import org.taktik.connector.business.recipeprojects.core.utils.I18nHelper;
import org.taktik.connector.business.recipe.executor.domain.GetPrescriptionForExecutorResult;
import org.taktik.connector.business.recipe.executor.services.RecipeExecutorServiceV3Impl;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v3.ExecutorServiceAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import be.fgov.ehealth.recipe.protocol.v3.*;
import be.recipe.services.executor.ListNotificationsItem;
import com.sun.xml.ws.client.ClientTransportException;
import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;

import java.util.List;

public class ExecutorIntegrationModuleV3Impl extends AbstractExecutorIntegrationModule implements ExecutorIntegrationModule {

    private final static Logger LOG = Logger.getLogger(ExecutorIntegrationModuleV3Impl.class);

    public ExecutorIntegrationModuleV3Impl() throws IntegrationModuleException {
        super();
        LOG.info("*************** Executor V3 System module init correctly *******************");
    }

    @Profiled(logFailuresSeparately = true, tag = "ExecutorIntegrationModule#getPrescription")
    @Override
    public GetPrescriptionForExecutorResult getPrescription(String rid) throws IntegrationModuleException {
        validateRid(rid);
        ApplicationConfig.getInstance().assertValidSession();

        InsurabilityHandler.setInsurability(null);
        InsurabilityHandler.setMessageId(null);

        try {
            // create get prescription request
            final byte[] sealedGetPrescriptionForExecutorParam = getSealedGetPrescriptionForExecutorParam(rid);

            GetPrescriptionForExecutorRequest request = new GetPrescriptionForExecutorRequest();
            request.setDisablePatientInsurabilityCheckParam(Boolean.parseBoolean(getPropertyHandler().getProperty("patient.insurability.disable"))); // will be false if not defined in property file
            request.setSecuredGetPrescriptionForExecutorRequest(createSecuredContentType(sealedGetPrescriptionForExecutorParam));

            GetPrescriptionForExecutorResponse response = null;
            try {
                response = RecipeExecutorServiceV3Impl.getInstance().getPrescriptionForExecutor(request);
            } catch (ClientTransportException cte) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
            }
            checkStatus(response);

            final byte[] securedContent = response.getSecuredGetPrescriptionForExecutorResponse().getSecuredContent();
            org.taktik.connector.business.recipe.executor.domain.GetPrescriptionForExecutorResult finalResult = createGetPrescriptionForExecutorResult(securedContent);
            getPrescriptionCache().put(rid, finalResult);
            return finalResult;
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
        return null;
    }

    @Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModule#markAsArchived")
    @Override
    public void markAsArchived(String rid) throws IntegrationModuleException {
        validateRid(rid);
        ApplicationConfig.getInstance().assertValidSession();
        try {
            // create param
            byte[] sealedMarkAsArchivedParam = getSealedMarkAsArchivedParam(rid);

            // create request
            MarkAsArchivedRequest request = new MarkAsArchivedRequest();
            request.setSecuredMarkAsArchivedRequest(createSecuredContentType(sealedMarkAsArchivedParam));
            request.setAdministrativeInformation(getAdministrativeInfo(rid));

            // call WS
            try {
                checkStatus(RecipeExecutorServiceV3Impl.getInstance().markAsArchived(request));
            } catch (ClientTransportException cte) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
            }
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
    }

    @Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModule#markAsDelivered")
    @Override
    public void markAsDelivered(String rid) throws IntegrationModuleException {
        validateRid(rid);
        ApplicationConfig.getInstance().assertValidSession();
        try {
            byte[] sealedMarkAsDeliveredParam = getSealedMarkAsDeliveredParam(rid);

            MarkAsDeliveredRequest request = new MarkAsDeliveredRequest();
            request.setSecuredMarkAsDeliveredRequest(createSecuredContentType(sealedMarkAsDeliveredParam));
            request.setAdministrativeInformation(getAdministrativeInfo(rid));

            try {
                checkStatus(RecipeExecutorServiceV3Impl.getInstance().markAsDelivered(request));
            } catch (ClientTransportException cte) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
            }
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
    }

    @Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModule#markAsUndelivered")
    @Override
    public void markAsUndelivered(String rid) throws IntegrationModuleException {
        validateRid(rid);
        ApplicationConfig.getInstance().assertValidSession();
        try {
            final byte[] sealedMarkAsUndeliveredParam = getSealedMarkAsUndeliveredParam(rid);

            MarkAsUnDeliveredRequest request = new MarkAsUnDeliveredRequest();
            request.setSecuredMarkAsUnDeliveredRequest(createSecuredContentType(sealedMarkAsUndeliveredParam));
            request.setAdministrativeInformation(getAdministrativeInfo(rid));

            try {
                checkStatus(RecipeExecutorServiceV3Impl.getInstance().markAsUnDelivered(request));
            } catch (ClientTransportException cte) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
            }
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
    }

    @Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModule#revokePrescription")
    @Override
    public void revokePrescription(String rid, String reason) throws IntegrationModuleException {
        validateRid(rid);
        ApplicationConfig.getInstance().assertValidSession();
        try {
            final byte[] sealedRevokePrescriptionParam = getSealedRevokePrescriptionParam(rid, reason);

            RevokePrescriptionForExecutorRequest request = new RevokePrescriptionForExecutorRequest();
            request.setAdministrativeInformation(getAdministrativeInfo(rid));
            request.setSecuredRevokePrescriptionRequest(createSecuredContentType(sealedRevokePrescriptionParam));

            try {
                checkStatus(RecipeExecutorServiceV3Impl.getInstance().revokePrescriptionForExecutor(request));
            } catch (ClientTransportException cte) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
            }

        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
    }


    @Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModule#listNotifications")
    @Override
    public List<ListNotificationsItem> listNotifications(boolean readFlag) throws IntegrationModuleException {
        ApplicationConfig.getInstance().assertValidSession();

        try {
            final byte[] sealedListNotificationsParam = getSealedListNotificationsParam(readFlag);

            ListNotificationsRequest request = new ListNotificationsRequest();
            request.setSecuredListNotificationsRequest(createSecuredContentType(sealedListNotificationsParam));

            ListNotificationsResponse response = null;
            try {
                response = RecipeExecutorServiceV3Impl.getInstance().listNotifications(request);
            } catch (ClientTransportException cte) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
            }
            checkStatus(response);

            final byte[] securedContent = response.getSecuredListNotificationsResponse().getSecuredContent();
            return createListNotificationItems(securedContent);
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
        return null;
    }

    @Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModule#createFeedback")
    @Override
    public void createFeedback(String prescriberId, String rid, byte[] feedbackText) throws IntegrationModuleException {
        validateRid(rid);
        ApplicationConfig.getInstance().assertValidSession();
        try {
            getKmehrHelper().assertValidFeedback(feedbackText);
            List<EncryptionToken> etkRecipients = getEtkHelper().getEtks(KgssIdentifierType.NIHII, prescriberId);

            for (int i = 0; i < etkRecipients.size(); i++) {
                EncryptionToken etkRecipient = etkRecipients.get(i);
                final byte[] sealedCreateFeedbackParam = getSealedCreateFeedbackParam(feedbackText, etkRecipient, rid, prescriberId);

                CreateFeedbackRequest request = new CreateFeedbackRequest();
                request.setSecuredCreateFeedbackRequest(createSecuredContentType(sealedCreateFeedbackParam));
                ExecutorServiceAdministrativeInformationType info = getAdministrativeInfo(rid);
                info.setPrescriberIdentifier(createIdentifierType(prescriberId, IdentifierTypes.SSIN.name()));
                request.setAdministrativeInformation(info);

                try {
                    checkStatus(RecipeExecutorServiceV3Impl.getInstance().createFeedback(request));
                } catch (ClientTransportException cte) {
                    throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
                }
            }
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
    }

    private SecuredContentType createSecuredContentType(byte[] content) {
        SecuredContentType secured = new SecuredContentType();
        secured.setSecuredContent(content);
        return secured;
    }


    private ExecutorServiceAdministrativeInformationType getAdministrativeInfo(String rid) {
        GetPrescriptionForExecutorResult prescription = getPrescriptionCache().get(rid);
        ExecutorServiceAdministrativeInformationType info = new ExecutorServiceAdministrativeInformationType();
        if (prescription != null) {
            info.setPatientIdentifier(createIdentifierType(prescription.getPatientId(), IdentifierTypes.SSIN.name()));
            info.setPrescriberIdentifier(createIdentifierType(prescription.getPrescriberId(), IdentifierTypes.NIHII11.name()));
        } else {
            info.setPatientIdentifier(createIdentifierType("72081061175", IdentifierTypes.SSIN.name()));
            info.setPrescriberIdentifier(createIdentifierType("10998018001", IdentifierTypes.NIHII11.name()));
        }
        return info;
    }
}
