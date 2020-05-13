package org.taktik.connector.business.recipeprojects.core.utils;

import java.io.InputStream;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import org.perf4j.aop.Profiled;

import org.taktik.connector.business.recipeprojects.core.domain.KgssIdentifierType;
import org.taktik.connector.business.recipeprojects.core.ehealth.services.KeyDepotService;
import org.taktik.connector.business.recipeprojects.core.ehealth.services.KeyDepotServiceImpl;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleRuntimeException;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionTokenFactory;

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
    private KeyDepotService keyDepotService = KeyDepotServiceImpl.getInstance();

    public ETKHelper(PropertyHandler propertyHandler, EncryptionUtils encryptionUtils) {
        this.propertyHandler = propertyHandler;
        this.encryptionUtils = encryptionUtils;
        init();
    }

    @Profiled(logFailuresSeparately = true, tag = "0.ETKHelper#getKGSS_ETK", logger = "org.perf4j.TimingLogger_Common")
    public List<EncryptionToken> getKGSS_ETK() throws IntegrationModuleException {
        return getEtks(KgssIdentifierType.CBE, KGSS_ID, "KGSS");
    }

    @Profiled(logFailuresSeparately = true, tag = "0.ETKHelper#getRecipe_ETK", logger = "org.perf4j.TimingLogger_Common")
    public List<EncryptionToken> getRecipe_ETK() throws IntegrationModuleException {
        return getEtks(KgssIdentifierType.CBE, RECIPE_ID, "");
    }

    @Profiled(logFailuresSeparately = true, tag = "0.ETKHelper#getTIPSystem_ETK", logger = "org.perf4j.TimingLogger_Common")
    public List<EncryptionToken> getTIPSystem_ETK(String tipSystemId) throws IntegrationModuleException {
        return getEtks(KgssIdentifierType.CBE, tipSystemId, "TIPSYSTEM");
    }

    @Profiled(logFailuresSeparately = true, tag = "0.ETKHelper#getTIP_ETK", logger = "org.perf4j.TimingLogger_Common")
    public List<EncryptionToken> getTIP_ETK(String tipId) throws IntegrationModuleException {
        return getEtks(KgssIdentifierType.CBE, tipId, "");
    }

    @Profiled(logFailuresSeparately = true, tag = "0.ETKHelper#getPCDH_ETK", logger = "org.perf4j.TimingLogger_Common")
    public List<EncryptionToken> getPCDH_ETK() throws IntegrationModuleException {
        return getEtks(KgssIdentifierType.CBE, PCDH_ID, "PCDH");
    }

    @Profiled(logFailuresSeparately = true, tag = "0.ETKHelper#getSystemETK", logger = "org.perf4j.TimingLogger_Common")
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
                    EncryptionToken etkToken = EncryptionTokenFactory.getInstance().create(etk);
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
            List<EncryptionToken> encryptiontokens = keyDepotService.retrieveEtk(identifierType, identifierValue, application);
            return encryptiontokens;
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
        return null;
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
