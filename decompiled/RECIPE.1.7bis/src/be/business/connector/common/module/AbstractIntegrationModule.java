/*
 * 
 */
package be.business.connector.common.module;

import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.business.connector.common.ApplicationConfig;
import org.taktik.connector.business.recipeprojects.core.ehealth.services.KgssService;
import org.taktik.connector.business.recipeprojects.core.ehealth.services.KgssServiceImpl;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.technical.connector.utils.Crypto;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.taktik.connector.business.recipeprojects.core.utils.*;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.utils.ConnectorCryptoUtils;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealer;
import be.fgov.ehealth.etee.crypto.decrypt.UnsealedData;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealer;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import be.fgov.ehealth.etee.crypto.status.CryptoResultException;
import be.fgov.ehealth.etee.crypto.status.NotificationError;
import be.fgov.ehealth.etee.crypto.status.NotificationWarning;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponseContent;
import be.fgov.ehealth.technicalconnector.signature.AdvancedElectronicSignatureEnumeration;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilder;
import be.fgov.ehealth.technicalconnector.signature.SignatureBuilderFactory;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import be.fgov.ehealth.technicalconnector.signature.domain.XadesOption;
import be.recipe.services.executor.*;
import be.recipe.services.executor.Properties;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.cms.bc.BcRSASignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.tsp.TimeStampTokenInfo;
import org.bouncycastle.util.encoders.Base64;
import org.perf4j.aop.Profiled;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractIntegrationModule {

    private final static Logger LOG = Logger.getLogger(AbstractIntegrationModule.class);

    public static long TIME_KGSS_CALL = 0L;
    public static final String EHEALTH_SUCCESS_CODE_100 = "100";
    public static final String EHEALTH_SUCCESS_CODE_200 = "200";
    public static final String EHEALTH_SUCCESS_CODE_300 = "300";
    public static final String EHEALTH_SUCCESS_CODE_400 = "400";
    public static final String EHEALTH_SUCCESS_CODE_500 = "500";
    public static final int PATTERN_LENGTH = 12;
    public static final String RID_PATTERN = "BE(P|K|N)(P|0|1|2|3|4|5|6|7|8|9)[(0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | A | B | C | D | E | F | G | H | J | K | L | M | N | P | Q | R | S | T | V | W | X | Y | Z)]{8}";
    private final Pattern ridPattern = Pattern.compile(RID_PATTERN);

    protected DataUnsealer dataUnsealer = null;
    private DataSealer oldDataSealer = null;
    private DataUnsealer oldDataUnsealer = null;

    private ETKHelper etkHelper;
    private Key symmKey = null;

    private CacheManager cacheManager;
    private Cache kgssCache;
    private Cache etkCache;

    private final KgssService kgssService = KgssServiceImpl.getInstance();

    public AbstractIntegrationModule() throws IntegrationModuleException {
        ApplicationConfig.getInstance().assertInitialized();
        init();
    }

    protected void init() throws IntegrationModuleException {
        try {
            LOG.info("Init abstractIntegrationModule!");
            LoggingUtil.initLog4J(getPropertyHandler());

            getJaxContextCentralizer().addContext(GetKeyRequestContent.class);
            getJaxContextCentralizer().addContext(GetKeyResponseContent.class);

            Security.addProvider(new BouncyCastleProvider());

            MessageDumper.getInstance().init(getPropertyHandler());

            // When running in DOTNET, the current context class loader must be overriden to avoid class not found exceptions!!!
            Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
            System.setProperty("javax.xml.soap.SOAPFactory", "com.sun.xml.messaging.saaj.soap.ver1_1.SOAPFactory1_1Impl");

            // Extra debug information
            if (LOG.isDebugEnabled()) {
                LOG.debug("Curdir : " + new File(".").getCanonicalPath());
                LOG.debug("Support P12 keystores : " + KeyStore.getInstance("PKCS12"));
            }

            initCaching();
            initEncryption();
            LOG.info("End Init abstractIntegrationModule!");
        } catch (Throwable t) {
            LOG.error("Exception in init abstractIntegrationModule: ", t);
            Exceptionutils.errorHandler(t);
        }
    }

    private void initCaching() {
        LOG.info("INIT CACHE MANAGER");
        URL url = getClass().getResource("/cache/config/ehcache.xml");
        cacheManager = CacheManager.newInstance(url);

        LOG.info("DOES KGSS CACHE EXIST?");
        kgssCache = cacheManager.getCache("KGSS");
        if (kgssCache == null) {
            LOG.info("NEW KGSS CACHE");
            kgssCache = new Cache("KGSS", 0, false, false, 0, 0);
            cacheManager.addCache(kgssCache);
        }

        LOG.info("DOES ETK CACHE EXIST?");
        etkCache = cacheManager.getCache("ETK");
        if (etkCache == null) {
            LOG.info("NEW ETK CACHE");
            etkCache = new Cache("ETK", 0, false, false, 0, 0);
            cacheManager.addCache(etkCache);
        }
    }

    public void initEncryption() throws IntegrationModuleException {
        try {

            LOG.info("Init the encryption - create symmKey");
            symmKey = getEncryptionUtils().generateSecretKey();

            if (getEncryptionUtils().getOldKeyStore() != null) {
                oldDataSealer = getEncryptionUtils().initOldSealing();
                oldDataUnsealer = getEncryptionUtils().initOldUnSealing();
            }
            LOG.info("Init the encryption - init etkHelper");
            etkHelper = new ETKHelper(getPropertyHandler(), getEncryptionUtils());

            // if (hasPersonalEtk()) { //only for care providers
            // LOG.info("Init the encryption - care provider has a personal ETK");
            // encrUtils.verifyDecryption(etkHelper.getSystemETK().get(0));
            // }
        } catch (Throwable t) {
            LOG.error("Exception occured when initializing the encryption util: ", t);
            Exceptionutils.errorHandler(t, "error.initialization");
        }
    }

    /**
     * Checks for personal etk. Default implementation returns true always. Override and set to false for patients.
     *
     * @return whether the user has an etk.
     */
    protected boolean hasPersonalEtk() {
        return true;
    }

    @Profiled(logFailuresSeparately = true, tag = "0.AbstractIntegrationModule#sealRequest", logger = "org.perf4j.TimingLogger_Common")
    protected synchronized byte[] sealRequest(EncryptionToken paramEncryptionToken, byte[] paramArrayOfByte) throws IntegrationModuleException {
        return Crypto.seal(paramEncryptionToken, paramArrayOfByte);
    }

    /**
     * Unseal request.
     *
     * @param message the message
     * @return the byte[]
     * @throws IntegrationModuleException the integration module exception
     */
    @Profiled(logFailuresSeparately = true, tag = "0.AbstractIntegrationModule#unsealRequest", logger = "org.perf4j.TimingLogger_Common")
    protected byte[] unsealRequest(byte[] message) throws IntegrationModuleException {
        return Crypto.unseal(message);
    }

    /**
     * Unseal.
     *
     * @param message the message
     * @return the byte[]
     * @throws IntegrationModuleException the integration module exception
     */
    protected byte[] unsealNotif(byte[] message) throws IntegrationModuleException {
        return Crypto.unseal(message);
    }

    protected byte[] unsealNotifOld(byte[] message) {
        CryptoResult<UnsealedData> result = getOldDataUnsealer().unseal(message);
        // decryption operation succeeded and there are no errors or failures
        if (result != null && result.hasData()) {

            if (result.hasErrors()) { // 3.A.A. There are no errors or failures
                for (NotificationError error : result.getErrors()) {
                    LOG.error(error.name());
                }
                for (NotificationWarning warning : result.getWarnings()) {
                    LOG.error(warning.name());
                }
                if (result.getFatal() != null) {
                    LOG.error(result.getFatal().getErrorMessage());
                }
            }

            // Get the unsealed data
            InputStream unsealedDataStream = result.getData().getContent();
            byte[] unsealedData = IOUtils.getBytes(unsealedDataStream);

            return unsealedData;
        }
        return null;
    }

    protected byte[] unsealNotiffeed(byte[] message) throws IntegrationModuleException {
        byte[] unsealedNotification = null;
        boolean calledUnsealNotifOld = false;
        try {
            LOG.debug("Start unseal notification: " + org.apache.commons.io.IOUtils.toString(message, "UTF-8"));
            unsealedNotification = Crypto.unseal(message);
            if (unsealedNotification != null) {
                return unsealedNotification;
            }
            if (getOldDataUnsealer() != null) {
                LOG.debug("Unseal notification was null. Start unseal notification with old keystore: " + Arrays.toString(message));
                calledUnsealNotifOld = true;
                unsealedNotification = unsealNotifOld(message);
                if (unsealedNotification != null) {
                    return unsealNotifOld(message);
                }
            } else {
                LOG.debug("OldDataUnsealer is null.");
            }
        } catch (Throwable t) {
            LOG.error("Exception occured with unsealing notification: ", t);
            if (calledUnsealNotifOld) {
                if (t instanceof CryptoResultException && t.getMessage().contains("There is no data available")) {
                    return null;
                }
                Exceptionutils.errorHandler(t, "error.data.unseal");
            } else {
                try {
                    LOG.debug("Exception occured with unsealing notification. Trying to unseal notification with old keystore: " + Arrays.toString(message));
                    unsealedNotification = unsealNotifOld(message);
                } catch (Throwable te) {
                    if (t instanceof CryptoResultException && t.getMessage().contains("There is no data available")) {
                        return null;
                    }
                    Exceptionutils.errorHandler(te, "error.data.unseal");
                }
            }
        }
        if (unsealedNotification == null) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.data.unseal"));
        }
        return unsealedNotification;
    }

    @Profiled(logFailuresSeparately = true, tag = "0.AbstractIntegrationModule#unsealPrescriptionForUnknown", logger = "org.perf4j.TimingLogger_Common")
    protected byte[] unsealPrescriptionForUnknown(KeyResult key, byte[] protectedMessage) throws IntegrationModuleException {
        return Crypto.unsealForUnknown(key, protectedMessage);
    }

    protected KeyResult getKeyFromKgss(String keyId) throws IntegrationModuleException {
        return getKeyFromKgss(keyId, null);
    }

    @Profiled(logFailuresSeparately = true, tag = "0.AbstractIntegrationModule#getKeyFromKgss", logger = "org.perf4j.TimingLogger_Common")
    public KeyResult getKeyFromKgss(String keyId, byte[] myEtk) throws IntegrationModuleException {
        KeyResult keyResult = null;
        try {
            // For test, when a sim key is specified in the config
            if (getPropertyHandler().hasProperty("test_kgss_key")) {
                String part1 = getPropertyHandler().getProperty("test_kgss_key").split(";")[0];
                String part2 = getPropertyHandler().getProperty("test_kgss_key").split(";")[1];
                // LOG.info("KGSS key retrieved from configuration. Key Id = part1);
                byte[] keyResponse = Base64.decode(part2);
                return new KeyResult(new SecretKeySpec(keyResponse, "AES"), part1);
            }

            keyResult = kgssService.retrieveKeyFromKgss(keyId.getBytes(), myEtk, etkHelper.getKGSS_ETK().get(0).getEncoded());

        } catch (Throwable t) {
            LOG.error("Exception in getKeyFromKgss abstractIntegrationModule: ", t);
            Exceptionutils.errorHandler(t);
        }
        return keyResult;
    }

    protected Key getSymmKey() {
        return symmKey;
    }

    public EncryptionUtils getEncryptionUtils() {
        return EncryptionUtils.getInstance();
    }

    public PropertyHandler getPropertyHandler() {
        return PropertyHandler.getInstance();
    }

    public void setOldDataUnsealer(DataUnsealer oldDataUnsealer) {
        this.oldDataUnsealer = oldDataUnsealer;
    }

    public DataUnsealer getOldDataUnsealer() {
        return oldDataUnsealer;
    }

    public void setOldDataSealer(DataSealer oldDataSealer) {
        this.oldDataSealer = oldDataSealer;
    }

    public DataSealer getOldDataSealer() {
        return oldDataSealer;
    }

    protected ETKHelper getEtkHelper() {
        return etkHelper;
    }


    public void setDataUnsealer(DataUnsealer dataUnsealer) {
        this.dataUnsealer = dataUnsealer;
    }

    public JaxContextCentralizer getJaxContextCentralizer() {
        return JaxContextCentralizer.getInstance();
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public Cache getKgssCache() {
        return kgssCache;
    }

    public void setKgssCache(Cache kgssCache) {
        this.kgssCache = kgssCache;
    }

    public Cache getEtkCache() {
        return etkCache;
    }

    public void setEtkCache(Cache etkCache) {
        this.etkCache = etkCache;
    }

    protected byte[] unsealWithSymKey(GetPrescriptionForExecutorResultSealed result, KeyResult key, String identifier, String type) throws IntegrationModuleException {
        try {
            String timestampedPrescriptionVersion = VERSION_2;
            byte[] sealedPrescription;
            TimestampedPrescription tp = null;
            try {
                MarshallerHelper<TimestampedPrescription, TimestampedPrescription> helper = new MarshallerHelper<>(TimestampedPrescription.class,
                        TimestampedPrescription.class);
                tp = helper.unmarsh(result.getPrescription());
                sealedPrescription = tp.getPrescriptionWithSecurityToken().getPrescriptionSealed();
            } catch (Exception ex) {
                LOG.debug("Prescription is version 1: ", ex);
                timestampedPrescriptionVersion = VERSION_1;
                sealedPrescription = result.getPrescription();
            }
            byte[] unsealedPrescription = unsealPrescriptionForUnknown(key, sealedPrescription);
            unsealedPrescription = IOUtils.decompress(unsealedPrescription);

            archivePrescription(unsealedPrescription, result, sealedPrescription, timestampedPrescriptionVersion, tp, key, identifier, type);
            return unsealedPrescription;
        } catch (IOException | TechnicalConnectorException | XPathExpressionException ex) {
            Exceptionutils.errorHandler(ex, "error.data.uncompression");
        }
        return null;
    }

    private static final String VERSION_1 = "v1";
    private static final String VERSION_2 = "v2";

    private void archivePrescription(byte[] unsealedPrescription, GetPrescriptionForExecutorResultSealed result,
                                     byte[] sealedPrescription, String timestampedPrescriptionVersion, TimestampedPrescription tp, KeyResult key,
                                     String identifier, String type) throws IOException, IntegrationModuleException, TechnicalConnectorException, XPathExpressionException {
        boolean archivePrescription = Boolean.parseBoolean(getPropertyHandler().getProperty("ArchivePrescription-xml", "false"));
        ID prescriberFromKmehr = getPrescriberIdFromKmehr(new ByteArrayInputStream(unsealedPrescription));
        ArchiveStandard archiveStandard = new ArchiveStandard();
        //TODO: this validation are meant to be used in RAOTD
        if (Boolean.parseBoolean(getPropertyHandler().getProperty("validate.befor.archiving", "true"))) {
            validatePrescriber(tp, archiveStandard, prescriberFromKmehr);
            if (VERSION_2.equalsIgnoreCase(timestampedPrescriptionVersion)) {
                validateXadesT(result, archiveStandard, SignatureVerificationError.CERTIFICATE_CHAIN_COULD_NOT_BE_VERIFIED);
            } else {
                validateTimeStamping(result, archiveStandard);
            }
        }
        if (archivePrescription) {
            String archivingPath = getArchivedFilePath(result.getRid());
            MarshallerHelper<ArchiveStandard, ArchiveStandard> helper = new MarshallerHelper<>(ArchiveStandard.class, ArchiveStandard.class);
            archiveStandard = getArchiveStandard(result, unsealedPrescription, sealedPrescription,
                    timestampedPrescriptionVersion, tp, key, identifier, type, prescriberFromKmehr, archiveStandard);
            helper.wrtiePrescriptionToFile(helper.toXMLByteArray(archiveStandard), archivingPath);
        }
    }

    protected String getArchivedFilePath(String rid) {
        String archivingPath = getPropertyHandler().getProperty("ArchivePrescriptionDirectory");
        archivingPath = archivingPath == null ? null : archivingPath.trim();
        if (archivingPath != null) {
            if (archivingPath.endsWith("/")) {
                archivingPath = archivingPath.concat(rid);
            } else {
                archivingPath = archivingPath.concat("/").concat(rid);
            }
            archivingPath = archivingPath.concat(".xml");

        }
        return archivingPath;
    }

    public byte[] getArchivedPrescription(String archivedFilePath, String... options) throws IntegrationModuleException {
        try {
            return Files.readAllBytes(Paths.get(archivedFilePath));
        } catch (Throwable ex) {
            LOG.warn("Could not read file [" + archivedFilePath + "].", ex);
            throw new IntegrationModuleException(I18nHelper.getLabel("error.archive.file", options));
        }
    }

    public XMLGregorianCalendar getCurrentXMLGregorianCalendar() {
        XMLGregorianCalendar xgcal = null;
        try {
            GregorianCalendar gcal = new GregorianCalendar();
            gcal.setTimeInMillis(System.currentTimeMillis());
            xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        } catch (DatatypeConfigurationException ex) {
            LOG.error("Error creating a xml gregorian calendat!! ", ex);
        }
        return xgcal;
    }

    private ArchiveStandard getArchiveStandard(GetPrescriptionForExecutorResultSealed result, byte[] unsealedPrescription, byte[] sealedPrescription,
                                               String timestampedPrescriptionVersion, TimestampedPrescription tp, KeyResult key, String identifier, String type, ID id,
                                               ArchiveStandard archiveStandard) {
//        MarshallerHelper<KeyResult, KeyResult> helper = new MarshallerHelper<>(KeyResult.class, KeyResult.class);
        archiveStandard.setCreationDate(getCurrentXMLGregorianCalendar());
        if (VERSION_1.equalsIgnoreCase(timestampedPrescriptionVersion)) {
            archiveStandard.setPrescriptionSealed(sealedPrescription);
            archiveStandard.setTimestampeId(result.getTimestampingId());
            archiveStandard.setPatientId(getID(result.getPatientId(), "SSIN", Qualities.CITIZEN.name()));
            archiveStandard.setPrescriberId(getID(result.getPrescriberId(), "NIHII", Qualities.DOCTOR.name()));
        } else {
            archiveStandard.setTimestampedPrescription(result.getPrescription());
            archiveStandard.setPatientId(tp.getPrescriptionWithSecurityToken().getPatientId());
            ID prescId = tp.getPrescriptionWithSecurityToken().getPrescriberId();
            //TODO: this code snipet has to be deleted when a new version of Recip-e is deployed after 4/10/2016
            //Begin:
            prescId.setIdType(prescId.getIdType().replace("NIHHI", "NIHII"));
            //END
            archiveStandard.setPrescriberId(prescId);
        }

        archiveStandard.setExecutorId(getID(identifier, type, Qualities.PHARMACY.name()));
        archiveStandard.setPrescriptionPrescriberId(id);
        archiveStandard.setPrescriptionType(result.getPrescriptionType());
        archiveStandard.setRid(result.getRid());
        archiveStandard.setEncryptionKeyId(result.getEncryptionKeyId());
        archiveStandard.setEncryptionKey(key.getSecretKey().getEncoded());
        archiveStandard.setTimestampedPrescriptionVersion(timestampedPrescriptionVersion);
        archiveStandard.setUnsealedPrescription(unsealedPrescription);
        Properties properties = new Properties();
        properties.getProperty().add(getProperty("sdkVersion", getPropertyHandler().getProperty("sdk.version")));
        properties.getProperty().add(getProperty("SupportedPrescriptionVersion", getPropertyHandler().getProperty("supported.prescription.version")));
        properties.getProperty().add(getProperty("prescriptionVersion", getPropertyHandler().getProperty("prescription.version")));
        archiveStandard.setProperties(properties);

        return archiveStandard;
    }

    private Property getProperty(String key, String value) {
        Property property = new Property();
        property.setKey(key);
        property.setValue(value);
        return property;
    }

    private void validatePrescriber(TimestampedPrescription tp, ArchiveStandard archiveStandard, ID prescriberFromKmehr) throws IntegrationModuleException {
        if (tp != null) {
            try {
                byte[] samlToken = tp.getPrescriptionWithSecurityToken().getSecurityToken();
                Document doc = obtenerDocumentDeByte(samlToken);
                String nihiiInSamlToken = STSHelper.getNihii(doc.getDocumentElement());

                boolean ErrorOccurs = false;

                if (StringUtils.isNotBlank(prescriberFromKmehr.getValue()) && StringUtils.isNotBlank(nihiiInSamlToken)) {
                    LOG.debug("NIHII found in the Saml Assertion is [" + nihiiInSamlToken + "], and the one in the kmehr message [" + prescriberFromKmehr.getValue() + "].");
                    if (!prescriberFromKmehr.getValue().substring(0, 8).equals(nihiiInSamlToken.substring(0, 8))) {
                        ErrorOccurs = true;
                    }
                } else {
                    ErrorOccurs = true;
                }
                if (ErrorOccurs) {
                    LOG.error("Prescriber mismach between kmehr and SAMLToken!! [" + prescriberFromKmehr.getValue() + "]-[" + nihiiInSamlToken + "]");
                    archiveStandard.setValidationWarnings(getValidationWarnings(archiveStandard.getValidationWarnings(), "RECIPE", "1041", "ERROR", "error.validation.presciber.mismach", prescriberFromKmehr.getValue(), nihiiInSamlToken));
                }
            } catch (Throwable ex) {
//            new Exception("Prescriber mismach between kmehr and SAMLToken!! [" + prescriberId + "]<>[" + nihii + "]");
                LOG.error("Problems validating the prescriber in the samlToken against the one in the Kmehr!!!", ex);
                archiveStandard.setValidationWarnings(getValidationWarnings(archiveStandard.getValidationWarnings(), "RECIPE", "1042", "ERROR", "error.validation.presciber"));
            }
        }
    }

    private Document obtenerDocumentDeByte(byte[] documentoXml) throws SAXException, ParserConfigurationException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new ByteArrayInputStream(documentoXml));
    }

    private ID getPrescriberIdFromKmehr(InputStream is) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        NamespaceContext nsCtx = new MapNamespaceContext("http://www.ehealth.fgov.be/standards/kmehr/schema/v1");
        xPath.setNamespaceContext(nsCtx);
        InputSource inputSource = new InputSource(is);
        Document doc = (Document) xPath.evaluate("/", inputSource, XPathConstants.NODE);
        String idValue = xPath.evaluate("/ns1:kmehrmessage/ns1:header/ns1:sender/ns1:hcparty/ns1:id[@S='ID-HCPARTY' and @SV='1.0']/text()", doc);
        String type = xPath.evaluate("/ns1:kmehrmessage/ns1:header/ns1:sender/ns1:hcparty/ns1:cd[@S='CD-HCPARTY' and @SV='1.0']/text()", doc);
        ID id = new ID();
        id.setIdType("NIHII");
        id.setValue(idValue);
        id.setType(type);
        return id;
    }

    public SignatureVerificationResult validateXadesT(GetPrescriptionForExecutorResultSealed gpfers, ArchiveStandard archiveStandard, SignatureVerificationError... errors) throws TechnicalConnectorException {
        LOG.debug("Verify XadesT signature.");
        String toSignStringOrig = new String(gpfers.getPrescription());
        String toSignDoc = toSignStringOrig.substring(0, toSignStringOrig.indexOf("<ds:Signature")) + "</ns2:timestampedPrescription>";
        String signatureString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + toSignStringOrig.substring(toSignStringOrig.indexOf("<ds:Signature"),
                toSignStringOrig.indexOf("</ds:Signature>") + "</ds:Signature>".length());
        LOG.debug("To sign document [" + toSignDoc + "].");
        LOG.debug("Signature [" + signatureString + "].");
        SignatureBuilder builder1 = SignatureBuilderFactory.getSignatureBuilder(AdvancedElectronicSignatureEnumeration.XAdES_T);
        Map<String, Object> options = new HashMap<>();
        options.put(XadesOption.FOLLOWNESTEDMANIFEST, Boolean.TRUE);
        options.put(XadesOption.ENCAPSULATE, Boolean.TRUE);
        SignatureVerificationResult result = builder1.verify(toSignDoc.trim().getBytes(), signatureString.getBytes(Charset.forName("UTF-8")), options);
        for (SignatureVerificationError error : errors) {
            result.getErrors().remove(error);
        }
        for (SignatureVerificationError error : result.getErrors()) {
            LOG.warn("ERROR: " + error.getErrorName());
            ValidationWarnings warnings = getValidationWarnings(archiveStandard.getValidationWarnings(), "RECIPE", error.getErrorName(), "ERROR", "error.validation.signature", error.getMessage());
            archiveStandard.setValidationWarnings(warnings);
        }
        return result;
    }

    private ValidationWarnings getValidationWarnings(ValidationWarnings warnings, String ruleIDType, String ruleIdValue, String sevirity, String key, String... args) {
        List<ValidationWarning> validationWarnings = new ArrayList<>();
        ValidationWarning warning = new ValidationWarning();
        RuleId ruleID = new RuleId();
        ruleID.setIdType(ruleIDType);
        ruleID.setValue(ruleIdValue);
        warning.setRuleId(ruleID);
        RuleMessage ruleMessage = new RuleMessage();
        List<MessageText> messageTexts = new ArrayList<>();
        Map<String, String> labels = I18nHelper.getAllLanguagesLabels(key, args);
        for (Object entry : labels.entrySet()) {
            Map.Entry<String, String> pair = (Map.Entry<String, String>) entry;
            MessageText messageText = new MessageText();
            messageText.setLanguage(pair.getKey());
            messageText.setValue(pair.getValue());
            messageTexts.add(messageText);
        }

        ruleMessage.getMessageText().addAll(messageTexts);
        warning.setRuleMessage(ruleMessage);
        warning.setSeverity(sevirity);
        validationWarnings.add(warning);
        if (warnings == null) {
            warnings = new ValidationWarnings();
        }
        warnings.getValidationWarning().addAll(validationWarnings);
        return warnings;
    }

    private ID getID(String idValue, String... type) {
        ID id = new ID();
        id.setIdType(type[0]);
        if (type.length > 1) {
            id.setType(type[1]);
        }
        id.setValue(idValue);
        return id;
    }

    public void validateTimeStamping(GetPrescriptionForExecutorResultSealed result, ArchiveStandard aStandard) {
        try {
            //TODO: implement
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            TimeStampResponse response = new TimeStampResponse(Base64.decode(result.getTimestampingId().getBytes()));
            boolean isValid = true;
            TimeStampToken tsToken = response.getTimeStampToken();
            byte[] tokenDigestValue = tsToken.getTimeStampInfo().getMessageImprintDigest();
            String algo = tsToken.getTimeStampInfo().getHashAlgorithm().getAlgorithm().getId();
            byte[] calculatedDigest = ConnectorCryptoUtils.calculateDigest(algo, result.getPrescription());

            if (!MessageDigest.isEqual(calculatedDigest, tokenDigestValue)) {
                isValid = false;
                //throw new Exception("response for different message imprint digest.");
            }

            Attribute scV1 = tsToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
            Attribute scV2 = tsToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);

            if (scV1 == null && scV2 == null) {
                isValid = false;
                ValidationWarnings warnings = getValidationWarnings(aStandard.getValidationWarnings(), "RECIPE", "1038", "ERROR", "error.validation.missing.certificate");
                aStandard.setValidationWarnings(warnings);
            }

            if (scV1 != null && scV2 != null) {
                isValid = false;
                ValidationWarnings warnings = getValidationWarnings(aStandard.getValidationWarnings(), "RECIPE", "1039", "ERROR", "error.validation.conflicting.certificate.attribute");
                aStandard.setValidationWarnings(warnings);
            }
            if (isValid) {
                validateTimeStampToken(tsToken);
            }
        } catch (Throwable ex) {
            LOG.warn("Validation error: ", ex);
            ValidationWarnings warnings = getValidationWarnings(aStandard.getValidationWarnings(), "RECIPE", "1040", "ERROR", "error.validation.timestamp", ex.getMessage());
            aStandard.setValidationWarnings(warnings);
        }
    }

    private boolean validateTimeStampToken(TimeStampToken tsToken) throws Exception {
        boolean result = false;
        KeyStore keyStore = getEncryptionUtils().getTSAKeyStore();
        List<String> aliases = getEncryptionUtils().getTsaStoreAliases();
        if (aliases == null || keyStore == null) {
            throw new IllegalStateException("keystore or aliases not initialised yet : aliases : [" + aliases + "] and keystore : [" + keyStore + "]");
        }

        TimeStampTokenInfo tsi = tsToken.getTimeStampInfo();

        LOG.info("GenTime:" + tsi.getGenTime());
        LOG.info("ImprintAlgOID:" + tsi.getMessageImprintAlgOID());
        LOG.info("Policy:" + tsi.getPolicy());
        //LOG.info("Accuracy:" + tsi.getAccuracy().getSeconds());
        LOG.info("HashAlgorithm:" + tsi.getHashAlgorithm().getAlgorithm().getId());

        boolean signatureValid = false;

        Exception lastException = null;
        for (String alias : aliases) {
            try {
                X509Certificate ttsaCert = (X509Certificate) keyStore.getCertificate(alias);
                String t = ttsaCert.getSubjectX500Principal().getName(X500Principal.RFC1779);
                LOG.debug("Trying to validate timestamp against certificate with alias [" + alias + "] : [" + t + "]");

                X509CertificateHolder tokenSigner = new X509CertificateHolder(ttsaCert.getEncoded());
                SignerInformationVerifier verifier = new BcRSASignerInfoVerifierBuilder(new DefaultCMSSignatureAlgorithmNameGenerator(),
                        new DefaultSignatureAlgorithmIdentifierFinder(), new DefaultDigestAlgorithmIdentifierFinder(), new BcDigestCalculatorProvider()).build(
                        tokenSigner);
                tsToken.validate(verifier);
                signatureValid = true;
                break;
            } catch (Exception e) {
                lastException = e;
                //throw new Exception("timestamp not valid with certificate-alias '" + alias + "': " + e.getMessage());
            }
        }
        if (signatureValid) {
            result = true;
            LOG.debug("timestampToken is valid");
        } else {
            result = false;
            throw new Exception("timestamp is not valid ", lastException);
        }
        return result;
    }

    public void validateRid(String rid) throws IntegrationModuleException {
        Matcher matcher = ridPattern.matcher(rid);
        if (!matcher.find()) {
            LOG.error("Invalid RID was provided.");
            throw new IntegrationModuleException(I18nHelper.getLabel("error.rid.validation", new Object[]{rid}));
        }
    }
}
