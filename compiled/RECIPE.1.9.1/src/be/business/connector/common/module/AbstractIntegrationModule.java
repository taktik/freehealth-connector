/*
 * 
 */
package be.business.connector.common.module;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import be.apb.gfddpp.common.utils.JaxContextCentralizer;
import be.business.connector.common.ApplicationConfig;
import be.business.connector.core.ehealth.services.KgssService;
import be.business.connector.core.ehealth.services.KgssServiceImpl;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.technical.connector.utils.Crypto;
import be.business.connector.core.utils.ETKHelper;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.LoggingUtil;
import be.business.connector.core.utils.MapNamespaceContext;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.MessageDumper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.Qualities;
import be.business.connector.core.utils.STSHelper;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
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
import be.recipe.services.core.Page;
import be.recipe.services.executor.ArchiveStandard;
import be.recipe.services.executor.GetPrescriptionForExecutorResultSealed;
import be.recipe.services.executor.ID;
import be.recipe.services.executor.MessageText;
import be.recipe.services.executor.Properties;
import be.recipe.services.executor.Property;
import be.recipe.services.executor.RuleId;
import be.recipe.services.executor.RuleMessage;
import be.recipe.services.executor.TimestampedPrescription;
import be.recipe.services.executor.ValidationWarning;
import be.recipe.services.executor.ValidationWarnings;
import be.recipe.services.patient.GetPrescriptionForPatientResultSealed;

public abstract class AbstractIntegrationModule {

    private static final Logger LOG = Logger.getLogger(AbstractIntegrationModule.class);

	private static final String VERSION_1 = "v1";
	private static final String VERSION_2 = "v2";
    public static final String EHEALTH_SUCCESS_CODE_100 = "100";
    public static final String EHEALTH_SUCCESS_CODE_200 = "200";
    public static final String EHEALTH_SUCCESS_CODE_300 = "300";
    public static final String EHEALTH_SUCCESS_CODE_400 = "400";
    public static final String EHEALTH_SUCCESS_CODE_500 = "500";

    protected DataUnsealer dataUnsealer = null;
    private DataSealer oldDataSealer = null;
    private DataUnsealer oldDataUnsealer = null;

    private ETKHelper etkHelper;
    private Key symmKey = null;

    private final KgssService kgssService = KgssServiceImpl.getInstance();

	public AbstractIntegrationModule() {
		ApplicationConfig.getInstance().assertInitialized();
        init();
    }

	protected void init() {
        try {
            LOG.info("Init abstractIntegrationModule!");
            LoggingUtil.initLog4J(PropertyHandler.getInstance());

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

            initEncryption();
            LOG.info("End Init abstractIntegrationModule!");
        } catch (Throwable t) {
            LOG.error("Exception in init abstractIntegrationModule: ", t);
            Exceptionutils.errorHandler(t);
        }
    }

	public void initEncryption() {
        try {
            LOG.info("Init the encryption - create symmKey");
            symmKey = getEncryptionUtils().generateSecretKey();
            if (getEncryptionUtils().getOldKeyStore() != null) {
                oldDataSealer = getEncryptionUtils().initOldSealing();
                oldDataUnsealer = getEncryptionUtils().initOldUnSealing();
            }
            LOG.info("Init the encryption - init etkHelper");
			etkHelper = new ETKHelper(getPropertyHandler(), getEncryptionUtils());
		} catch (Exception t) {
            Exceptionutils.errorHandler(t, "error.initialization");
        }
    }

    @Profiled(logFailuresSeparately = true, tag = "0.AbstractIntegrationModule#sealRequest", logger = "org.perf4j.TimingLogger_Common")
	public synchronized byte[] sealRequest(EncryptionToken paramEncryptionToken, byte[] paramArrayOfByte) {
        return Crypto.seal(paramEncryptionToken, paramArrayOfByte);
    }

    /**
	 * Unseal request.
	 *
	 * @param message
	 *            the message
	 * @return the byte[] @ the integration module exception
	 */
    @Profiled(logFailuresSeparately = true, tag = "0.AbstractIntegrationModule#unsealRequest", logger = "org.perf4j.TimingLogger_Common")
	protected byte[] unsealRequest(byte[] message) {
        return Crypto.unseal(message);
    }

