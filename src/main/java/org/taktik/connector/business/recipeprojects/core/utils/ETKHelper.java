package org.taktik.connector.business.recipeprojects.core.utils;

import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.fgov.ehealth.etkdepot._1_0.protocol.ErrorType;
import be.fgov.ehealth.etkdepot._1_0.protocol.GetEtkRequest;
import be.fgov.ehealth.etkdepot._1_0.protocol.GetEtkResponse;
import be.fgov.ehealth.etkdepot._1_0.protocol.IdentifierType;
import be.fgov.ehealth.etkdepot._1_0.protocol.MatchingEtk;
import be.fgov.ehealth.etkdepot._1_0.protocol.SearchCriteriaType;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleRuntimeException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.ServiceFactory;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.keydepot.KeyDepotService;
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotServiceImpl;

public class ETKHelper {

    private static final Logger LOG = LogManager.getLogger(ETKHelper.class);
    private static final String RECIPE_ID = "0823257311";
    private static final String KGSS_ID = "0809394427";

    private Map<String, EncryptionToken> etkCache = new HashMap<String, EncryptionToken>();
    private Map<String, List<EncryptionToken>> etksCache = new HashMap<String, List<EncryptionToken>>();

    public ETKHelper() {
    }

    public List<EncryptionToken> getKGSS_ETK() throws IntegrationModuleException {
        return getEtks(KgssIdentifierType.CBE, KGSS_ID, "KGSS");
    }
    
    public List<EncryptionToken> getRecipe_ETK() throws IntegrationModuleException {
        return getEtks(KgssIdentifierType.CBE, RECIPE_ID, "");
    }

    public List<EncryptionToken> getEtks(KgssIdentifierType identifierType, String identifierValue) throws IntegrationModuleException {
        return getEtks(identifierType, identifierValue, "");
    }

    private List<EncryptionToken> getEtks(KgssIdentifierType identifierType, String identifierValue, String application) throws IntegrationModuleException {
        String etkCacheId = identifierType + "/" + identifierValue + "/" + application;
        if (etkCache.containsKey(etkCacheId)) {
            LOG.info("ETK retrieved from the cache : " + etkCacheId);
            return etksCache.get(etkCacheId);
        }
        List<EncryptionToken> encryptionTokens = getEtksFromDepot(identifierType, identifierValue, application);
        etksCache.put(etkCacheId, encryptionTokens);
        return encryptionTokens;
    }

    private List<EncryptionToken> getEtksFromDepot(KgssIdentifierType identifierType, String identifierValue, String application) throws IntegrationModuleException {
        try {
            return retrieveEtk(identifierType, identifierValue, application);
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
        return null;
    }

    private void validateETKResponse(GetEtkResponse response, KgssIdentifierType identifierType, String identifierValue) throws IntegrationModuleException {
        List<ErrorType> errors = response.getErrors();

        for(int i = 0; i < errors.size(); ++i) {
            ErrorType error = errors.get(i);
            if (!StringUtils.isEmpty(error.getCode()) && error.getCode().equals("NO_MATCHING_ETK")) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.etk.notfound", new Object[]{identifierType.getName(), identifierValue}));
            }

            if (!StringUtils.isEmpty(error.getCode()) && error.getCode().equals("INVALID_NIHII_NUMBER")) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.etk.invalid.nihii", new Object[]{identifierValue}));
            }

            if (!StringUtils.isEmpty(error.getCode()) && error.getCode().equals("INVALID_CBE_NUMBER")) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.etk.invalid.cbe", new Object[]{identifierValue}));
            }

