package org.taktik.connector.business.recipe.executor;

import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.common.module.AbstractIntegrationModule;
import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.handlers.InsurabilityHandler;
import org.taktik.connector.business.recipeprojects.core.utils.IOUtils;
import org.taktik.connector.business.recipeprojects.core.utils.MarshallerHelper;
import org.taktik.connector.business.recipeprojects.core.utils.PropertyHandler;
import org.taktik.connector.business.recipe.executor.domain.GetPrescriptionForExecutorResult;
import org.taktik.connector.business.recipe.executor.domain.ListNotificationsItem;
import org.taktik.connector.business.recipe.utils.KmehrHelper;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.commons.core.v1.IdentifierType;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.core.v1.StatusType;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.recipe.services.executor.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Liesje Demuynck.
 */
public abstract class AbstractExecutorIntegrationModule extends AbstractIntegrationModule {

    private final static Logger LOG = Logger.getLogger(AbstractExecutorIntegrationModule.class);

    private Map<String, GetPrescriptionForExecutorResult> prescriptionCache = new HashMap<>();
    private KmehrHelper kmehrHelper;

    public AbstractExecutorIntegrationModule() throws IntegrationModuleException {
        super();
        kmehrHelper = new KmehrHelper(PropertyHandler.getInstance().getProperties());
    }

    public Map<String, GetPrescriptionForExecutorResult> getPrescriptionCache() {
        return prescriptionCache;
    }

    public KmehrHelper getKmehrHelper() {
        return kmehrHelper;
    }

    /**
     * @param rid
     * @param reason
     * @return
     * @throws IntegrationModuleException
     */
    protected byte[] getSealedRevokePrescriptionParam(String rid, String reason) throws IntegrationModuleException {
        RevokePrescriptionParam param = new RevokePrescriptionParam();
        param.setRid(rid);
        param.setReason(reason);
        param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());

        return sealForRecipe(param, RevokePrescriptionParam.class);
    }


    protected byte[] getSealedMarkAsArchivedParam(String rid) throws IntegrationModuleException {
        // create param
        MarkAsArchivedParam param = new MarkAsArchivedParam();
        param.setRid(rid);
        param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());

        return sealForRecipe(param, MarkAsArchivedParam.class);
    }

    protected byte[] getSealedMarkAsDeliveredParam(String rid) throws IntegrationModuleException {
        MarkAsDeliveredParam param = new MarkAsDeliveredParam();
        param.setRid(rid);
        param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());

        return sealForRecipe(param, MarkAsDeliveredParam.class);
    }

    protected byte[] getSealedMarkAsUndeliveredParam(String rid) throws IntegrationModuleException {
        MarkAsUndeliveredParam param = new MarkAsUndeliveredParam();
        param.setRid(rid);
        param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());

        return sealForRecipe(param, MarkAsUndeliveredParam.class);
    }

    protected byte[] getSealedGetPrescriptionForExecutorParam(String rid) throws IntegrationModuleException {
        GetPrescriptionForExecutorParam param = new GetPrescriptionForExecutorParam();
        param.setRid(rid);
        param.setSymmKey(getSymmKey().getEncoded());
        param.setVersion(getPropertyHandler().getProperty("connector.version"));
        param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
        return sealForRecipe(param, GetPrescriptionForExecutorParam.class);
    }

    protected byte[] getSealedCreateFeedbackParam(byte[] feedbackText, EncryptionToken etkRecipient, String rid, String prescriberId) throws Exception {
        byte[] message = IOUtils.compress(feedbackText);
        message = sealRequest(etkRecipient, message);

        CreateFeedbackParam param = new CreateFeedbackParam();
        param.setContent(message);
        param.setPrescriberId(prescriberId);
        param.setRid(rid);
        param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());

        return sealForRecipe(param, CreateFeedbackParam.class);
    }

    protected byte[] getSealedListNotificationsParam(boolean readFlag) throws IntegrationModuleException {
        ListNotificationsParam param = new ListNotificationsParam();
        param.setSymmKey(getSymmKey().getEncoded());
        param.setReadFlag(readFlag);
        param.setExecutorId(StandaloneRequestorProvider.getRequestorIdInformation());
        return sealForRecipe(param, ListNotificationsParam.class);
    }


    private <T> byte[] sealForRecipe(T data, Class<T> type) throws IntegrationModuleException {
        MarshallerHelper<Object, T> helper = new MarshallerHelper<>(Object.class, type);
        final EncryptionToken etkRecipe = getEtkHelper().getRecipe_ETK().get(0);
        return sealRequest(etkRecipe, helper.toXMLByteArray(data));
    }


    /**
     * Maps the SealedContent response from the executor to a {@link GetPrescriptionForExecutorResult}
     *
     * @param sealedExecutorResponse
     * @return
     */
    protected GetPrescriptionForExecutorResult createGetPrescriptionForExecutorResult(final byte[] sealedExecutorResponse) throws IntegrationModuleException {

        MarshallerHelper<GetPrescriptionForExecutorResultSealed, Object> marshaller = new MarshallerHelper<>(GetPrescriptionForExecutorResultSealed.class, Object.class);

        final String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
        final String requestorTypeInformation = StandaloneRequestorProvider.getRequestorTypeInformation();

        GetPrescriptionForExecutorResultSealed sealedResult = marshaller.unsealWithSymmKey(sealedExecutorResponse, getSymmKey());
        KeyResult key = getKeyFromKgss(sealedResult.getEncryptionKeyId(), getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0).getEncoded());
        byte[] unsealedPrescription = unsealWithSymKey(sealedResult, key, requestorIdInformation, requestorTypeInformation);

        GetPrescriptionForExecutorResult finalResult = new GetPrescriptionForExecutorResult(sealedResult);
        finalResult.setSealedContent(sealedResult.getPrescription());
        finalResult.setPrescription(unsealedPrescription);
        finalResult.setEncryptionKey(key.getSecretKey().getEncoded());
        finalResult.setInsurabilityResponse(InsurabilityHandler.getInsurability());
        finalResult.setMessageId(InsurabilityHandler.getMessageId());
        return finalResult;
    }


    /**
     * Maps the SealedContent response from the executor to a list of {@link ListNotificationsItem}
     *
     * @param sealedExecutorResponse
     * @return
     */
    protected List<be.recipe.services.executor.ListNotificationsItem> createListNotificationItems(final byte[] sealedExecutorResponse) throws IntegrationModuleException {

        MarshallerHelper<ListNotificationsResult, Object> marshaller = new MarshallerHelper<>(ListNotificationsResult.class, Object.class);
        List<be.recipe.services.executor.ListNotificationsItem> items = marshaller.unsealWithSymmKey(sealedExecutorResponse, getSymmKey()).getAddressedNotifications();

        for (int i = 0; i < items.size(); i++) {
            final ListNotificationsItem item = new ListNotificationsItem(items.get(i));
            try {
                item.setContent(IOUtils.decompress(unsealNotiffeed(item.getContent())));
            } catch (Throwable t) {
                item.setLinkedException(t);
            }
            items.set(i, item);
        }
        return items;
    }

    protected void checkStatus(ResponseType response) throws IntegrationModuleException {
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

    protected IdentifierType createIdentifierType(final String id, final String type) {
        IdentifierType ident = new IdentifierType();
        ident.setId(id);
        ident.setType(type);
        return ident;
    }


}