    /**
	 * Unseal.
	 *
	 * @param message
	 *            the message
	 * @return the byte[] @ the integration module exception
	 */
	protected byte[] unsealNotif(byte[] message) {
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
            return IOUtils.getBytes(result.getData().getContent());
        }
        return null;
    }

	protected byte[] unsealNotiffeed(byte[] message) {
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
	protected byte[] unsealPrescriptionForUnknown(KeyResult key, byte[] protectedMessage) {
        return Crypto.unsealForUnknown(key, protectedMessage);
    }
    
	protected byte[] unsealForUnknown(KeyResult key, byte[] protectedMessage) {
        return Crypto.unsealForUnknown(key, protectedMessage);
    }

	protected KeyResult getKeyFromKgss(String keyId) {
        return getKeyFromKgss(keyId, null);
    }

    @Profiled(logFailuresSeparately = true, tag = "0.AbstractIntegrationModule#getKeyFromKgss", logger = "org.perf4j.TimingLogger_Common")
	public KeyResult getKeyFromKgss(String keyId, byte[] myEtk) {
        KeyResult keyResult = null;
        try {
            // For test, when a sim key is specified in the config
            if (getPropertyHandler().hasProperty("test_kgss_key")) {
                String part1 = getPropertyHandler().getProperty("test_kgss_key").split(";")[0];
                String part2 = getPropertyHandler().getProperty("test_kgss_key").split(";")[1];
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

	public Key getSymmKey() {
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

	public ETKHelper getEtkHelper() {
        return etkHelper;
    }


    public void setDataUnsealer(DataUnsealer dataUnsealer) {
        this.dataUnsealer = dataUnsealer;
    }

    public JaxContextCentralizer getJaxContextCentralizer() {
        return JaxContextCentralizer.getInstance();
    }

	public byte[] unsealWithSymKey(GetPrescriptionForExecutorResultSealed result, KeyResult key, String identifier, String type)
	{
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

			archivePrescription(unsealedPrescription, result, sealedPrescription, timestampedPrescriptionVersion, tp, key, identifier,
					type);
            return unsealedPrescription;
        } catch (IOException | TechnicalConnectorException | XPathExpressionException ex) {
            Exceptionutils.errorHandler(ex, "error.data.uncompression");
        }
        return null;
    }

	protected byte[] unsealWithSymKey(GetPrescriptionForPatientResultSealed result, KeyResult key, String identifier,
			String type) {
		try {
			byte[] sealedPrescription;
			TimestampedPrescription tp = null;
			try {
				MarshallerHelper<TimestampedPrescription, TimestampedPrescription> helper = new MarshallerHelper<>(
						TimestampedPrescription.class, TimestampedPrescription.class);
				tp = helper.unmarsh(result.getPrescription());
				sealedPrescription = tp.getPrescriptionWithSecurityToken().getPrescriptionSealed();
			} catch (Exception ex) {
				LOG.debug("Prescription is version 1: ", ex);
				sealedPrescription = result.getPrescription();
			}
			byte[] unsealedPrescription = unsealPrescriptionForUnknown(key, sealedPrescription);
			unsealedPrescription = IOUtils.decompress(unsealedPrescription);

			return unsealedPrescription;
		} catch (IOException ex) {
			Exceptionutils.errorHandler(ex, "error.data.uncompression");
		}
		return null;
	}

    private void archivePrescription(byte[] unsealedPrescription, GetPrescriptionForExecutorResultSealed result,
                                     byte[] sealedPrescription, String timestampedPrescriptionVersion, TimestampedPrescription tp, KeyResult key,
			String identifier, String type)
			throws IOException, TechnicalConnectorException, XPathExpressionException {
		boolean archivePrescription = Boolean.parseBoolean(getPropertyHandler().getProperty("ArchivePrescription-xml", "false"));
        ID prescriberFromKmehr = getPrescriberIdFromKmehr(new ByteArrayInputStream(unsealedPrescription));
        ArchiveStandard archiveStandard = new ArchiveStandard();
        LOG.debug("To archive prescription: " + new String(unsealedPrescription, StandardCharsets.UTF_8));
        if (Boolean.parseBoolean(getPropertyHandler().getProperty("validate.befor.archiving", "true"))) {
            validatePrescriber(tp, archiveStandard, prescriberFromKmehr);
            if (VERSION_2.equalsIgnoreCase(timestampedPrescriptionVersion)) {
				validateXadesT(result, archiveStandard, SignatureVerificationError.CERTIFICATE_CHAIN_COULD_NOT_BE_VERIFIED,
						SignatureVerificationError.XADES_SIGNEDPROPS_INVALID_SIGNINGTIME);
            } else {
                validateTimeStamping(result, archiveStandard);
            }
        }
		if (archivePrescription) {
			String archivingPath = getArchivedFilePath(result.getRid());
            archiveStandard = getArchiveStandard(result, unsealedPrescription, sealedPrescription,
                    timestampedPrescriptionVersion, tp, key, identifier, type, prescriberFromKmehr, archiveStandard);
            final MarshallerHelper<ArchiveStandard, ArchiveStandard> helper = new MarshallerHelper<>(ArchiveStandard.class, ArchiveStandard.class);
            helper.writePrescriptionToFile(helper.toXMLByteArray(archiveStandard), archivingPath);
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

	public byte[] getArchivedPrescription(String archivedFilePath, String... options) {
        try {
            return Files.readAllBytes(Paths.get(archivedFilePath));
		} catch (IOException ex) {
        	final InputStream is = this.getClass().getResourceAsStream(archivedFilePath);
			return IOUtils.getBytes(is);
		} catch (Exception ex) {
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
        properties.getProperty().add(getProperty("prescriptionVersion", PropertyHandler.getInstance().getProperty("prescription.version")));
        properties.getProperty().add(getProperty("samv2", extractReferenceSourceVersionFromKmehr(unsealedPrescription)));
        if (result != null && result.getExpirationDate() != null && result.getExpirationDate().getTime() != null) {
			final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			properties.getProperty().add(getProperty("expirationDate", df.format(result.getExpirationDate().getTime())));
		}
		archiveStandard.setProperties(properties);

        return archiveStandard;
    }
    
	@Profiled(logFailuresSeparately = true, tag = "0.AbstractIntegraionModule#extractReferenceSourceVersionFromKmehr", logger = "org.perf4j.TimingLogger_Common")
	public String extractReferenceSourceVersionFromKmehr(final byte[] xmlDocument) {
		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(false);
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document kmehrDocument = builder.parse(new ByteArrayInputStream(xmlDocument));
			final XPath xpath = XPathFactory.newInstance().newXPath();
			final String xpathStr1 = PropertyHandler.getInstance().getProperty("referenceSourceVersion.xpath1");
			final NodeList referenceSourceVersionNodeList1 = (NodeList) xpath.evaluate(xpathStr1, kmehrDocument, XPathConstants.NODESET);
			String referenceSourceVersionPart1 = "";
			if (referenceSourceVersionNodeList1.item(0) != null) {
				referenceSourceVersionPart1 = referenceSourceVersionNodeList1.item(0).getTextContent();
				
			} 
			final String xpathStr2 = PropertyHandler.getInstance().getProperty("referenceSourceVersion.xpath2");
			final NodeList referenceSourceVersionNodeList2 = (NodeList) xpath.evaluate(xpathStr2, kmehrDocument, XPathConstants.NODESET);
			String referenceSourceVersionPart2 = "";
			if (referenceSourceVersionNodeList2.item(0) != null) {
				referenceSourceVersionPart2 += referenceSourceVersionNodeList2.item(0).getTextContent();
			} 
			
			if (StringUtils.isNotBlank(referenceSourceVersionPart1) && StringUtils.isNotBlank(referenceSourceVersionPart2)) {
				return referenceSourceVersionPart1 + ":" + referenceSourceVersionPart2;
			} else {
				return "Unknown";
			}
		} catch (XPathExpressionException | ParserConfigurationException | SAXException | IOException e) {
			Exceptionutils.errorHandler(e);
		}
		return null;
	}

	@Profiled(logFailuresSeparately = true, tag = "0.AbstractIntegraionModule#extractExpirationDateFromKmehr", logger = "org.perf4j.TimingLogger_Common")
	public String extractExpirationDateFromKmehr(final byte[] xmlDocument) {
		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(false);
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document kmehrDocument = builder.parse(new ByteArrayInputStream(xmlDocument));
			final PropertyHandler propertyHandler = PropertyHandler.getInstance();
			final XPath xpath = XPathFactory.newInstance().newXPath();
			final String xpathStr = propertyHandler.getProperty("expirationdate.xpath");
			final NodeList prescriptionVersionNodeList = (NodeList) xpath.evaluate(xpathStr, kmehrDocument, XPathConstants.NODESET);
			if (prescriptionVersionNodeList.item(0) != null) {
				final String prescriptionVersion = prescriptionVersionNodeList.item(0).getTextContent();
				return prescriptionVersion;
			}
			return "";
		} catch (XPathExpressionException | ParserConfigurationException | SAXException | IOException e) {
			Exceptionutils.errorHandler(e);
		}
		return null;
	}

    private Property getProperty(String key, String value) {
        Property property = new Property();
        property.setKey(key);
        property.setValue(value);
        return property;
    }

	private void validatePrescriber(TimestampedPrescription tp, ArchiveStandard archiveStandard, ID prescriberFromKmehr) {
        if (tp != null) {
            try {
                byte[] samlToken = tp.getPrescriptionWithSecurityToken().getSecurityToken();
                Document doc = obtenerDocumentDeByte(samlToken);
                String nihiiInSamlToken = STSHelper.getNihii(doc.getDocumentElement());
                String type = STSHelper.getType(doc.getDocumentElement());

                boolean errorOccurs = false;

                if (StringUtils.isNotBlank(prescriberFromKmehr.getValue()) && StringUtils.isNotBlank(nihiiInSamlToken)) {
                    LOG.debug("NIHII found in the Saml Assertion is [" + nihiiInSamlToken + "], and the one in the kmehr message [" + prescriberFromKmehr.getValue() + "].");
                    LOG.debug("Quality is [" + type +"].");
                    if (!"HOSPITAL".equalsIgnoreCase(type)  && !prescriberFromKmehr.getValue().substring(0, 8).equals(nihiiInSamlToken.substring(0, 8))) {
                        errorOccurs = true;
                    }
                } else {
                    errorOccurs = true;
                }
                if (errorOccurs) {
                    LOG.error("Prescriber mismach between kmehr and SAMLToken!! [" + prescriberFromKmehr.getValue() + "]-[" + nihiiInSamlToken + "]");
                    archiveStandard.setValidationWarnings(getValidationWarnings(archiveStandard.getValidationWarnings(), "RECIPE", "1041", "ERROR", "error.validation.presciber.mismach", prescriberFromKmehr.getValue(), nihiiInSamlToken));
                }
            } catch (Throwable ex) {
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

    private SignatureVerificationResult validateXadesT(GetPrescriptionForExecutorResultSealed gpfers, ArchiveStandard archiveStandard, SignatureVerificationError... errors) throws TechnicalConnectorException {
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

    private void validateTimeStamping(final GetPrescriptionForExecutorResultSealed result, final ArchiveStandard aStandard) {
        try {
            final TimeStampResponse response = new TimeStampResponse(Base64.decode(result.getTimestampingId().getBytes()));
            boolean isValid = true;
            final TimeStampToken tsToken = response.getTimeStampToken();
            final byte[] tokenDigestValue = tsToken.getTimeStampInfo().getMessageImprintDigest();
            final String algo = tsToken.getTimeStampInfo().getHashAlgorithm().getAlgorithm().getId();
            final byte[] calculatedDigest = ConnectorCryptoUtils.calculateDigest(algo, result.getPrescription());

            if (!MessageDigest.isEqual(calculatedDigest, tokenDigestValue)) {
                isValid = false;
            }

            final Attribute scV1 = tsToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
            final Attribute scV2 = tsToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);

            if (scV1 == null && scV2 == null) {
                isValid = false;
                final ValidationWarnings warnings = getValidationWarnings(aStandard.getValidationWarnings(), "RECIPE", "1038", "ERROR", "error.validation.missing.certificate");
                aStandard.setValidationWarnings(warnings);
            }

            if (scV1 != null && scV2 != null) {
                isValid = false;
                final ValidationWarnings warnings = getValidationWarnings(aStandard.getValidationWarnings(), "RECIPE", "1039", "ERROR", "error.validation.conflicting.certificate.attribute");
                aStandard.setValidationWarnings(warnings);
            }
            if (isValid) {
                validateTimeStampToken(tsToken);
            }
        } catch (final Throwable ex) {
            LOG.warn("Validation error: ", ex);
            final ValidationWarnings warnings = getValidationWarnings(aStandard.getValidationWarnings(), "RECIPE", "1040", "ERROR", "error.validation.timestamp", ex.getMessage());
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

	protected Page buildDefaultPage() {
		final Page page = new Page();
		page.setMonth(Calendar.getInstance().get(Calendar.MONTH) - 1);
		page.setYear(Calendar.getInstance().get(Calendar.YEAR));
		return page;
	}
	
	public String getId() {
		return "id" + UUID.randomUUID().toString();
	}
}