package org.taktik.connector.business.recipe.prescriber;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.StandaloneRequestorProvider;
import org.taktik.connector.business.recipeprojects.core.domain.IdentifierTypes;
import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType;
import org.taktik.connector.business.recipeprojects.core.ehealth.services.KgssService;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.business.connector.common.module.AbstractIntegrationModule;
import org.taktik.connector.business.recipeprojects.core.technical.connector.utils.Crypto;
import org.taktik.connector.business.recipeprojects.core.utils.*;
import org.taktik.connector.business.recipeprojects.common.utils.ValidationUtils;
import org.taktik.connector.business.recipe.prescriber.services.RecipePrescriberServiceImpl;
import org.taktik.connector.business.recipe.utils.KmehrHelper;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.fgov.ehealth.commons.core.v1.IdentifierType;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.core.v1.StatusType;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v1.CreatePrescriptionAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v1.PrescriberServiceAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import be.fgov.ehealth.recipe.core.v1.SendNotificationAdministrativeInformationType;
import be.fgov.ehealth.recipe.protocol.v1.*;
import be.recipe.services.prescriber.*;
import com.sun.xml.ws.client.ClientTransportException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.perf4j.aop.Profiled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrescriberIntegrationModuleImpl extends AbstractIntegrationModule implements PrescriberIntegrationModule {

    private final static Logger LOG = Logger.getLogger(PrescriberIntegrationModuleImpl.class);

    private Map<String, KeyResult> keyCache = new HashMap<>();
    /**
     * The prescription cache. Key is the RID, Value is the Patient ID.
     */
    private Map<String, String> prescriptionCache = new HashMap<>();
    private KgssService kgssService = org.taktik.connector.business.recipeprojects.core.ehealth.services.KgssServiceImpl.getInstance();
    private KmehrHelper kmehrHelper;


    public PrescriberIntegrationModuleImpl() throws IntegrationModuleException {
        super();
        kmehrHelper = new KmehrHelper(PropertyHandler.getInstance().getProperties());
        LOG.info("*************** Prescriber System module init correctly *******************");
    }


    /**
     * Prepare create prescription.
     *
     * @param patientId the patient id
     * @throws IntegrationModuleException
     */
    @Override
    @Profiled(logFailuresSeparately = true, tag = "PrescriberIntegrationModule#prepareCreatePrescription")
    public void prepareCreatePrescription(String patientId, String prescriptionType) throws IntegrationModuleException {
        ApplicationConfig.getInstance().assertValidSession();

        String cacheId = "(" + patientId + "#" + prescriptionType + ")";
        keyCache.put(cacheId, getNewKeyFromKgss(prescriptionType, StandaloneRequestorProvider.getRequestorIdInformation(), null, patientId, getEtkHelper().getSystemETK().get(0).getEncoded()));
    }

    /**
     * Gets the new key.
     *
     * @param patientId        the patient id
     * @param prescriptionType the prescription type
     * @return the new key
     * @throws IntegrationModuleException the integration module exception
     */
    private KeyResult getNewKey(String patientId, String prescriptionType) throws IntegrationModuleException {
        KeyResult key = null;

        String cacheId = "(" + patientId + "#" + prescriptionType + ")";
        if (keyCache.containsKey(cacheId)) {
            key = keyCache.get(cacheId);
            keyCache.remove(cacheId);
        } else {
            key = getNewKeyFromKgss(prescriptionType, StandaloneRequestorProvider.getRequestorIdInformation(), null, patientId, getEtkHelper().getSystemETK().get(0).getEncoded());
        }
        return key;
    }

    @Profiled(logFailuresSeparately = true, tag = "PrescriberIntegrationModule#ping")
    public void ping() throws IntegrationModuleException {

        AliveCheckResponse response = null;
        try {
            response = RecipePrescriberServiceImpl.getInstance().aliveCheck(new AliveCheckRequest());
        } catch (ClientTransportException cte) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
        }
        LOG.info("Ping response : " + response.getAliveCheckResult());
        checkStatus(response);

    }

    /**
     * Creates the prescription.
     *
     * @param feedbackRequested the feedback requested
     * @param patientId         the patient id
     * @param prescription      the prescription
     * @param prescriptionType  the prescription type
     * @return the string
     * @throws IntegrationModuleException the integration module exception
     */
    @Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#createPrescription")
    @Override
    public String createPrescription(boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType) throws IntegrationModuleException {

        ApplicationConfig.getInstance().assertValidSession();

        if (StringUtils.isBlank(patientId)) {
            throw new IntegrationModuleException("Patient ID is 0.");
        }

        String pid = patientId;
        ValidationUtils.validatePatientId(pid);
        try {

            getKmehrHelper().assertValidKmehrPrescription(prescription, prescriptionType);

            // init helper
            MarshallerHelper<CreatePrescriptionResult, CreatePrescriptionParam> helper = new MarshallerHelper<>(CreatePrescriptionResult.class, CreatePrescriptionParam.class);

            // get recipe etk
            final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

            // create sealed prescription
            byte[] message = IOUtils.compress(prescription);

            KeyResult key = getNewKey(pid, prescriptionType);
            message = sealPrescriptionForUnknown(key, message);

            final String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();

            // create sealed content
            CreatePrescriptionParam params = new CreatePrescriptionParam();
            params.setPatientId(pid);
            params.setFeedbackRequested(feedbackRequested);
            params.setPrescription(message);
            params.setPrescriptionType(prescriptionType);
            params.setSymmKey(getSymmKey().getEncoded());
            params.setKeyId(key.getKeyId());
            params.setPrescriberId(requestorIdInformation);

            // create request
            CreatePrescriptionRequest request = new CreatePrescriptionRequest();
            request.setSecuredCreatePrescriptionRequest(createSecuredContentType(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(params))));

            // create administrative info
            CreatePrescriptionAdministrativeInformationType info = new CreatePrescriptionAdministrativeInformationType();
            request.setAdministrativeInformation(info);
            info.setKeyIdentifier(Base64.decode(key.getKeyId()));
            // info.setKeyIdentifier(key.getKeyId().getBytes());
            info.setPrescriptionType(prescriptionType);
            info.setPatientIdentifier(createIdentifierType(pid, IdentifierTypes.SSIN.name()));
            info.setPrescriberIdentifier(createIdentifierType(requestorIdInformation, IdentifierTypes.NIHII11.name()));

            // WS call
            CreatePrescriptionResponse response = RecipePrescriberServiceImpl.getInstance().createPrescription(request);
            checkStatus(response);

            // unseal response
            CreatePrescriptionResult result = helper.unsealWithSymmKey(response.getSecuredCreatePrescriptionResponse().getSecuredContent(), getSymmKey());

            // update the local cache
            setPatientId(result.getRid(), pid);

            return result.getRid();
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }

        return null;
    }

    /**
     * Cancel prescription.
     *
     * @param rid    the rid
     * @param reason the reason
     * @throws IntegrationModuleException the integration module exception
     */
    @Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#revokePrescription")
    @Override
    public void revokePrescription(String rid, String reason) throws IntegrationModuleException {
        validateRid(rid);
        ApplicationConfig.getInstance().assertValidSession();

        try {
            // init helper
            MarshallerHelper<Object, RevokePrescriptionParam> helper = new MarshallerHelper<Object, RevokePrescriptionParam>(Object.class, RevokePrescriptionParam.class);

            // get Recipe ETK
            final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

            // create params
            RevokePrescriptionParam params = new RevokePrescriptionParam();
            params.setRid(rid);
            params.setReason(reason);
            params.setPrescriberId(StandaloneRequestorProvider.getRequestorIdInformation());

            // create request
            RevokePrescriptionRequest request = new RevokePrescriptionRequest();
            request.setSecuredRevokePrescriptionRequest(createSecuredContentType(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(params))));

            // Admin Info for eHealth
            PrescriberServiceAdministrativeInformationType info = new PrescriberServiceAdministrativeInformationType();
            info.setPatientIdentifier(createIdentifierType(getPatientId(rid), IdentifierTypes.SSIN.name()));

            request.setAdministrativeInformation(info);

            // call WS
            try {
                checkStatus(RecipePrescriberServiceImpl.getInstance().revokePrescription(request));
            } catch (ClientTransportException cte) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
            }

        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }

    }

    /**
     * Gets the prescription.
     *
     * @param rid the rid
     * @return the prescription
     * @throws IntegrationModuleException the integration module exception
     */
    @Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#getPrescription")
    @Override
    public GetPrescriptionForPrescriberResult getPrescription(String rid) throws IntegrationModuleException {
        validateRid(rid);
        ApplicationConfig.getInstance().assertValidSession();
        try {

            // init helper
            MarshallerHelper<GetPrescriptionForPrescriberResult, GetPrescriptionForPrescriberParam> helper = new MarshallerHelper<GetPrescriptionForPrescriberResult, GetPrescriptionForPrescriberParam>(GetPrescriptionForPrescriberResult.class, GetPrescriptionForPrescriberParam.class);

            // get recipe etk
            final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

            // create sealed request
            GetPrescriptionForPrescriberParam param = new GetPrescriptionForPrescriberParam();
            param.setRid(rid);
            param.setSymmKey(getSymmKey().getEncoded());
            param.setPrescriberId(StandaloneRequestorProvider.getRequestorIdInformation());

            // build request
            GetPrescriptionForPrescriberRequest request = new GetPrescriptionForPrescriberRequest();
            request.setSecuredGetPrescriptionForPrescriberRequest(createSecuredContentType((sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param)))));

            PrescriberServiceAdministrativeInformationType info = new PrescriberServiceAdministrativeInformationType();

            // id added
            info.setPatientIdentifier(createIdentifierType(getPatientId(rid), IdentifierTypes.SSIN.name()));
            request.setAdministrativeInformation(info);

            // call sealed WS
            GetPrescriptionForPrescriberResponse response = null;

            try {
                response = RecipePrescriberServiceImpl.getInstance().getPrescriptionForPrescriber(request);
            } catch (ClientTransportException cte) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
            }

            checkStatus(response);

            // unseal WS response
            GetPrescriptionForPrescriberResult result = helper.unsealWithSymmKey(response.getSecuredGetPrescriptionForPrescriberResponse().getSecuredContent(), getSymmKey());

            KeyResult key = getKeyFromKgss(result.getEncryptionKeyId(), getEtkHelper().getSystemETK().get(0).getEncoded());
            byte[] unsealedPrescription = IOUtils.decompress(unsealPrescriptionForUnknown(key, result.getPrescription()));
            result.setPrescription(unsealedPrescription);

            // update the local cache
            setPatientId(result.getRid(), result.getPatientId());

            return result;
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
        return null;
    }

    /**
     * List open prescription.
     *
     * @param patientId
     * @return the list for a geiven patient.
     * @throws IntegrationModuleException the integration module exception
     */
    @Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#listOpenPrescription")
    @Override
    public List<String> listOpenPrescription(String patientId) throws IntegrationModuleException {
        ApplicationConfig.getInstance().assertValidSession();
        try {
            // init helper
            MarshallerHelper<GetListOpenPrescriptionResult, GetListOpenPrescriptionParam> helper = new MarshallerHelper<GetListOpenPrescriptionResult, GetListOpenPrescriptionParam>(GetListOpenPrescriptionResult.class, GetListOpenPrescriptionParam.class);

            // get recipe etk
            final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

            // create param
            GetListOpenPrescriptionParam param = new GetListOpenPrescriptionParam();
            param.setSymmKey(getSymmKey().getEncoded());
            param.setPrescriberId(StandaloneRequestorProvider.getRequestorIdInformation());
            param.setPatientId(patientId);

            // create request
            ListOpenPrescriptionsRequest request = new ListOpenPrescriptionsRequest();
            request.setSecuredListOpenPrescriptionsRequest(createSecuredContentType(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param))));

            // call sealed WS
            ListOpenPrescriptionsResponse response = null;
            try {
                response = RecipePrescriberServiceImpl.getInstance().listOpenPrescriptions(request);
            } catch (ClientTransportException cte) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
            }

            checkStatus(response);

            // unseal WS response
            GetListOpenPrescriptionResult result = helper.unsealWithSymmKey(response.getSecuredListOpenPrescriptionsResponse().getSecuredContent(), getSymmKey());

            return result.getPrescriptions() == null ? new ArrayList<String>() : result.getPrescriptions();

        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }

        return null;
    }

    /**
     * List open prescription.
     *
     * @return the list
     * @throws IntegrationModuleException the integration module exception
     */
    @Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#listOpenPrescription")
    @Override
    public List<String> listOpenPrescription() throws IntegrationModuleException {
        return listOpenPrescription(null);
    }

    /**
     * Address prescription.
     *
     * @param notificationText the notification text
     * @param patientId        the patient id
     * @param executorId       the executor id
     * @throws IntegrationModuleException the integration module exception
     */
    @Profiled(logFailuresSeparately = true, tag = "PrescriberIntegrationModule#sendNotification")
    @Override
    public void sendNotification(byte[] notificationText, String patientId, String executorId) throws IntegrationModuleException {
        ApplicationConfig.getInstance().assertValidSession();
        try {
            getKmehrHelper().assertValidNotification(notificationText);
            ValidationUtils.validatePatientId(patientId);

            // init helper
            MarshallerHelper<Object, SendNotificationParam> helper = new MarshallerHelper<>(Object.class, SendNotificationParam.class);

            // get recipe etk
            final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

            // get recipient etk
            List<EncryptionToken> etkRecipients = getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, executorId);

            byte[] notificationZip = IOUtils.compress(notificationText);

            for (int i = 0; i < etkRecipients.size(); i++) {
                EncryptionToken etkRecipient = etkRecipients.get(0);

                byte[] notificationSealed = sealNotification(etkRecipient, notificationZip);

                // create param
                SendNotificationParam param = new SendNotificationParam();
                param.setContent(notificationSealed);
                param.setExecutorId(executorId);
                param.setPrescriberId(StandaloneRequestorProvider.getRequestorIdInformation());
                param.setPatientId(patientId);

                // create request
                SendNotificationRequest request = new SendNotificationRequest();
                request.setSecuredSendNotificationRequest(createSecuredContentType(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param))));
                SendNotificationAdministrativeInformationType info = new SendNotificationAdministrativeInformationType();
                info.setExecutorIdentifier(createIdentifierType(executorId, IdentifierTypes.SSIN.name()));
                info.setPatientIdentifier(createIdentifierType(patientId, IdentifierTypes.SSIN.name()));
                request.setAdministrativeInformation(info);

                // call sealed WS
                try {
                    checkStatus(RecipePrescriberServiceImpl.getInstance().sendNotification(request));
                } catch (ClientTransportException cte) {
                    throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
                }
            }
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
    }

    /**
     * Update feedback flag.
     *
     * @param rid             the rid
     * @param feedbackAllowed the feedback allowed
     * @throws IntegrationModuleException the integration module exception
     */
    @Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#updateFeedbackFlag")
    @Override
    public void updateFeedbackFlag(String rid, boolean feedbackAllowed) throws IntegrationModuleException {
        validateRid(rid);
        ApplicationConfig.getInstance().assertValidSession();
        try {

            // init helper
            MarshallerHelper<Object, UpdateFeedbackFlagParam> helper = new MarshallerHelper<>(Object.class, UpdateFeedbackFlagParam.class);

            // get recipe etk
            final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

            // create param
            UpdateFeedbackFlagParam param = new UpdateFeedbackFlagParam();
            param.setAllowFeedback(feedbackAllowed);
            param.setRid(rid);
            param.setPrescriberId(StandaloneRequestorProvider.getRequestorIdInformation());

            UpdateFeedbackFlagRequest request = new UpdateFeedbackFlagRequest();
            request.setSecuredUpdateFeedbackFlagRequest(createSecuredContentType(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param))));
            PrescriberServiceAdministrativeInformationType info = new PrescriberServiceAdministrativeInformationType();

            // Admin Info for eHealth
            info.setPatientIdentifier(createIdentifierType(getPatientId(rid), IdentifierTypes.SSIN.name()));
            request.setAdministrativeInformation(info);

            // call sealed WS
            try {
                checkStatus(RecipePrescriberServiceImpl.getInstance().updateFeedbackFlag(request));
            } catch (ClientTransportException cte) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
            }
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }

    }

    @Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#listFeedback")
    @Override
    public List<ListFeedbackItem> listFeedback(boolean readFlag) throws IntegrationModuleException {
        ApplicationConfig.getInstance().assertValidSession();

        final String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
        try {
            // check if personal password has been set
            List<EncryptionToken> personalETKs = getEtkHelper().getEtks(KgssIdentifierType.NIHII, requestorIdInformation);

            getEncryptionUtils().verifyDecryption(personalETKs.get(0));

            // init helper
            MarshallerHelper<ListFeedbacksResult, ListFeedbacksParam> helper = new MarshallerHelper<ListFeedbacksResult, ListFeedbacksParam>(ListFeedbacksResult.class, ListFeedbacksParam.class);

            // get recipe etk
            final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

            // create param
            ListFeedbacksParam param = new ListFeedbacksParam();
            param.setReadFlag(readFlag);
            param.setSymmKey(getSymmKey().getEncoded());
            param.setPrescriberId(requestorIdInformation);

            // create request
            ListFeedbacksRequest request = new ListFeedbacksRequest();
            request.setSecuredListFeedbacksRequest(createSecuredContentType(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param))));

            // call sealed WS
            ListFeedbacksResponse response = null;

            try {
                response = RecipePrescriberServiceImpl.getInstance().listFeedbacks(request);
            } catch (ClientTransportException cte) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
            }
            checkStatus(response);

            // unseal WS response
            List<ListFeedbackItem> feedbacks = helper.unsealWithSymmKey(response.getSecuredListFeedbacksResponse().getSecuredContent(), getSymmKey()).getFeedbacks();

            for (int i = 0; i < feedbacks.size(); i++) {
                org.taktik.connector.business.recipe.prescriber.domain.ListFeedbackItem item = new org.taktik.connector.business.recipe.prescriber.domain.ListFeedbackItem(feedbacks.get(i));
                byte[] content = item.getContent();
                try {
                    content = unsealFeedback(content);
                    content = content == null ? content : IOUtils.decompress(content);
                    item.setContent(content);
                } catch (Throwable t) {
                    item.setLinkedException(t);
                }
                feedbacks.set(i, item);
            }
            return feedbacks;

        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
        return null;
    }

    private SecuredContentType createSecuredContentType(byte[] content) {
        SecuredContentType secured = new SecuredContentType();
        secured.setSecuredContent(content);
        return secured;
    }

    private void checkStatus(ResponseType response) throws IntegrationModuleException {
        if (!EHEALTH_SUCCESS_CODE_100.equals(response.getStatus().getCode()) && !EHEALTH_SUCCESS_CODE_200.equals(response.getStatus().getCode())) {
            LOG.error("Error Status received : " + response.getStatus().getCode());
            throw new IntegrationModuleException(getLocalisedMsg(response.getStatus()));
        }
    }

    private String getLocalisedMsg(StatusType status) {
        final String locale = IntegrationModuleException.getUserLocale();
        for (LocalisedString msg : status.getMessages()) {
            if (msg.getLang() != null && locale.equalsIgnoreCase(msg.getLang().value())) {
                return msg.getValue();
            }
        }
        if (status.getMessages().size() > 0) {
            return status.getMessages().get(0).getValue();
        }
        return status.getCode();
    }

    private IdentifierType createIdentifierType(final String id, final String type) {
        IdentifierType ident = new IdentifierType();
        ident.setId(id + "");
        ident.setType(type);
        return ident;
    }

    private String getPatientId(String rid) {
        if (prescriptionCache.containsKey(rid)) {
            return prescriptionCache.get(rid);
        }
        return "72081061175";
    }

    private void setPatientId(String rid, String patientId) {
        prescriptionCache.put(rid, patientId);
    }

    public void setPersonalPassword(String personalPassword) throws IntegrationModuleException {
        final SessionItem sessionItem = Session.getInstance().getSession();
        SessionValidator.assertValidSession(sessionItem);

        try {
            final String niss = STSHelper.getNiss(sessionItem.getSAMLToken().getAssertion());
            final EncryptionUtils encryptionUtils = EncryptionUtils.getInstance();
            encryptionUtils.unlockPersonalKey(niss, personalPassword);
            dataUnsealer = encryptionUtils.initUnsealing();
            List<EncryptionToken> tokens = getEtkHelper().getEtks(KgssIdentifierType.NIHII, StandaloneRequestorProvider.getRequestorIdInformation());
            encryptionUtils.verifyDecryption(tokens.get(0));
        } catch(Exception e){
            throw new IntegrationModuleException(e);
        }

    }

    @Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#unsealFeedback")
    protected byte[] unsealFeedback(byte[] message) throws IntegrationModuleException {
        return unsealNotiffeed(message);
    }

    @Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#getNewKeyFromKgss")
    protected KeyResult getNewKeyFromKgss(String prescriptionType, String prescriberId, String executorId, String patientId, byte[] myEtk) throws IntegrationModuleException {
        // For test, when a sim key is specified in the config
        if (getPropertyHandler().hasProperty("test_kgss_key")) {
            return getKeyFromKgss(null, null);
        }

        EncryptionToken etkKgss = getEtkHelper().getKGSS_ETK().get(0);
        List<String> credentialTypes = getPropertyHandler().getMatchingProperties("kgss.createPrescription.ACL." + prescriptionType);

        KeyResult keyResult = null;
        try {
            keyResult = kgssService.retrieveNewKey(etkKgss.getEncoded(), credentialTypes, prescriberId, executorId, patientId, myEtk);
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
        return keyResult;
    }

    @Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#sealNotification")
    protected byte[] sealNotification(EncryptionToken paramEncryptionToken, byte[] paramArrayOfByte) throws IntegrationModuleException {
        return Crypto.seal(paramEncryptionToken, paramArrayOfByte);
    }

    @Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#sealPrescriptionForUnknown")
    protected byte[] sealPrescriptionForUnknown(KeyResult key, byte[] messageToProtect) throws IntegrationModuleException {
        return Crypto.seal(messageToProtect, key.getSecretKey(), key.getKeyId());
    }

    private KmehrHelper getKmehrHelper() {
        return kmehrHelper;
    }
}
