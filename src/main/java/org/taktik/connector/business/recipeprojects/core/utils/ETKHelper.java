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
import org.apache.log4j.Logger;

import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleRuntimeException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.ServiceFactory;
import org.taktik.connector.technical.service.etee.domain.EncryptionToken;
import org.taktik.connector.technical.service.keydepot.KeyDepotService;
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotServiceImpl;

public class ETKHelper {

    private static final Logger LOG = Logger.getLogger(ETKHelper.class);
    private static final String RECIPE_ID = "0823257311";
    private static final String KGSS_ID = "0809394427";
    //private static final String TIP_ID = "0406753266";
    private static String PCDH_ID = "0406753266";
    private static final String MY_ETK_PROPERTY = "MY_ETK";

    private PropertyHandler propertyHandler;
    private EncryptionUtils encryptionUtils;

    private Map<String, EncryptionToken> etkCache = new HashMap<String, EncryptionToken>();
    private Map<String, List<EncryptionToken>> etksCache = new HashMap<String, List<EncryptionToken>>();
    private KeyDepotService keyDepotService = new KeyDepotServiceImpl();

    public ETKHelper(PropertyHandler propertyHandler, EncryptionUtils encryptionUtils) {
        this.propertyHandler = propertyHandler;
        this.encryptionUtils = encryptionUtils;
        init();
    }

    
    public List<EncryptionToken> getKGSS_ETK() throws IntegrationModuleException {
        return getEtks(KgssIdentifierType.CBE, KGSS_ID, "KGSS");
    }

    
    public List<EncryptionToken> getRecipe_ETK() throws IntegrationModuleException {
        return getEtks(KgssIdentifierType.CBE, RECIPE_ID, "");
    }

    
    public List<EncryptionToken> getTIPSystem_ETK(String tipSystemId) throws IntegrationModuleException {
        return getEtks(KgssIdentifierType.CBE, tipSystemId, "TIPSYSTEM");
    }

    
    public List<EncryptionToken> getTIP_ETK(String tipId) throws IntegrationModuleException {
        return getEtks(KgssIdentifierType.CBE, tipId, "");
    }

    
    public List<EncryptionToken> getPCDH_ETK() throws IntegrationModuleException {
        return getEtks(KgssIdentifierType.CBE, PCDH_ID, "PCDH");
    }

    
    public List<EncryptionToken> getSystemETK() throws IntegrationModuleException {
        String application = "";
        KgssIdentifierType identifierType = null;
        String identifierValue = "";

        if (propertyHandler.hasProperty(MY_ETK_PROPERTY)) {
            String myETK = propertyHandler.getProperty(MY_ETK_PROPERTY);
            if (myETK.indexOf(';') > -1) {
                String[] etk = myETK.split(";");
                if (etk.length > 1) {
                    identifierType = KgssIdentifierType.lookup(etk[0].toUpperCase());
                    identifierValue = etk[1];
                    if (etk.length >= 3) {
                        application = etk[2];
                    }
                }
            } else {
                try {
                    InputStream etkStream = IOUtils.getResourceAsStream(myETK);
                    byte[] etk = IOUtils.getBytes(etkStream);
                    List<EncryptionToken> encryptionTokens = new ArrayList<>();
                    EncryptionToken etkToken = new EncryptionToken(etk);
                    encryptionTokens.add(etkToken);
                    return encryptionTokens;
                } catch (Exception e) {
                    throw new IntegrationModuleException("Invalid ETK", e);
                }
            }
        } else {
            X509Certificate certificate = encryptionUtils.getCertificate();
            if (certificate != null) {
                try {
                    certificate.checkValidity();
                } catch (CertificateExpiredException e) {
                    throw new IntegrationModuleRuntimeException(I18nHelper.getLabel("error.expired.system.certificate"), e);
                } catch (CertificateNotYetValidException e) {
                    throw new IntegrationModuleRuntimeException(I18nHelper.getLabel("error.invalid.system.certificate"), e);
                }
            } else {
                throw new IntegrationModuleRuntimeException(I18nHelper.getLabel("error.notfound.system.certificate"));
            }
            CertificateParser parser = new CertificateParser(certificate);
            identifierType = KgssIdentifierType.lookup(parser.getType());
            identifierValue = parser.getValue();
            application = parser.getApplication();
        }

        if (identifierType == null || identifierValue == null || "".equals(identifierValue)) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.invalid.system.certificate"));
        }
        return getEtks(identifierType, Long.valueOf(identifierValue), application);
    }

    public List<EncryptionToken> getEtks(KgssIdentifierType identifierType, String identifierValue) throws IntegrationModuleException {
        return getEtks(identifierType, identifierValue, "");
    }

    public List<EncryptionToken> getEtks(KgssIdentifierType identifierType, Long identifierValue, String applicationid) throws IntegrationModuleException {
        if (null != identifierType) {
            switch (identifierType) {
                case CBE:
                    return getEtks(KgssIdentifierType.CBE, longToString(identifierValue, 10), applicationid);
                case SSIN:
                    return getEtks(KgssIdentifierType.SSIN, longToString(identifierValue, 11), applicationid);
                case NIHII_PHARMACY:
                    return getEtks(KgssIdentifierType.NIHII_PHARMACY, longToString(identifierValue, 8), applicationid);
            }
        }
                // ETK_ID_TYPE_NIHII
        return getEtks(KgssIdentifierType.NIHII, longToString(identifierValue / 1000, 8), applicationid);
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
            List<EncryptionToken> encryptiontokens = retrieveEtk(identifierType, identifierValue, application);
            return encryptiontokens;
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
        return null;
    }

    private void validateETKResponse(GetEtkResponse response, KgssIdentifierType identifierType, String identifierValue) throws IntegrationModuleException {
        List<ErrorType> errors = response.getErrors();

        for(int i = 0; i < errors.size(); ++i) {
            ErrorType error = (ErrorType)errors.get(i);
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
            MatchingEtk matchingEtk = (MatchingEtk)matchingEtks.get(i);

            for(int j = 0; j < matchingEtk.getIdentifiers().size(); ++j) {
                IdentifierType iType = (IdentifierType)matchingEtk.getIdentifiers().get(j);
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

                for (int i = 0; i < errors.size(); i++) {
                    ErrorType error = errors.get(i);
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

    public static String longToString(Long id, int numberOfDigits) {
        if (id == null) {
            return null;
        }

        StringBuilder buffer = new StringBuilder(Long.toString(id.longValue()));
        int delta = numberOfDigits - buffer.length();

        if (delta == 0) {
            return buffer.toString();
        }
        if (delta < 0) {
            throw new IllegalArgumentException("numberOfDigits < input length");
        }
        for (; delta > 0; --delta) {
            buffer.insert(0, "0");
        }
        return buffer.toString();
    }

    public static String subString(String id, int numberOfDigits) {
        if (StringUtils.isBlank(id)) {
            return null;
        }

        StringBuilder buffer = new StringBuilder(id);
        int delta = numberOfDigits - buffer.length();

        if (delta == 0) {
            return buffer.toString();
        }
        if (delta < 0) {
            throw new IllegalArgumentException("numberOfDigits < input length");
        }
        for (; delta > 0; --delta) {
            buffer.insert(0, "0");
        }
        return buffer.toString();
    }

    private void init() {
        if (propertyHandler.hasProperty("default.pcdh.id")) {
            String tmp = propertyHandler.getProperty("default.pcdh.id");
            if (!StringUtils.equals("", tmp)) {
                PCDH_ID = tmp;
            }
        }
    }

}