            if (!StringUtils.isEmpty(error.getCode()) && error.getCode().equals("INVALID_SSIN_NUMBER")) {
                throw new IntegrationModuleException(I18nHelper.getLabel("error.etk.invalid.ssin", new Object[]{identifierValue}));
            }
        }
    }

    private List<EncryptionToken> multiEtkResponse(List<EncryptionToken> encryptiontokens, KgssIdentifierType identifierType, String identifierValue, String application, List<byte[]> etks) throws IntegrationModuleException, GeneralSecurityException, TechnicalConnectorException {
        GetEtkResponse etkResponse = null;
        GetEtkRequest paramGetEtkRequest = this.createEtkRequest(identifierType, identifierValue, application, false);
        KeyDepotService service = ServiceFactory.getKeyDepotService();
        etkResponse = service.getETK(paramGetEtkRequest);
        this.validateETKResponse(etkResponse, identifierType, identifierValue);
        List<MatchingEtk> matchingEtks = etkResponse.getMatchingEtks();

        for(int i = 0; i < matchingEtks.size(); ++i) {
            MatchingEtk matchingEtk = matchingEtks.get(i);

            for(int j = 0; j < matchingEtk.getIdentifiers().size(); ++j) {
                IdentifierType iType = matchingEtk.getIdentifiers().get(j);
                String value = iType.getValue();
                String applicationid = iType.getApplicationID();
                KgssIdentifierType kgssType = KgssIdentifierType.lookup(iType.getType());
                if (kgssType != null && kgssType.getName().equals(identifierType.getName())) {
                    GetEtkRequest etkRequest = this.createEtkRequest(kgssType, value, applicationid, true);
                    GetEtkResponse resp = null;
                    resp = service.getETK(etkRequest);
                    this.validateETKResponse(resp, kgssType, value);
                    encryptiontokens = this.singleEtkResponse(resp, encryptiontokens, etks);
                } else {
                    LOG.debug("ETK - Not the correct identifier type: " + kgssType.getName() + " has to be " + identifierType.getName() + ". The value of this identifier is: " + value + " and the application id is: " + applicationid);
                }
            }
        }

        return encryptiontokens;
    }

    private List<EncryptionToken> singleEtkResponse(GetEtkResponse response, List<EncryptionToken> encryptiontokens, List<byte[]> etks) throws IntegrationModuleException, GeneralSecurityException {
        byte[] etk = response.getETK();
        if (etk == null) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.etk.other"));
        } else {
            if (!etks.contains(etk)) {
                etks.add(etk);
                EncryptionToken encryptionToken = new EncryptionToken(etk);
                encryptiontokens.add(encryptionToken);
            }

            return encryptiontokens;
        }
    }

    private GetEtkRequest createEtkRequest(KgssIdentifierType identifierType, String identifierValue, String applicationid, boolean setapplicationid) {
        GetEtkRequest paramGetEtkRequest = new GetEtkRequest();
        SearchCriteriaType paramSearchCriteriaType = new SearchCriteriaType();
        IdentifierType it = new IdentifierType();
        it.setType(identifierType.getName());
        it.setValue(identifierValue);
        if (setapplicationid) {
            it.setApplicationID(applicationid);
        }

        paramSearchCriteriaType.getIdentifiers().add(it);
        paramGetEtkRequest.setSearchCriteria(paramSearchCriteriaType);
        return paramGetEtkRequest;
    }
    
    public List<EncryptionToken> retrieveEtk(KgssIdentifierType identifierType, String identifierValue, String applicationName) throws IntegrationModuleException, GeneralSecurityException, TechnicalConnectorException {
		/*
		 * Create request
		 */
        // create etk request
        GetEtkRequest request = new GetEtkRequest();
        // create the search criteria
        SearchCriteriaType searchCriteria = new SearchCriteriaType();
        // the request expects a list of identifiers to get the ETK for.
        // in this example we will provide a list with 1 item.
        List<IdentifierType> listIdentifiers = new ArrayList<IdentifierType>();
        // create the identifier
        IdentifierType identifier = new IdentifierType();
        // set the application name
        identifier.setApplicationID(applicationName);
        // set the type (e.g. CBE)
        identifier.setType(identifierType.getName());
        // set the value associated with the type
        identifier.setValue(identifierValue);
        // add the identifier to the list
        listIdentifiers.add(identifier);
        // add the list to the search criteria
        searchCriteria.getIdentifiers().addAll(listIdentifiers);
        // add the search criteria to the request
        request.setSearchCriteria(searchCriteria);

		/*
		 * Invoke the technical connector framework's ETK Service's getEtk operation
		 */
        try {
            KeyDepotService service = ServiceFactory.getKeyDepotService();
            GetEtkResponse response = service.getETK(request);

            List<EncryptionToken> encryptiontokens = new ArrayList<>();
            List<byte[]> etks = new ArrayList<>();
            if (response.getETK() == null) {
                List<ErrorType> errors = response.getErrors();

                for (ErrorType error : errors) {
                    if (!StringUtils.isEmpty(error.getCode()) && error.getCode().equals("NO_MATCHING_ETK")) {
                        encryptiontokens = multiEtkResponse(encryptiontokens, identifierType, identifierValue, applicationName, etks);
                        break;
                    } else {
                        validateETKResponse(response, identifierType, identifierValue);
                    }
                }

            } else {
                validateETKResponse(response, identifierType, identifierValue);
                encryptiontokens = singleEtkResponse(response, encryptiontokens, etks);
            }

            return encryptiontokens;

        } catch (TechnicalConnectorException e) {
            //TODO: DENNIS: Test how to see the full stacktrace in log
            LOG.error("Error retrieving new key", e);
            throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.retrieve.new.key"), e);
        }
    }
}
